package com.cardinalhealth.chh.rabdem;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration
{
	@Value("${rabbit-demo.amqp.cares.topicExchange.name:cares-exchange}")
	private String topicExchangeName;
	
	@Value("${rabbit-demo.amqp.caresDeadLetter.topicExchange.name:cares-deadletter-exchange}")
	private String deadLetterTopicExchangeName;

	@Value("${rabbit-demo.amqp.cares.queue.name:cares-orders}")
    private String queueName;
    
	@Value("${rabbit-demo.amqp.caresDeadLetter.queue.name:cares-orders-deadletter}")
    private String deadLetterQueueName;
	
	@Value("${rabbit-demo.amqp.cares.routing-key:cares.orders}")
	private String routingKey;
	
	@Value("${rabbit-demo.amqp.caresDeadLetter.routing-key:cares.orders.deadletter}")
	private String deadLetterRoutingKey;
    
    @Bean
    Queue queue() 
    {
    	return QueueBuilder.nonDurable(queueName)
    			           .withArgument("x-dead-letter-exchange", deadLetterTopicExchangeName)
    			           .withArgument("x-dead-letter-routing-key", routingKey)
    			           .withArgument("x-message-ttl", 10000)
    			           .withArgument("x-max-length", 50)
    			           .build();
    }
    
    @Bean
    Queue deadLetterForceQueue()
    {
    	return new Queue(deadLetterQueueName, false);
    }

    @Bean
    TopicExchange exchange() 
    {
        return new TopicExchange(topicExchangeName);
    }
    
    @Bean
    TopicExchange deadLetterExchange()
    {
    	return new TopicExchange(deadLetterTopicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) 
    {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    
    @Bean
    Binding deadLetterBinding(Queue deadLetterForceQueue, TopicExchange deadLetterExchange)
    {
    	return BindingBuilder.bind(deadLetterForceQueue).to(deadLetterExchange).with(routingKey);
    }
    
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter
            //MessageListenerAdapter forceDeadLetterListenerAdapter
    		) 
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    
    /*
    @Bean
    SimpleMessageListenerContainer deadLetterContainer(ConnectionFactory connectionFactory,
            MessageListenerAdapter forceDeadLetterListenerAdapter) 
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(deadLetterQueueName);
        container.setMessageListener(forceDeadLetterListenerAdapter);
        return container;
    }
    */
    
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) 
    {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    
    /*
    @Bean
    MessageListenerAdapter forceDeadLetterListenerAdapter(ForceDeadLetterReceiver forceDeadLetterReceiver) 
    {
    	return new MessageListenerAdapter(forceDeadLetterReceiver, "receiveMessage");
    }
    */
}
