package org.rezistenz.product.directory.web;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rezistenz.product.directory.model.Category;
import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.CategoryRepository;
import org.rezistenz.product.directory.service.ProductService;
import org.rezistenz.product.directory.web.dto.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"/META-INF/applicationContextService.xml",
	"/META-INF/applicationContextWeb.xml"})
@Transactional
public class ProductControllerTest {
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		mockMvc.perform(get("/products"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/list"))
			.andExpect(model().attributeExists("productFilter"))
			.andExpect(model().attributeExists("pagingInfo"))
			.andExpect(model().attributeExists("productPagedList"))
			.andExpect(forwardedUrl("products/list"));
		
		mockMvc.perform(get("/products/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/list"))
			.andExpect(model().attributeExists("productFilter"))
			.andExpect(model().attributeExists("pagingInfo"))
			.andExpect(model().attributeExists("productPagedList"))
			.andExpect(forwardedUrl("products/list"));
		
	}

	@Test
	public void testCreate() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		ProductForm productForm=new ProductForm();
		
		Collection<Category> categories=categoryRepository.findAll();
		
		mockMvc.perform(get("/products/create"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/edit"))
			.andExpect(model().attributeExists("productForm"))
			.andExpect(model().attributeExists("categories"))
			.andExpect(model().attribute("productForm",hasProperty("id", equalTo(0L))))
			.andExpect(model().attribute("categories",categories))
			.andExpect(forwardedUrl("products/edit"));
	}
	
	@Test
	public void testEdit() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		Product product=productService.findById(7L);
		
		assertNotNull(product);
		
		ProductForm productForm=new ProductForm(product);
		
		Collection<Category> categories=categoryRepository.findAll();
		
		mockMvc.perform(get("/products/edit").param("id", "7"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/edit"))
			.andExpect(model().attributeExists("productForm"))
			.andExpect(model().attributeExists("categories"))
			.andExpect(model().attribute("productForm",hasProperty("id", equalTo(product.getId()))))
			.andExpect(model().attribute("productForm",hasProperty("name", equalTo(product.getName()))))
			.andExpect(model().attribute("categories",categories))
			.andExpect(forwardedUrl("products/edit"));
	}
	
	@Test
	public void testSaveEmptyName() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		Collection<Category> categories=categoryRepository.findAll();
		
		mockMvc.perform(
				post("/products/save")
					.param("name", "")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("products/edit"))
			.andExpect(model().attributeExists("productForm"))
			.andExpect(model().attributeExists("categories"))
			.andExpect(model().attribute("categories",categories))
			.andExpect(model().hasErrors())
			.andExpect(forwardedUrl("products/edit"));
	}
	
	@Test
	public void testSaveNotEmpty() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		//get product with id=7 form db
		Product productBeforeUpdate=productService.findById(7L);
		
		//create dto form product
		ProductForm productForm=new ProductForm(productBeforeUpdate);
		
		//update fields for dto
		productForm.setName("updated product");
		productForm.setDescription("updated product description");
		productForm.setProducer("updated product producer");
		productForm.setPrice(new BigDecimal(54321.24));
		
		Calendar createDateCalendar=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
		Date createDate=sdf.parse("26.05.2012");
		createDateCalendar.setTime(createDate);
		
		productForm.setCreateDate(createDateCalendar);
		
		Category category=categoryRepository.findByPK(5L);
		
		assertNotNull(category);
		
		productForm.setCategoryId(category.getId());
		
		//save updated product with id=7 
		mockMvc.perform(
				post("/products/save")
					.param("id", String.valueOf(productForm.getId()))
					.param("name", productForm.getName())
					.param("description", productForm.getDescription())
					.param("producer", productForm.getProducer())
					.param("price", String.valueOf(productForm.getPrice()))
					.param("createDate", sdf.format(productForm.getCreateDate().getTime()))
					.param("categoryId", String.valueOf(productForm.getCategoryId()))
			)
			.andExpect(status().isFound())
			.andExpect(view().name("redirect:/products/edit"))
			.andExpect(redirectedUrl("/products/edit?id=7"));
		
		Collection<Category> categories=categoryRepository.findAll();
		
		//open for edit product with id=7 after save 
		mockMvc.perform(
				get("/products/edit")
					.param("id", "7")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("products/edit"))
			.andExpect(model().attributeExists("productForm"))
			.andExpect(model().attributeExists("categories"))
			.andExpect(model().attribute("categories",categories))
			.andExpect(model().hasNoErrors())
			.andExpect(model().attributeExists("productForm"))
			.andExpect(model().attributeExists("categories"))
			.andExpect(model().attribute("categories",categories))
			.andExpect(model().attribute("productForm",hasProperty("id", equalTo(productForm.getId()))))
			.andExpect(model().attribute("productForm",hasProperty("name", equalTo(productForm.getName()))))
			.andExpect(model().attribute("productForm",hasProperty("description", equalTo(productForm.getDescription()))))
			.andExpect(model().attribute("productForm",hasProperty("producer", equalTo(productForm.getProducer()))))
			.andExpect(model().attribute("productForm",hasProperty("price", equalTo(productForm.getPrice()))))
			.andExpect(model().attribute("productForm",hasProperty("createDate", equalTo(productForm.getCreateDate()))))
			.andExpect(model().attribute("productForm",
					hasProperty(
							"categoryId", 
							equalTo(productForm.getCategoryId()))
						)
				)
			.andExpect(forwardedUrl("products/edit"));
		
		//check saved product in db
		Product productAfterUpdate=productService.findById(7L);
	
		assertTrue(productAfterUpdate.getName().equals(productForm.getName()));
		assertTrue(productAfterUpdate.getDescription().equals(productForm.getDescription()));
		assertThat(productAfterUpdate, hasProperty("producer",equalTo(productForm.getProducer())));
		assertThat(productAfterUpdate, hasProperty("price",equalTo(productForm.getPrice())));
		assertThat(productAfterUpdate, hasProperty("createDate",equalTo(productForm.getCreateDate())));
		assertTrue(productAfterUpdate.getCategory().getId().equals(productForm.getCategoryId()));
	}
}
