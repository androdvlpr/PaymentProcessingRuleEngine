package com.payment.processing.ruleengine.core;

import java.util.HashSet;

public class Order{
	
	private final Customer m_customer;
	private HashSet<String> m_listOfItemsInStock;
	private final AvailableItem[] m_AvailableItems;
	private final Agent m_agent;
	
    public Order(final Customer customer, final AvailableItem[] availableItems, final Agent agent) throws IllegalArgumentException {
        if (availableItems == null || availableItems.length == 0) {
        	throw new IllegalArgumentException("line items are required");
        }
        
        this.m_AvailableItems = availableItems;
        this.m_customer = customer;
        this.m_agent = agent;

        m_listOfItemsInStock = new HashSet<String>();
    }

    public Customer getCustomer(){
        return m_customer;
    }

    public AvailableItem[] getItemsInStock(){
        return m_AvailableItems;
    }

	public Agent getAgent() {
		return m_agent;
    }
    
	public void addGiftByAvailableItemInUnit(String sAvailableUnitInStock) {
        if(!m_listOfItemsInStock.contains(sAvailableUnitInStock))
            m_listOfItemsInStock.add(sAvailableUnitInStock);
    }
    
    public String[] getGiftbyStockInUnit(){
        return m_listOfItemsInStock.toArray(new String[0]);
    }
}