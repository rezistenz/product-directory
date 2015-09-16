package org.rezistenz.product.directory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="category")
@NamedQuery(
		name="findAll",
		query="select c from Category c"
	)
public class Category {
	
	@TableGenerator(
			name="category_gen",
			table="sequences",
			pkColumnName="name",
			valueColumnName="value",
			pkColumnValue="category",
			initialValue=1000
		)
	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE, 
		generator="category_gen"
	)
	private Long id;
	
	@NotNull
	@Column(length=200)
	private String name;
	
	@Column(length=10000,
			columnDefinition="TEXT")
	private String description;

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
	
}
