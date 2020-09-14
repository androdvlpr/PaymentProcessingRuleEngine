/*
 * 
 */
package com.payment.processing.ruleengine.core;

public class ProductCategory {
	public final String sProductCategory;

	private ProductCategory(final String name) {
		sProductCategory = name;
	}

	public String getName() {
		return sProductCategory;
	}

	public static final ProductCategory Membership = new ProductCategory("membership");
	public static final ProductCategory Video = new ProductCategory("video");
	public static final ProductCategory Books = new ProductCategory("books");
	public static final ProductCategory Physical = new ProductCategory("physical");
}
