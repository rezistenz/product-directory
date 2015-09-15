package org.rezistenz.product.directory.service;

import java.util.Collection;

import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.web.dto.PagingInfo;
import org.rezistenz.product.directory.web.dto.ProductFilter;
import org.rezistenz.product.directory.web.dto.ProductForm;
import org.rezistenz.product.directory.web.dto.ProductListItem;


public interface ProductService {
	Collection<ProductListItem> findProducts(ProductFilter productFilter,
			PagingInfo pagingInfo);
	long getProductsCount(ProductFilter productFilter);
	Product findById(long id);
	void save(ProductForm productForm);
}
