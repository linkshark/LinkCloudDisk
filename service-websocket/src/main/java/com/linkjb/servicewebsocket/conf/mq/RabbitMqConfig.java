package com.linkjb.servicewebsocket.conf.mq;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sharkshen
 * @description RabbitMQ配置类
 * @data 2019/5/14 16:42
 */
@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "websocketExchange";

    //topic 模式交换机
    public static final String TOPICEXCHANGE = "websocketTopicExchange";

    public static final String ROUTINGKEY1 = "websocket_queue_key1";

    public static final String ROUTINGKEY2 = "websocket_queue_key2";

    public static final String ROUTINGKEY3 = "websocket_queue_key3";

//    public static final String ROUTINGKEY4 = "websocket_topic_key1";

    @Autowired
    private QueueConfig queueConfig;
    @Autowired
    private  ExchangeConfig exchangeConfig;
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     *功能描述  将消息队列与交换机进行绑定
     * @author shark
     * @date 2019/5/14
     * @param  * @param null
     * @return
     */
    @Bean
    public Binding binding_one(){
        return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY1);
    }

    @Bean
    public Binding binding_two(){
        return BindingBuilder.bind(queueConfig.secondQueue()).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY2);
    }

    @Bean
    public Binding binding_info(){
        return BindingBuilder.bind(queueConfig.infoQueue()).to(exchangeConfig.topicExchange()).with("topic.#");
    }

    @Bean
    public Binding binding_error(){
        return BindingBuilder.bind(queueConfig.errorQueue()).to(exchangeConfig.topicExchange()).with("topic.error");
    }

    /**
     * queue listener  观察 监听模式
     * 当有消息到达时会通知监听在对应的队列上的监听对象
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer_one(){
       // ClassPathXmlApplicationContext;
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.addQueues(queueConfig.firstQueue());
        simpleMessageListenerContainer.addQueues(queueConfig.thirdQueue());
        simpleMessageListenerContainer.addQueues(queueConfig.infoQueue());
        simpleMessageListenerContainer.addQueues(queueConfig.errorQueue());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        return simpleMessageListenerContainer;
    }

    /**
     * 定义rabbit template用于数据的接收和发送
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        /**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        template.setConfirmCallback(msgSendConfirmCallBack());
        //template.setReturnCallback(msgSendReturnCallback());
        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        //  template.setMandatory(true);
        return template;
    }


    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     * @return
     */
    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack(){
        return new MsgSendConfirmCallBack();
    }


}
