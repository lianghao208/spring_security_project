package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/18.
 * 模拟消息队列异步处理请求的类
 */
@Component
public class MockQueue {

    //下单请求消息
    private String placeOrder;

    //下单完成消息
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setPlaceOrder(String placeOrder) throws Exception{
        new Thread(()->{
            logger.info("接到下单请求， " + placeOrder);
            try {
                Thread.sleep(1000);//模拟其它服务器进程处理下单请求的耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完成, " + placeOrder);
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
