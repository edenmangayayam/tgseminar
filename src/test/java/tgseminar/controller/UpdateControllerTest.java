package tgseminar.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

import com.google.appengine.api.datastore.Entity;

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

	@Test
	public void respond400IfIdIsNotNumeric() throws NullPointerException, 
		IllegalArgumentException, IOException, ServletException{
		
		tester.param("id", "hai");
		tester.param("title", "To-Do #1");
		tester.start("/Update");
		
		//assert response from server is 400
		
		assertThat(tester.response.getStatus(), is(400));
		
	}

	@Test
	public void respond400IfTitleIsNotSpecified() throws NullPointerException, 
		IllegalArgumentException, IOException, ServletException{
		
		tester.param("id", "1");
		//tester.param("title", "To-Do #1");
		tester.start("/Update");
		
		//assert response from server is 400
		
		assertThat(tester.response.getStatus(), is(400));
		
	}

	@Test
	public void respond400IfEntityDoesNotExist() throws NullPointerException, 
		IllegalArgumentException, IOException, ServletException{
		
		tester.param("id", "3");
		tester.param("title", "To-Do #3 modified");
		tester.start("/Update");
		
		//assert response from server is 400
		
		assertThat(tester.response.getStatus(), is(400));
		
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		Entity entity1 = new Entity(Datastore.createKey("Todo", 1));
		entity1.setProperty("title", "To-Do #1");
		entity1.setProperty("createdBy", "loginuser@example.com");
		entity1.setProperty("createdAt", new Date());

		Entity entity2 = new Entity(Datastore.createKey("Todo", 1));
		entity2.setProperty("title", "To-Do #1");
		entity2.setProperty("createdBy", "loginuser@example.com");
		entity2.setProperty("createdAt", new Date());
		
		Datastore.put(entity1, entity2);
		
		
	}

	
	
	
}
