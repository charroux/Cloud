package com.maboite;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class ChercherServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
		String nom = req.getParameter("nom");
		System.out.println("nom="+nom);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Etudiant");
		PreparedQuery pq = datastore.prepare(query);
		for(Entity entity : pq.asIterable()){
			Long num = (Long)entity.getProperty("numero");
			System.out.println(num);
		}
		
		Queue queue = QueueFactory.getDefaultQueue();	// envoi t�che dans file
		queue.add(TaskOptions.Builder.withUrl("/calculTacheDeFond").method(Method.GET));
		
		resp.sendRedirect("index.html");
		

	}
	
}
