package tgseminar.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityQuery;
import org.slim3.memcache.Memcache;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

public class MemCacheController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		
		User user = UserServiceFactory.getUserService()
				.getCurrentUser();
				String email = user.getEmail();
		
		List<Entity> entities = Memcache.get(email);
		
		if(entities == null){
			entities = Datastore.query("ToDo").filter("createdBy", FilterOperator.EQUAL, email).sort("createdAt", SortDirection.DESCENDING).asList();
		}else {
			System.out.println("Hit!");
		}
		
		List<Map<String, Object>> entitiesForJSON = new ArrayList<Map<String, Object>>();
		
		for (Entity entity : entities){
			
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", entity.getKey().getId());
			map.put("createdBy", entity.getProperty("createdBy"));
			map.put("createdAt", entity.getProperty("createdAt"));
			entitiesForJSON.add(map);
			
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String json = JSONObject.valueToString(entitiesForJSON);
		response.getWriter().println(json);
		
		response.flushBuffer();

		response.setStatus(200);	
		return null;
	}

}
