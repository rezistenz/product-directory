package org.rezistenz.product.directory.service.impl;

import java.util.Collection;
import java.util.Map;

import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.CategoryRepository;
import org.rezistenz.product.directory.persistence.ProductRepository;
import org.rezistenz.product.directory.service.CategoryService;
import org.rezistenz.product.directory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public void setCategoryRepository(CategoryRepository categoryRepository){
		this.categoryRepository=categoryRepository;
	}

	@Override
	public Category add(Category entity) {
		return categoryRepository.add(entity);
	}

	@Override
	public Category udpate(Category entity) {
		return categoryRepository.udpate(entity);
	}

	@Override
	public Category findByPK(Long primaryKey) {
		return categoryRepository.findByPK(primaryKey);
	}

	@Override
	public Collection<Category> findByParams(Map<String, Object> params) {
		return categoryRepository.findByParams(params);
	}

	@Override
	public Long findByParamsCount(Map<String, Object> params) {
		return categoryRepository.findByParamsCount(params);
	}

}
