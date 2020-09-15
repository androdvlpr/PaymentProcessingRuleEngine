package com.payment.processing.ruleengine.core;

import java.util.ArrayList;
import java.util.List;

public class YmlConfiguration {

	private List<String> productCategory = new ArrayList<>();

	public List<String> getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(List<String> productCategory) {
		this.productCategory = productCategory;
	}
	
}
