package com.cardinalhealth.chh.rabdem;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Component;

@Component
public class Receiver
{
	private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);
			
	//private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) 
    {
        LOG.info("Received <{}>", message);
        if (message != null && message.contains("dead-letter"))
        {
        	LOG.error("message contained dead-letter; throwing exception");
        	//throw new RuntimeException("message specified to go to dead-letter");
        	throw new AmqpRejectAndDontRequeueException("I don't like this message!");
        }
        LOG.info("message processed!");
    }
    
    public void receiveMessage(byte[] bytes)
    {
    	LOG.info("received message as array of bytes!");
    	String message = new String(bytes);
    	receiveMessage(message);
    }
    
    public void receiveMessage(char[] chars)
    {
    	LOG.info("received message as array of chars!");
    	String message = new String(chars);
    	receiveMessage(message);
    }

    /*
    public CountDownLatch getLatch() 
    {
        return latch;
    }
    */
}
