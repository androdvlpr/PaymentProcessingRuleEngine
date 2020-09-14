package com.payment.processing.ruleengine.core;

public class Product{
	private String m_sStockKeepingUnit;

	public Product(String sStockKeepingUnit){
        m_sStockKeepingUnit = sStockKeepingUnit;
    }

    public String getSku(){
        return m_sStockKeepingUnit;
    }
}