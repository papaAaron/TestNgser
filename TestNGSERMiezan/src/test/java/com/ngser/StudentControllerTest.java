/**
 * 
 */
package com.ngser;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ngser.controller.StudentController;
import com.ngser.entities.Student;
import com.ngser.service.StudentService;



/**
 * @author ars test unit class
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	

	@MockBean
	private StudentService userService;


	static Student result = new Student();

	@BeforeClass
	public static void settingData() {
	
	Student st = new Student();
	st.setStudentId(UUID.fromString("2aa7394e-8d4d-4be4-8ffc-8146dccad3b0"));
	st.setStudentAge(10);
	st.setStudentFirstName("jhon");
	st.setStudentLastName("doe");
		result = st;

	}

	@Test
	public void test() throws Exception {
		// Setup "Mockito" to mock OrderFacade call
		Mockito.when(userService.findbyname(Mockito.anyString())).thenReturn(result);
		String apiUrl = "/test/student/2aa7394e-8d4d-4be4-8ffc-8146dccad3b0";

		// Build a GET Request and send it to the test server
		RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
		MvcResult r = mockMvc.perform(rb).andReturn(); // throws Exception

		// Validate response
		String tr = r.getResponse().getContentAsString();
		 System.out.println("****************************");

		 System.out.println(tr);
		String er="{\"studentId\":\"2aa7394e-8d4d-4be4-8ffc-8146dccad3b0\",\"studentFirstName\":\"jhon\",\"studentLastName\":\"doe\",\"studentAge\":10,\"studentIsDeleted\":false}";
		 System.out.println("===============================");

		 System.out.println(er);

				//JSONAssert.assertEquals(er, tr, true);


	}
}
