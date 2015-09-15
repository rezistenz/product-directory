package org.rezistenz.product.directory.service.impl;

import java.util.Collection;

import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.persistence.CategoryRepository;
import org.rezistenz.product.directory.service.CategoryService;
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
	@Transactional(readOnly=true)
	public Collection<Category> findAll() {
		return categoryRepository.findAll();
	}

}
