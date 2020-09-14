package com.payment.processing.ruleengine.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ProductCategoryTest {
	
	File m_fbaseDir = new File("src/test/resources/ProductCategoryTest");
	
	ObjectMapper m_Mapper = new ObjectMapper(new YAMLFactory());
	
	
	/*
	 * positive test case for the list of product category
	 */
	@Test
	public void getCategoryList() {
		ArrayList<String> listOfProduct = new ArrayList<String>();
		listOfProduct.add(ProductCategory.Books.getName());
		listOfProduct.add(ProductCategory.Physical.getName());
		listOfProduct.add(ProductCategory.Membership.getName());
		listOfProduct.add(ProductCategory.Video.getName());
		try {
			YmlConfiguration ymlConfiguration = m_Mapper.readValue(new File(m_fbaseDir, "product_category.yml"), YmlConfiguration.class);
			assertEquals(ymlConfiguration.getRoles(), listOfProduct);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * negative test case for the list of product category
	 */
	@Test
	public void getCategoryListforNegativeTest() {
		ArrayList<String> listOfProduct = new ArrayList<String>();
		listOfProduct.add(ProductCategory.Books.getName());
		listOfProduct.add(ProductCategory.Physical.getName());
		listOfProduct.add(ProductCategory.Membership.getName());
		listOfProduct.add(ProductCategory.Video.getName());
		try {
			YmlConfiguration ymlConfiguration = m_Mapper.readValue(new File(m_fbaseDir, "product_category_negative.yml"), YmlConfiguration.class);
			assertFalse(ymlConfiguration.getRoles().equals(listOfProduct));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
