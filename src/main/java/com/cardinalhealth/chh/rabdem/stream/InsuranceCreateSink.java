package com.cardinalhealth.chh.rabdem.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InsuranceCreateSink
{
	final String INPUT = "insuranceCreate";
	
	@Input(INPUT)
	SubscribableChannel input();
}
