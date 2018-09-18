package com.cardinalhealth.chh.rabdem.stream.ic;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface InsuranceCreateSource
{
	final String OUTPUT = "insuranceCreateChannel";
	
	@Output(OUTPUT)
    MessageChannel insuranceOrders();
}
