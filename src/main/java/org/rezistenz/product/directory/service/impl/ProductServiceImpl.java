package org.rezistenz.product.directory.service.impl;

import java.util.Collection;
import java.util.Map;

import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.ProductRepository;
import org.rezistenz.product.directory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public void setProductRepository(ProductRepository productRepository){
		this.productRepository=productRepository;
	}
	
	@Override
	public Product add(Product entity) {
		return productRepository.add(entity);
	}

	@Override
	public Product udpate(Product entity) {
		return productRepository.udpate(entity);
	}

	@Override
	public Product findByPK(Long primaryKey) {
		return productRepository.findByPK(primaryKey);
	}

	@Override
	public Collection<Product> findByParams(Map<String, Object> params) {
		return productRepository.findByParams(params);
	}

	@Override
	public Long findByParamsCount(Map<String, Object> params) {
		return productRepository.findByParamsCount(params);
	}
}
