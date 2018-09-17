package com.cardinalhealth.chh.rabdem.amqp;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amqp")
public class AMQPController
{
	private static final Logger LOG = LoggerFactory.getLogger(AMQPController.class);
	
	@Value("${rabbit-demo.amqp.cares.topicExchange.name:cares-exchange}")
	private String topicExchangeName;
	
	@Value("${rabbit-demo.amqp.caresDeadLetter.topicExchange.name:cares-deadletter-exchange}")
	private String deadLetterTopicExchangeName;
	
	@Value("${rabbit-demo.amqp.cares.routing-key:cares.orders}")
	private String routingKey;
	
	@Resource
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping
    public String message(@RequestParam String message) 
	{
		LOG.info("Sending message...");
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
        LOG.info("sent!");
        
        return "spiffy";
    }
    
}
