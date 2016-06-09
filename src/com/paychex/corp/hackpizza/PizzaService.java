package com.paychex.corp.hackpizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/PizzaService")
public class PizzaService {
	private PizzaDao pizzaDao = new PizzaDao();

	@GET
	@Path("/pizza")
	//@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzas() throws Exception {
		InitialContext ctx;
		List<Pizza> pizzas = new ArrayList<Pizza>();
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("jdbc/HackPizza");
			Connection conn = ds.getConnection();
			String getAllPizzasSql = "Select pizza_id, pizza_name from Hack2.pizza";
			PreparedStatement getAllPizzasStatement = conn.prepareStatement(getAllPizzasSql);
			ResultSet results = getAllPizzasStatement.executeQuery();
			while (results.next()){
				Pizza pizza = new Pizza();
				pizza.setPizza_id(results.getString("PIZZA_ID"));
				pizza.setPizza_name(results.getString("PIZZA_NAME"));
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return pizzas;
	}

}
