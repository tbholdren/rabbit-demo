package com.cardinalhealth.chh.rabdem.stream.ic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.cardinalhealth.chh.rabdem.stream.model.InsuranceCreate;

@EnableBinding(InsuranceCreateSink.class)
public class InsuranceCreateStreamListener
{
	private static final Logger LOG = LoggerFactory.getLogger(InsuranceCreateStreamListener.class);
			
	@StreamListener(target = InsuranceCreateSink.INPUT)
	public void processCheapMeals(InsuranceCreate insuranceCreate) throws Exception 
	{
		LOG.info("received insurance create request: {}", insuranceCreate);
		if (insuranceCreate != null && insuranceCreate.getName() != null && insuranceCreate.getName().contains("fraud"))
		{
			throw new Exception("Insurance fraud found! Move to dead letter queue");
		}
		
		LOG.info("processed");
	}
}
