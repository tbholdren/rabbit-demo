package com.cardinalhealth.chh.rabdem.stream.model;

public class FoodOrder
{
	private String restaurant;
    private String customerAddress;
    private String orderDescription;
    
	@Override
	public String toString()
	{
		return "FoodOrder [restaurant=" + restaurant + ", customerAddress=" + customerAddress + ", orderDescription="
				+ orderDescription + "]";
	}
	
	public String getRestaurant()
	{
		return restaurant;
	}
	public void setRestaurant(String restaurant)
	{
		this.restaurant = restaurant;
	}
	public String getCustomerAddress()
	{
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress)
	{
		this.customerAddress = customerAddress;
	}
	public String getOrderDescription()
	{
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription)
	{
		this.orderDescription = orderDescription;
	}
    
    
}
