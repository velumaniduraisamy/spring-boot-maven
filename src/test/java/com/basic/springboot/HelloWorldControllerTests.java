package com.basic.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@WebMvcTest(HelloWorldController.class)
@ActiveProfiles("test")
public class HelloWorldControllerTests {
//	  @Autowired
//	  private WebApplicationContext webApplicationContext;
//	@Autowired  
	private MockMvc mockMvc;
	  
//
	  @Before
	  public void setUp() {
		  final HelloWorldController helloController = new HelloWorldController();
	    mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
	  }

	@Test
	public void testHello() throws Exception {
		this.mockMvc.perform(get("/hello")).andExpect(status().isOk());
	}

}