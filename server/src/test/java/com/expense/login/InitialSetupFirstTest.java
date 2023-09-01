package com.expense.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import com.expense.exception.UserAlreadyExistsException;
import com.expense.filter.AuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(MethodName.class)
class InitialSetupFirstTest {
	
	private MockMvc mock;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private AuthenticationFilter authFilter;
	
	@Autowired
	private WebApplicationContext context;
	
	
	
	@BeforeEach
	public  void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.addFilters(authFilter).build();
	}
	
	@Test
	void auth_a1_checkRegister() throws Exception {
		String formData = "{\"userName\":\"testUser\",\"firstName\":\"test user\",\"lastName\":\"last name\",\"email\":\"testUser@gmail.com\",\"mobileNum\":\"9123456789\",\"profileUrl\":\"none\",\"password\":\"AliensAtWork@123\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User registered successfully."))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.userName").value("testUser"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("test user"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("last name"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("testUser@gmail.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.mobileNum").value("9123456789"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.profileUrl").value("none"));
	}
	
	@Test
	void auth_a2_checkRegister_existing() throws Exception {
		String formData = "{\"userName\":\"testUser\",\"firstName\":\"test user\",\"lastName\":\"last name\",\"email\":\"testUser@gmail.com\",\"mobileNum\":\"9123456789\",\"profileUrl\":\"none\",\"password\":\"AliensAtWork@123\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(result -> {
			assertTrue(result.getResolvedException() instanceof UserAlreadyExistsException);
			assertEquals("User already exits!",result.getResolvedException().getMessage());
		});
	}
	@Test
	void auth_a3_checkRegister_invalid_body_1() throws Exception {
		String formData = "{\"userName\":\"te\",\"firstName\":\"test user\",\"lastName\":\"last name\",\"email\":\"testUser@gmail.com\",\"mobileNum\":\"9123456789\",\"profileUrl\":\"none\",\"password\":\"AliensAtWork@123\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(result -> {
			assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
		});
	}
	
	@Test
	void auth_a4_checkRegister_invalid_body_2() throws Exception {
		String formData = "{\"userName\":\"testUser\",\"firstName\":\"te\",\"lastName\":\"la\",\"email\":\"testUs.com\",\"mobileNum\":\"91456789\",\"profileUrl\":\"none\",\"password\":\"password\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(result -> {
			assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
		});
	}
	
	static String token = "";
	
	@Test
	void auth_a5_login() throws Exception {
		String formData = "{\"username\":\"testUser\",\"password\":\"AliensAtWork@123\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		MvcResult result = this.mock.perform(servletRequestBuilder)
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value("User logged in  successfully."))
			.andExpect(MockMvcResultMatchers.jsonPath("$.data.token").exists()).andReturn();
		ObjectNode response = mapper.readValue(result.getResponse().getContentAsString(),ObjectNode.class);
		token = response.get("data").get("token").asText();
		
	}
	
	@Test
	void auth_a6_login_invalid_creds() throws Exception {
		String formData = "{\"username\":\"testUser\",\"password\":\"AliensAtWork\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid credentils provided."));
	}

	@Test
	void auth_a7_login_invalid_username() throws Exception {
		String formData = "{\"username\":\"invalidUser\",\"password\":\"AliensAtWork\"}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User not found."));
	}
	
	@Test
	void auth_a8_create_expense() throws Exception {
		String formData = "{\"category\":\"misc\",\"description\":\"Just description\",\"date\":\"2023-08-29\",\"amount\":23437.98}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/expense/")
				.header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("200"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Expense added succcessfully."))
		.andExpect(MockMvcResultMatchers.jsonPath("$.data").exists());
	}
	
	@Test
	void auth_a9_create_expense_invalid_token() throws Exception {
		String formData = "{\"category\":\"misc\",\"description\":\"Just description\",\"date\":\"2023-08-29\",\"amount\":23437.98}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/expense/")
				.header("Authorization", "Bearer 1223423"+token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("401"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Full authentication is required to access this resource"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Unauthorized"));
	}
	
	@Test
	void auth_b1_create_expense_no_token() throws Exception {
		String formData = "{\"category\":\"misc\",\"description\":\"Just description\",\"date\":\"2023-08-29\",\"amount\":23437.98}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/expense/")
				.header("Authorization", "")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("401"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Full authentication is required to access this resource"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Unauthorized"));
	}
	
	@Test
	void auth_b2_create_expense_no_header() throws Exception {
		String formData = "{\"category\":\"misc\",\"description\":\"Just description\",\"date\":\"2023-08-29\",\"amount\":23437.98}";
		MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
				.post("/api/v1/expense/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(formData);
		this.mock.perform(servletRequestBuilder)
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("401"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Full authentication is required to access this resource"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Unauthorized"));
	}
	
}
