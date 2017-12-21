package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/18.
 * 消息队列监听器，一旦消息队列的消息处理完成返回，则触发监听器的操作
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{//Spring容器启动则监听器启动

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 主线程2的监听器，查看消息队列mockQueue是否完成了请求，如果完成则返回客户端Http响应
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){

        new Thread(()->{
            while(true){
                if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){//消息队列有值--完成了订单

                    String orderNumber = mockQueue.getCompleteOrder();//从消息队列拿订单处理结果
                    logger.info("返回订单处理结果：" + orderNumber);//打印日志输出
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");//拿出订单中对应的DeferredResult，设置为请求成功
                    mockQueue.setCompleteOrder(null);
                }else {//如果没有完成订单，则休息100毫秒继续执行
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
