package org.rezistenz.product.directory.web.dto;

public class ProductFilter {

	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductFilter [categoryName=" + categoryName + "]";
	}

}
