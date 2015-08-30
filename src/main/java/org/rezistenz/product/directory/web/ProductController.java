package org.rezistenz.product.directory.web;

import java.util.Collection;
import java.util.logging.Logger;

import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.service.ProductService;
import org.rezistenz.product.directory.web.dto.PagingInfo;
import org.rezistenz.product.directory.web.dto.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private static Logger log=Logger.getLogger(ProductController.class.getName());
	
	@RequestMapping({"","/list"})
	public String list(
			ProductFilter productFilter,
			PagingInfo pagingInfo, 
			Model model){
		
		log.info(productFilter.toString());
		log.info(pagingInfo.toString());
		
		if(pagingInfo.getPageSize() == 0){
			pagingInfo.setPageSize(5);
		}
		
		long productsCount=productService.getProductsCount(productFilter);
		
		pagingInfo.setItemsCount(productsCount);
		
		Collection<Product> products=productService.findProducts(productFilter, pagingInfo);
		
		model.addAttribute("productFilter", productFilter);
		model.addAttribute("pagingInfo", pagingInfo);
		
		model.addAttribute("products", products);
		
		return "products/list";
	}
}
