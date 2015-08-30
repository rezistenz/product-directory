package org.rezistenz.product.directory.service;

import java.util.Collection;

import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.web.dto.PagingInfo;
import org.rezistenz.product.directory.web.dto.ProductFilter;


public interface ProductService {
	
	long getProductsCount(ProductFilter productFilter);
	Collection<Product> findProducts(ProductFilter productFilter,
			PagingInfo pagingInfo);	
}
