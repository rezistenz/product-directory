package org.rezistenz.product.directory.persistence;

import java.util.Collection;
import java.util.Map;

import org.rezistenz.product.directory.model.Category;

public interface CategoryRepository extends EnityRepository<Category, Long> {

	Collection<Category> findByParams(Map<String, Object> params);
	
	Long findByParamsCount(Map<String, Object> params);

	Collection<Category> findAll();

}
