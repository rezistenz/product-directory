package org.rezistenz.product.directory.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class ProductListItem {
	
	public ProductListItem(Long id, String name, String categoryName,
			String producer, BigDecimal price, Calendar createDate) {
		super();
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
		this.producer = producer;
		this.price = price;
		this.createDate = createDate;
	}
	
	private Long id;
	private String name;
	private String categoryName;
	private String producer;
	private BigDecimal price;
	private Calendar createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ProductListItem [id=" + id + ", name=" + name
				+ ", categoryName=" + categoryName + ", producer=" + producer
				+ ", price=" + price + ", createDate=" + createDate + "]";
	}
}
