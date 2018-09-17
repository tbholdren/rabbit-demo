package com.cardinalhealth.chh.rabdem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ForceDeadLetterReceiver
{
	private static final Logger LOG = LoggerFactory.getLogger(ForceDeadLetterReceiver.class);
	
	//private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) 
    {
        LOG.info("Received <{}>; throwing exception", message);
        throw new RuntimeException("I am forcing an exception");
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
