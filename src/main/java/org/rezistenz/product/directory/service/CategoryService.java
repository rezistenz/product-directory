package org.rezistenz.product.directory.service;

import java.util.Collection;

import org.rezistenz.product.directory.model.Category;


public interface CategoryService {
	Collection<Category> findAll();
}
