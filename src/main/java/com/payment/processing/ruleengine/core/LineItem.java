package com.payment.processing.ruleengine.core;

import java.util.HashMap;

public class LineItem {
	
	private final String m_sStockKeepingUnit;
	public final String m_sName;
	
    public LineItem(final String sStockKeepingUnit, final String sName, final ProductCategory[] categories){
        m_sStockKeepingUnit = sStockKeepingUnit;
        m_sName = sName;

        _categories = new HashMap<String, ProductCategory>();
         if(categories != null){
             for(ProductCategory category : categories){
                 if(!_categories.containsKey(category.getName()))
                    _categories.put(category.getName(), category);
             }
         }
    }

    public String getSku() {
        return m_sStockKeepingUnit;
    }

    public String getName() {
        return m_sName;
    }

    private final HashMap<String, ProductCategory> _categories;
	public boolean hasCategory(final ProductCategory category) {
		return _categories.containsKey(category.getName());
	}
}