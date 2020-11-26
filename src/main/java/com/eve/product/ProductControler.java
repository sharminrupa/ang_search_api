package com.eve.product;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.eve.HibernateUtil;

@Path("/product")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductControler {

	SessionFactory sessionFactory;
	Transaction transaction;

	@Path("/list")
	@GET
	public Response getProductList(@QueryParam("keyword") String keyword) {

		try {

			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			List<Product> pList = session.createQuery("FROM Product where name like :name")
					.setParameter("name", "%" + keyword + "%").list();
			return Response.status(200).entity(pList).build();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}