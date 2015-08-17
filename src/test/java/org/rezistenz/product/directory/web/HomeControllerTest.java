package org.rezistenz.product.directory.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
public class HomeControllerTest {

	@Autowired
	private HomeController homeController;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		
		mockMvc.perform(get("/"))
			.andExpect(status().isFound())
			.andExpect(view().name("redirect:products/list"))
			.andExpect(redirectedUrl("products/list"));
		
		mockMvc.perform(get("/home"))
			.andExpect(status().isFound())
			.andExpect(view().name("redirect:products/list"))
			.andExpect(redirectedUrl("products/list"));
	}

}
