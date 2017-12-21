package com.imooc.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/12/18.
 */
@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 同步处理的方式进行下单
     * 日志打印结果---主线程开始到结束 相差1秒（1000毫秒）
     * @return
     */
    @RequestMapping("/orderSync")
    public String orderSync() throws Exception{
        logger.info("主线程开始");
        Thread.sleep(1000);//模拟下单请求主线程的耗时
        logger.info("主线程返回");
        return "success";
    }

    /**
     * 使用Callable异步处理的方式进行下单
     * 日志打印结果
     * ---主线程开始到结束 相差0.001秒（几乎马上返回）
     * ---副线程执行了1秒后返回（由副线程执行耗时操作，tomcat不需要等待主线程返回）
     * ---大大提高服务器吞吐量
     * @return
     */
    @RequestMapping("/orderAsync")
    public Callable<String> orderAsync() throws Exception{
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);//模拟下单请求主线程的耗时
                logger.info("副线程返回");
                return "success";
            }
        };

        logger.info("主线程返回");
        return result;
    }

    /**
     * 使用DeferredResult异步处理的方式进行下单
     * 日志打印结果
     * 共有3个线程：主线程1请求线程，消息队列处理线程，主线程2响应线程
     * ---主线程1开始到结束 相差0.002秒（几乎马上返回）
     * ---消息队列线程执行了1秒后返回（由消息队列线程执行耗时操作，tomcat不需要等待主线程返回）
     * ---主线程2开始到结束 相差几十毫秒（几乎马上返回）
     * @return
     */
    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception{
        logger.info("主线程开始");

        /**
         * 主线程1代码块
         */
        String orderNumber = RandomStringUtils.randomNumeric(8);//生成随机订单号
        mockQueue.setPlaceOrder(orderNumber);//放入消息队列中
        DeferredResult<String> result = new DeferredResult<>();//生成一个处理结果对象
        deferredResultHolder.getMap().put(orderNumber,result);//将订单号和处理结果保存在map中

        logger.info("主线程返回");
        return result;
    }
}
