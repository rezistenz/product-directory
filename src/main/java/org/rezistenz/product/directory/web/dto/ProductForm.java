package org.rezistenz.product.directory.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.model.Product;
import org.springframework.format.annotation.DateTimeFormat;

public class ProductForm {

	public ProductForm() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductForm(Product product) {
		this.id=product.getId();
		this.name=product.getName();
		this.description=product.getDescription();
		this.producer=product.getProducer();
		this.price=product.getPrice();
		this.createDate=product.getCreateDate();
		Category category = product.getCategory();
		if(category!=null){
			this.categoryId=category.getId();
		}
	}
	
	private long id;
	
	@NotNull
	@Size(min=1,max=200)
	private String name;
	
	private String description;
	private String producer;
	private BigDecimal price;
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Calendar createDate;
	
	private long categoryId;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
}
