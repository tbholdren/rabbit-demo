package com.cardinalhealth.chh.rabdem.stream.fo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@EnableBinding(Sink.class)
public class FoodOrderStreamListener
{
	private static final Logger LOG = LoggerFactory.getLogger(FoodOrderStreamListener.class);
	
	@StreamListener(target = Sink.INPUT)
	public void processCheapMeals(String meal) throws Exception 
	{
		Assert.isTrue(!StringUtils.isEmpty(meal), "meal cannot be empty");
		
		LOG.info("received food order request: {}", meal);
		if(meal.contains("vegetables"))
		{
			throw new Exception("Vegetables! Move to dead letter queue!");
		}
		else if(meal.contains("poison"))
		{
			throw new Exception("Poison! Move to dead letter queue!");
		}
		
		LOG.info("processed");
	}
	
}
