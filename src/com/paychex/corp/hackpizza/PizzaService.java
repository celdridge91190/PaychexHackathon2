package com.paychex.corp.hackpizza;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/PizzaService")
public class PizzaService {
	private PizzaDao pizzaDao = new PizzaDao();

	@GET
	@Path("/pizza")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzas() throws Exception {
		InitialContext ctx;
		Connection conn = null;
		List<Pizza> pizzas = new ArrayList<Pizza>();
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/HackPizza");
			conn = ds.getConnection();
			// Retrieve all pizza objects from database
			List<Pizza> allPizzas = pizzaDao.getAllPizzas(conn);
			// For each pizza, find the toppings that belong to that pizza and add them to the
			// pojo
			for (Pizza pizza : allPizzas){
				List<Topping> toppings = pizzaDao.getToppingsForPizza(conn, pizza);
				pizza.setToppings(toppings);
				pizzas.add(pizza);
			}
			
		} finally {
			if (conn != null && !conn.isClosed()){
				conn.close();
			}
		}

		return pizzas;
	}
	
	@GET
	@Path("/pizzaByName/{pizzaName}")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Pizza getPizza(@PathParam("pizzaName") String pizzaName) throws Exception {
		InitialContext ctx;
		Connection conn = null;
		Pizza pizza = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/HackPizza");
			conn = ds.getConnection();
			// Retrieve pizza object from database
			pizza = pizzaDao.getPizza(conn, pizzaName);
			// Get toppings for pizza
			List<Topping> toppings = pizzaDao.getToppingsForPizza(conn, pizza);
			pizza.setToppings(toppings);
		} finally {
			if (conn != null && !conn.isClosed()){
				conn.close();
			}
		}
		return pizza;
	}
	
	@GET
	@Path("/pizzaNames")
	// @Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getPizzaNames() throws Exception {
		InitialContext ctx;
		Connection conn = null;
		List<String> pizzaNames = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/HackPizza");
			conn = ds.getConnection();
			// Retrieve all pizza objects from database
			pizzaNames = pizzaDao.getPizzaNames(conn);
			// For each pizza, find the toppings that belong to that pizza and add them to the
			// pojo
			
			
		} finally {
			if (conn != null && !conn.isClosed()){
				conn.close();
			}
		}

		return pizzaNames;
	}

}
