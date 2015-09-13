package org.rezistenz.product.directory.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product")
public class Product {
	
	@TableGenerator(
			name="product_gen",
			table="sequences",
			pkColumnName="name",
			valueColumnName="value",
			pkColumnValue="product",
			initialValue=1000,
			allocationSize=100
		)
	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE, 
		generator="product_gen"
	)
	private Long id;
	
	@NotNull
	@Column(length=200)
	private String name;
	
	@Column(length=10000,
			columnDefinition="TEXT")
	private String description;
	
	@Column(length=500)
	private String producer;
	
	private BigDecimal price;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createDate;
	
	@Column(length=10000,
			columnDefinition="TEXT")
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description="
				+ description + ", producer=" + producer + ", price=" + price
				+ ", createDate=" + createDate + ", imageUrl=" + imageUrl
				+ ", category=" + category + "]";
	}
	
}
