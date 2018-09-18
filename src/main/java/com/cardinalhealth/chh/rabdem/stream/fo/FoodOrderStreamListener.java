package com.cardinalhealth.chh.rabdem.stream.fo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;

@EnableBinding(Sink.class)
public class FoodOrderStreamListener
{
	private static final Logger LOG = LoggerFactory.getLogger(FoodOrderStreamListener.class);
	
	@StreamListener(target = Sink.INPUT)
	public void processCheapMeals(String meal) throws Exception 
	{
		if(meal.contains("vegetables"))
			throw new Exception("Vegetables! Move to dead letter queue!");
		if(meal.contains("poison"))
			throw new Exception("Poison! Move to dead letter queue!");
		
		LOG.info("Meal consumed: {}", meal);
	}
	
}
