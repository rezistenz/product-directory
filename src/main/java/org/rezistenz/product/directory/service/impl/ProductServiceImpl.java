package org.rezistenz.product.directory.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.CategoryRepository;
import org.rezistenz.product.directory.persistence.ProductRepository;
import org.rezistenz.product.directory.service.CategoryService;
import org.rezistenz.product.directory.service.ProductService;
import org.rezistenz.product.directory.web.dto.PagingInfo;
import org.rezistenz.product.directory.web.dto.ProductFilter;
import org.rezistenz.product.directory.web.dto.ProductForm;
import org.rezistenz.product.directory.web.dto.ProductListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public void setProductRepository(ProductRepository productRepository){
		this.productRepository=productRepository;
	}
	
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Collection<ProductListItem> findProducts(ProductFilter productFilter,
			PagingInfo pagingInfo) {
		Map<String, Object> params = getParamsMap(productFilter);
		
		params.put("order_dir", pagingInfo.getOrderDir());
		params.put("order_col", pagingInfo.getOrderCol());
		
		params.put("page_size", pagingInfo.getPageSize());
		params.put("page_index", pagingInfo.getPageIndex());
		
		return productRepository.findByParams(params);
	}
	
	private Map<String, Object> getParamsMap(ProductFilter productFilter) {
		Map<String, Object> params=new HashMap<String, Object>();
		
		params.put("category_name", productFilter.getCategoryName());
		
		return params;
	}

	@Override
	@Transactional(readOnly=true)
	public long getProductsCount(ProductFilter productFilter) {
		Map<String, Object> params = getParamsMap(productFilter);
		
		return productRepository.findByParamsCount(params);
	}

	@Override
	@Transactional(readOnly=true)
	public Product findById(long id) {
		return productRepository.findByPK(id);
	}

	@Override
	public void save(ProductForm productForm) {
		Product product=null;
		
		if(productForm.getId() > 0L){
			product=productRepository.findByPK(productForm.getId());
		}else{
			product=new Product();
		}
		
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		product.setPrice(productForm.getPrice());
		product.setCreateDate(productForm.getCreateDate());
		product.setProducer(productForm.getProducer());
		
		Category category=categoryRepository.findByPK(productForm.getCategoryId());
		
		product.setCategory(category);
		
		if(product.getId() == null || product.getId() <= 0L){
			product=productRepository.add(product);
		}else{
			product=productRepository.udpate(product);
		}
		
		productForm.setId(product.getId());
	}
	
}
