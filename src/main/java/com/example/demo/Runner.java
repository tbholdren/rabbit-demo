package com.example.demo;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Runner implements CommandLineRunner 
{
	private static final Logger LOG = LoggerFactory.getLogger(Runner.class);
	
	@Resource
	private RabbitTemplate rabbitTemplate;
	
	@Resource
    private Receiver receiver;

	@Override
    public void run(String... args) throws Exception 
    {
        LOG.info("Sending message...");
        //rabbitTemplate.convertAndSend(RabbitDemoApplication.DEAD_LETTER_TOPIC_EXCHANGE_NAME, "foo.baz.bad", "Hello from RabbitMQ!");
        //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        LOG.info("sent!");
    }
    
}