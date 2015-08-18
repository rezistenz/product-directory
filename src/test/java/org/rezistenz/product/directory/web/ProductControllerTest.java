package org.rezistenz.product.directory.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/applicationContextWeb.xml"})
public class ProductControllerTest {
	
	@Autowired
	private ProductController productController;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		mockMvc.perform(get("/products"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/list"))
			.andExpect(forwardedUrl("products/list"));
		
		mockMvc.perform(get("/products/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/list"))
			.andExpect(forwardedUrl("products/list"));
		
	}

}
