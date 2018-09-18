package com.cardinalhealth.chh.rabdem.stream.fo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FoodOrderSource 
{
	final String OUTPUT = "foodOrdersChannel";
	
	@Output(OUTPUT)
    MessageChannel foodOrders();
}