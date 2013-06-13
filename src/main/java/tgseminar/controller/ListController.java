package tgseminar.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityQuery;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

public class ListController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		User user = UserServiceFactory.getUserService()
				.getCurrentUser();
				String email = user.getEmail();

		EntityQuery filter = Datastore.query("ToDo").filter("createdBy", FilterOperator.EQUAL, email).sort("createdAt", SortDirection.DESCENDING);
		//EntityQuery sort = Datastore.query("ToDo").sort("", SortDirection.DESCENDING);
		
		List<Entity> filterList = filter.asList();
		
//		for (int i = 0; i < filterList.size(); i++){
//			Entity entity = filterList.get(i);
//			
//			entity.getProperty("title");
//			entity.getProperty("createdBy");
//			entity.getProperty("createdAt");
//			
//			System.out.println(entity.getProperty("title"));
//			System.out.println(entity.getProperty("createdBy"));
//			System.out.println(entity.getProperty("createdAt"));
//		}
		List<Map<String, Object>> entitiesForJSON = new ArrayList<Map<String, Object>>();
		
		for (Entity entity : filterList){
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
