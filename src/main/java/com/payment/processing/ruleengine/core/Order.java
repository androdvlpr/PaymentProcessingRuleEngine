package com.payment.processing.ruleengine.core;

import java.util.HashSet;

public class Order{
	
	private final Customer customer;
	private HashSet<String> listOfStockKeepingUnits;
	private final LineItem[] lineItems;
	private final Agent agent;
	
    public Order(final Customer customer, final LineItem[] lineItems, final Agent agent) throws IllegalArgumentException {
        if (lineItems == null || lineItems.length == 0) {
        	throw new IllegalArgumentException("line items are required");
        }
        
        this.lineItems = lineItems;
        this.customer = customer;
        this.agent = agent;

        listOfStockKeepingUnits = new HashSet<String>();
    }

    public Customer getCustomer(){
        return customer;
    }

    public LineItem[] getLineItems(){
        return lineItems;
    }

	public Agent getAgent() {
		return agent;
    }
    
	public void addGiftBySku(String sStockKeepingUnit) {
        if(!listOfStockKeepingUnits.contains(sStockKeepingUnit))
            listOfStockKeepingUnits.add(sStockKeepingUnit);
    }
    
    public String[] getGiftSkus(){
        return listOfStockKeepingUnits.toArray(new String[0]);
    }
}