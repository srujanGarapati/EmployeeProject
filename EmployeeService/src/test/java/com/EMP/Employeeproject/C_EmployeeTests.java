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

import com.EMP.Employeeproject.bean.Employee;
import com.EMP.Employeeproject.bean.Role;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class C_EmployeeTests extends AbstractEmployeeprojectApplicationTests {

	private String link = "/Employee";

	private long id;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		String uri = "/Role";
		id = 10000;
		Role role = new Role("AM", "Assistant Manager");

		String json;
		try {
			json = super.mapToJson(role);
			mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
					.andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void A_updateNoEmplopyeeTesting() throws Exception {
		String uri = link;

		Employee emp = new Employee();

		emp.setName("Ravi");

//		emp.setAddress("Bangalore");

		Role role = new Role("AM", "Assistant Manager");

		emp.setRole(role);

		String json = super.mapToJson(emp);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.NOT_FOUND.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed update Employee for the role which is not already present");
	}

	@Test
	public void B_createEmployeeTesting() throws Exception {

		String uri = link;

		Employee emp = new Employee();

		emp.setName("Ravi");

//		emp.setAddress("Bangalore");

		Role role = new Role("AM", "Assistant Manager");

		emp.setRole(role);

		String json = super.mapToJson(emp);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andReturn();
		Employee responseEmployee = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
		id = responseEmployee.getId();
		emp.setId(id);
		json = super.mapToJson(emp);
		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.CREATED.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed Craete Employee URI");
	}

	@Test
	public void C_updateEmployeeTesting() throws Exception {
		String uri = link;

		Employee emp = new Employee();

		emp.setName("Ravi");

//		emp.setAddress("Chennai");

		emp.setId(10000);

		Role role = new Role("AM", "Assistant Manager");

		emp.setRole(role);

		String json = super.mapToJson(emp);

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
	public void D_getEmployeeTesting() throws Exception {

		String uri = link + "/" + id;

		Employee emp = new Employee();

		emp.setName("Ravi");

		emp.setId(10000);

//		emp.setAddress("Chennai");

		Role role = new Role("AM", "Assistant Manager");

		emp.setRole(role);

		String json = super.mapToJson(emp);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.OK.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo(json);

		System.out.println("LOG: Successfully Executed Update Employee URI");
	}

	@Test
	public void E_deleteEmployeeTesting() throws Exception {
		String uri = link + "?id=" + 10000;

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).describedAs("Status Code is not Equal")
				.isEqualTo(HttpStatus.OK.value());

		assertThat(mvcResult.getResponse().getContentAsString()).describedAs("Response is not Equal to actual value")
				.isEqualTo("Employee 10000 has been deleted sucessfully");

		System.out.println("LOG: Successfully Executed Delete Employee URI");
	}

}
