package org.rezistenz.product.directory.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.rezistenz.product.directory.persistence.ProductRepository;
import org.rezistenz.product.directory.service.ProductService;
import org.rezistenz.product.directory.web.dto.PagingInfo;
import org.rezistenz.product.directory.web.dto.ProductFilter;
import org.rezistenz.product.directory.web.dto.ProductListItem;
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
	
}
