package com.payment.processing.ruleengine.core;

import java.util.HashMap;

public class AvailableItem {
	
	private final String m_sItemInUnit;
	public final String m_sName;
	private final HashMap<String, ProductCategory> m_mapOfProductCategory;
	
    public AvailableItem(final String sItemInUnit, final String sName, final ProductCategory[] categories){
        m_sItemInUnit = sItemInUnit;
        m_sName = sName;

        m_mapOfProductCategory = new HashMap<String, ProductCategory>();
         if(categories != null){
             for(ProductCategory category : categories){
                 if(!m_mapOfProductCategory.containsKey(category.getProductCategory()))
                    m_mapOfProductCategory.put(category.getProductCategory(), category);
             }
         }
    }

    public String getItemInUnit() {
        return m_sItemInUnit;
    }

    public String getName() {
        return m_sName;
    }

	public boolean hasCategory(final ProductCategory category) {
		return m_mapOfProductCategory.containsKey(category.getProductCategory());
	}
}