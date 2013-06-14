package tgseminar.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.tester.ControllerTestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UpdateControllerTest extends ControllerTestCase {
	
	@Test
	public void respond400IfIdNotSpecified() throws NullPointerException, 
		IllegalArgumentException, IOException, ServletException{
		
//		tester.param("id", "1");
		tester.param("title", "To-Do #1");
		tester.start("/Update");
		
		//assert response from server is 400
		
		assertThat(tester.response.getStatus(), is(400));
		
	}

}
