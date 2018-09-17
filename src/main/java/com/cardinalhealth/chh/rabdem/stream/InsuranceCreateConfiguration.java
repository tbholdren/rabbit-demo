package com.cardinalhealth.chh.rabdem.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(InsuranceCreateSink.class)
public class InsuranceCreateConfiguration
{
	private static final Logger LOG = LoggerFactory.getLogger(InsuranceCreateConfiguration.class);
			
	@StreamListener(target = InsuranceCreateSink.INPUT)
	public void processCheapMeals(String meal) throws Exception 
	{
		if(meal.contains("vegetables"))
			throw new Exception("Vegetables! Move to dead letter queue!");
		if(meal.contains("poison"))
			throw new Exception("Poison! Move to dead letter queue!");
		
		LOG.info("insurance order consumed: {}", meal);
	}
}
