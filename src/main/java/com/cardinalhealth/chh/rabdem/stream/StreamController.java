package com.cardinalhealth.chh.rabdem.stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class StreamController
{
	private static final Logger LOG = LoggerFactory.getLogger(StreamController.class);
	
	@Resource
    private FoodOrderSource foodOrderSource;
	
	@Resource
	private InsuranceCreateSource insuranceOrderSource;
	
	@PostMapping("/order")
    public String orderFood(@RequestBody FoodOrder foodOrder)
	{
        foodOrderSource.foodOrders().send(MessageBuilder.withPayload(foodOrder).build());
        LOG.info("order: {}", foodOrder);
        return "food ordered!";
    }
	
	@PostMapping("/insurance")
	public String orderInsurance(@RequestBody FoodOrder foodOrder)
	{
		insuranceOrderSource.insuranceOrders().send(MessageBuilder.withPayload(foodOrder).build());
		LOG.info("insurance order: {}", foodOrder);
		return "insurance ordered!";
	}
}
