package com.cardinalhealth.chh.rabdem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Component;

@Component
public class Receiver
{
	private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);
			
	public void receiveMessage(String message) 
    {
        LOG.info("Received <{}>", message);
        if (message != null)
        {
        	if (message.contains("reject"))
        	{
        		LOG.error("message contained reject; throwing reject exception");
            	throw new AmqpRejectAndDontRequeueException("message rejected");
        	}
        	else if (message.contains("retry"))
        	{
        		LOG.error("message contained retry; throwing retry exception");
        		throw new RuntimeException("let's re-try");
        	}
        	
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
   
}
