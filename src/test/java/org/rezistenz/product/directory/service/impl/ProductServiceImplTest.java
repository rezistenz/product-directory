package org.rezistenz.product.directory.service.impl;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.impl.ProductRpositoryImplTest;
import org.rezistenz.product.directory.service.ProductService;
import org.rezistenz.product.directory.web.dto.PagingInfo;
import org.rezistenz.product.directory.web.dto.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/applicationContextService.xml"})
@Transactional
public class ProductServiceImplTest {

	private static final int PAGE_SIZE = 5;

	private static Logger log=Logger.getLogger(ProductServiceImplTest.class.getName());
	
	private static final String CATEGORY_NAME_COMPUTERS = "computers";

	private static final int DEFAULT_PRODUCTS_COUNT = 7;
	private static final int PRODUCTS_CATEGORY_COMPUTERS_COUNT = 3;
	
	@Autowired
	private ProductService productService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindProductsWithEmptyParams() {
		ProductFilter productFilter=new ProductFilter();
		
		PagingInfo pagingInfo=new PagingInfo();
		
		Collection<Product> products=productService.findProducts(productFilter, pagingInfo);
		
		assertTrue(products.size() == DEFAULT_PRODUCTS_COUNT);
	}

	@Test
	public void testFindProductsWithNotEmptyParams() {
		ProductFilter productFilter=new ProductFilter();
		productFilter.setCategoryName(CATEGORY_NAME_COMPUTERS);
		
		PagingInfo pagingInfo=new PagingInfo();
		
		Collection<Product> products=productService.findProducts(productFilter, pagingInfo);
		
		assertTrue(products.size() == PRODUCTS_CATEGORY_COMPUTERS_COUNT);
	}
	
	@Test
	public void testGetProductsCountForEmptyCategory() {
		ProductFilter productFilter=new ProductFilter();
		
		long productsCount = productService.getProductsCount(productFilter);
		
		assertTrue(productsCount == DEFAULT_PRODUCTS_COUNT);
	}

	@Test
	public void testGetProductsCountForNotEmptyCategory() {
		ProductFilter productFilter=new ProductFilter();
		productFilter.setCategoryName(CATEGORY_NAME_COMPUTERS);
		
		long productsCount = productService.getProductsCount(productFilter);
		
		assertTrue(productsCount == PRODUCTS_CATEGORY_COMPUTERS_COUNT);
	}
	
	@Test
	public void testFindProductsWithPagingFirstPage() {
		int pageIndex = 0;
		
		ProductFilter productFilter=new ProductFilter();
		
		PagingInfo pagingInfo=new PagingInfo();
		pagingInfo.setPageSize(PAGE_SIZE);
		pagingInfo.setPageIndex(pageIndex);
		
		Collection<Product> products=productService.findProducts(productFilter, pagingInfo);
		
		log.info("products.size(): "+products.size());
		
		for (Product product : products) {
			log.info(product.toString());
		}
		
		assertTrue(products.size() == PAGE_SIZE);
	}
	
	@Test
	public void testFindProductsWithPagingSecondPage() {
		int countPages = (int) Math.ceil( DEFAULT_PRODUCTS_COUNT / (double)PAGE_SIZE);
		int lastPageIndex = countPages - 1;
		
		int pageIndex = lastPageIndex;
		
		ProductFilter productFilter=new ProductFilter();
		
		PagingInfo pagingInfo=new PagingInfo();
		pagingInfo.setPageSize(PAGE_SIZE);
		pagingInfo.setPageIndex(pageIndex);
		
		Collection<Product> products=productService.findProducts(productFilter, pagingInfo);
		
		log.info("products.size(): "+products.size());
		
		for (Product product : products) {
			log.info(product.toString());
		}
		
		int countOnLastPage= DEFAULT_PRODUCTS_COUNT % PAGE_SIZE;
		
		log.info("countOnLastPage: "+countOnLastPage);
		
		assertTrue(products.size() == countOnLastPage);
	}
}
