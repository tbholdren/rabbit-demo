package com.cardinalhealth.chh.rabdem.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface InsuranceCreateSource
{
	final String OUTPUT = "insuranceCreateChannel";
	
	@Output(OUTPUT)
    MessageChannel insuranceOrders();
}
