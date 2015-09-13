package org.rezistenz.product.directory.persistence;

import java.util.Collection;
import java.util.Map;

import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.web.dto.ProductListItem;

public interface ProductRepository extends EnityRepository<Product, Long> {
	Collection<ProductListItem> findByParams(Map<String, Object> params);
	Long findByParamsCount(Map<String, Object> params);
}
