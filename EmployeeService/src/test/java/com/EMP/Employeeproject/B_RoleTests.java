package com.EMP.Employeeproject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.EMP.Employeeproject.bean.Role;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B_RoleTests extends AbstractEmployeeprojectApplicationTests {

	private String link = "/Role";

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void A_updateNoRoleTesting() throws Exception {

		String uri = link;

		Role role = new Role("AM", "Assistant Manager");

		String json = super.mapToJson(role);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.BAD_REQUEST.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed update Role for the role which is not already present");
	}

	@Test
	public void B_createRoleTesting() throws Exception {

		String uri = link;

		Role role = new Role("AM", "Assistant Manager");

		String json = super.mapToJson(role);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.CREATED.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed Crete Role URI");
	}

	@Test
	public void C_updateRoleTesting() throws Exception {
		String uri = link;

		Role role = new Role("AM", "Associate Manager");

		String json = super.mapToJson(role);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.OK.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed Update Role URI");
	}

	@Test
	public void D_getRoleTesting() throws Exception {
		String uri = link + "/AM";

		Role role = new Role("AM", "Associate Manager");

		String json = super.mapToJson(role);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.OK.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed Update Role URI");
	}

	@Test
	public void E_deleteRoleTesting() throws Exception {
		String uri = link + "?role=AM";

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.OK.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo("Role AM has been deleted sucessfully");

		System.out.println("LOG: Successfully Executed Delete Role URI");
	}

}
