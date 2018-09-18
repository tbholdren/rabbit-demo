package com.cardinalhealth.chh.rabdem.stream.model;

public class InsuranceCreate
{
	private String name;
	
	private String address;
	
	private boolean active;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	@Override
	public String toString()
	{
		return "InsuranceCreate [name=" + name + ", address=" + address + ", active=" + active + "]";
	}
	
}
