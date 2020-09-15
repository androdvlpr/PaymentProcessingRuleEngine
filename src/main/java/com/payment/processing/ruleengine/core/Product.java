package com.payment.processing.ruleengine.core;

public class Product{
	private String m_sStockInUnit;

	public Product(String sStockKeepingUnit){
        m_sStockInUnit = sStockKeepingUnit;
    }

    public String getStockInUnit(){
        return m_sStockInUnit;
    }
}