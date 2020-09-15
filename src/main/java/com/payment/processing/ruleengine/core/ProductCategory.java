/*
 * 
 */
package com.payment.processing.ruleengine.core;

public class ProductCategory {
	public final String m_sProductCategory;

	private ProductCategory(final String sProductCategoryName) {
		m_sProductCategory = sProductCategoryName;
	}

	public String getProductCategory() {
		return m_sProductCategory;
	}

	public static final ProductCategory Membership = new ProductCategory("membership");
	public static final ProductCategory Video = new ProductCategory("video");
	public static final ProductCategory Books = new ProductCategory("books");
	public static final ProductCategory Physical = new ProductCategory("physical");
}
