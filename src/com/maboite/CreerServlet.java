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

@SuppressWarnings("serial")
public class CreerServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity personne = new Entity("Etudiant");
		String nom = req.getParameter("nom");
		String n = req.getParameter("numero");
		Integer numero = Integer.parseInt(n);
		personne.setProperty("nom", nom);
		personne.setProperty("numero", numero);
		Transaction tx = datastore.beginTransaction();
		datastore.put(personne);
		tx.commit();
		
		resp.sendRedirect("/recherche?nom="+nom);
		
	}
	
}
