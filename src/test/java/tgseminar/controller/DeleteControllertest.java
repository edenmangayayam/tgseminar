package tgseminar.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class DeleteControllertest extends ControllerTestCase {

	@Test
	public void idIsNotNumeric() throws NullPointerException, 
		IllegalArgumentException, IOException, ServletException{
		
		tester.param("id", "hai");
		tester.param("title", "To-Do #1");
		tester.start("/Delete");
		
		//assert response from server is 400
		
		assertThat(tester.response.getStatus(), is(400));
		
	}


}
