package tgseminar.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class UpdateController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		
		String idString = request.getParameter("id");
		
		String title = request.getParameter("title");
		
		if (idString == null || "".equals(idString)){
			response.setStatus(400);
			return null;
		}
		int id;
		try {
			id =Integer.parseInt(idString);
		} catch(NumberFormatException e) {
			response.setStatus(400);
			return null;
		}
		
		if (title == null || "".equals(title)){
			response.setStatus(400);
			return null;
		}
		
		Key key = Datastore.createKey("ToDo", id);
//		Entity entity = Datastore.getOrNull(key);
//		
//		if(entity == null){
//			response.setStatus(400);
//			return null;
//			
//		}

		Entity entity;
		try {
			entity = Datastore.get(key);
		} catch (Exception e) {
			response.setStatus(400);
			return null;
		}
		
		return null;
	}

}
