package com.paychex.corp.hackpizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaDao {
	
	/**
	 * Retrieves all pizzas from the Hack2.pizza table
	 * @param conn connection to the database
	 * @return List of pizzas in the pizza table
	 * @throws SQLException
	 */
	public List<Pizza> getAllPizzas(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		List<Pizza> pizzas = new ArrayList<Pizza>();
		String getAllPizzasSql = "SELECT pizza_id, pizza_name from Hack2.pizza";
		PreparedStatement getAllPizzasStatement = null;
		try {
			getAllPizzasStatement = conn.prepareStatement(getAllPizzasSql);
			ResultSet results = getAllPizzasStatement.executeQuery();
			while (results.next()) {
				Pizza pizza = new Pizza();
				pizza.setPizza_id(results.getInt("PIZZA_ID"));
				pizza.setPizza_name(results.getString("PIZZA_NAME"));
				pizzas.add(pizza);
			}
		} finally {
			if (getAllPizzasStatement != null && !getAllPizzasStatement.isClosed()){
				getAllPizzasStatement.close();
			}
		}
		return pizzas;
	}
	
	/**
	 * 
	 * @param conn Connection to the database
	 * @return List of all toppings in Hack2.topping
	 * @throws SQLException
	 */
	public List<Topping> getAllToppings(Connection conn) throws SQLException {
		List<Topping> toppings = new ArrayList<Topping>();

		String getAllToppingsSql = "SELECT topping_id, topping_name from Hack2.topping";
		PreparedStatement getAllToppingsStatement = null;
		try {
			getAllToppingsStatement = conn.prepareStatement(getAllToppingsSql);
	
			ResultSet results = getAllToppingsStatement.executeQuery();
			while (results.next()) {
				Topping topping = new Topping();
				topping.setTopping_id(results.getInt("TOPPING_ID"));
				topping.setTopping_name(results.getString("TOPPING_NAME"));
			}
		} finally {
			if (getAllToppingsStatement != null && !getAllToppingsStatement.isClosed()){
				getAllToppingsStatement.close();
			}
		}
		return toppings;
	}
	
	/**
	 * 
	 * @param conn Connection to the database
	 * @param pizza Pizza to find toppings for
	 * @return All toppings for the Pizza passed in
	 * @throws SQLException
	 */
	public List<Topping> getToppingsForPizza(Connection conn, Pizza pizza) throws SQLException {
		List<Topping> toppings = new ArrayList<Topping>();
		String toppingsForPizzaSql = "select topping.topping_id, topping.topping_name "
				+ "from Hack2.topping topping join Hack2.pizzaToppingMap ptMap "
				+ "on (ptMap.topping_id = topping.topping_id) join Hack2.pizza pizza "
				+ "on (pizza.pizza_id = ptMap.pizza_id) where pizza.pizza_id = ?";
		PreparedStatement toppingsForPizzaStatement = null;
		try {
			toppingsForPizzaStatement = conn.prepareStatement(toppingsForPizzaSql);
			toppingsForPizzaStatement.setInt(1, pizza.getPizza_id());
			ResultSet results = toppingsForPizzaStatement.executeQuery();
			while (results.next()){
				Topping topping = new Topping();
				topping.setTopping_id(results.getInt("TOPPING_ID"));
				topping.setTopping_name(results.getString("TOPPING_NAME"));
				toppings.add(topping);
			}
		} finally {
			if (toppingsForPizzaStatement != null && !toppingsForPizzaStatement.isClosed()){
				toppingsForPizzaStatement.close();
			}
		}
		return toppings;
	}
	
	/**
	 * Get a specific pizza from the database based on name
	 * @param conn connection to the database
	 * @param pizzaName pizza name to find
	 * @return pizza from the database
	 * @throws SQLException
	 */
	public Pizza getPizza(Connection conn, String pizzaName) throws SQLException {
		Pizza pizza = new Pizza();
		String pizzaSql = "Select pizza_id, pizza_name from Hack2.pizza where pizza_name = ?";
		PreparedStatement pizzaStatement = null;
		try {
			pizzaStatement = conn.prepareStatement(pizzaSql);

			pizzaStatement.setString(1, pizzaName);
			pizzaStatement.setMaxRows(1);
			ResultSet results = pizzaStatement.executeQuery();
			if (results.next()) {
				pizza.setPizza_id(results.getInt("PIZZA_ID"));
				pizza.setPizza_name(results.getString("PIZZA_NAME"));
			}
		} finally {
			if (pizzaStatement != null & !pizzaStatement.isClosed()){
				pizzaStatement.close();
			}
		}
		return pizza;
	}
	
	
	/**
	 * Get all pizza names from pizza table
	 * @param conn connection to the database
	 * @return all pizza names in pizza table
	 * @throws SQLException
	 */
	public List<String> getPizzaNames(Connection conn) throws SQLException {
		List<String> pizzaNames = new ArrayList<String>();
		String pizzaNamesSql = "Select pizza_name from Hack2.pizza";
		PreparedStatement pizzaNameStatement = null;
		try {
			pizzaNameStatement = conn.prepareStatement(pizzaNamesSql);
			ResultSet results = pizzaNameStatement.executeQuery();
			while (results.next()){
				String pizzaName = results.getString("PIZZA_NAME");
				pizzaNames.add(pizzaName);
			}
		} finally {
			if (pizzaNameStatement != null && !pizzaNameStatement.isClosed()){
				pizzaNameStatement.close();
			}
		}
		return pizzaNames;
	}
	
	/**
	 * Get all topping names from topping table
	 * @param conn connection to the database
	 * @return all topping names in topping table
	 * @throws SQLException
	 */
	public List<String> getToppingNames(Connection conn) throws SQLException {
		List<String> toppingNames = new ArrayList<String>();
		String toppingNamesSql = "Select topping_name from Hack2.topping";
		PreparedStatement toppingNameStatement = null;
		try {
			toppingNameStatement = conn.prepareStatement(toppingNamesSql);
			ResultSet results = toppingNameStatement.executeQuery();
			while (results.next()){
				String toppingName = results.getString("TOPPING_NAME");
				toppingNames.add(toppingName);
			}
		} finally {
			if (toppingNameStatement != null && !toppingNameStatement.isClosed()){
				toppingNameStatement.close();
			}
		}
		return toppingNames;
	}
	
	
	/**
	 * Retreives all mappings from the Hack2.pizzaToppingMap table
	 * @param conn Connection to the database
	 * @return all mappings in Hack2.pizzaToppingMap
	 * @throws SQLException
	 */
	public Map<String, String> getMappings(Connection conn) throws SQLException {
		Map<String, String> mapping = new HashMap<String, String>();
		String getAllMappingsSQL = "SELECT pizza_id, topping_id from Hack2.pizzaToppingMap";
		PreparedStatement getAllMappingsStatement = null;
		try {
			getAllMappingsStatement = conn.prepareStatement(getAllMappingsSQL);

			ResultSet results = getAllMappingsStatement.executeQuery();
			while (results.next()) {
				String pizza_id = results.getString("PIZZA_ID");
				String topping_id = results.getString("TOPPING_ID");
				mapping.put(pizza_id, topping_id);
			}
		} finally {
			if (getAllMappingsStatement != null && !getAllMappingsStatement.isClosed()){
				getAllMappingsStatement.close();
			}
		}
		return mapping;
	}
	
	public void addPizza(Connection conn, String pizzaName) throws SQLException {
		String pizzaSql = "Insert into Hack2.pizza (pizza_name) value(?)";
		PreparedStatement pizzaStatement = null;
		try {
			pizzaStatement = conn.prepareStatement(pizzaSql);
			pizzaStatement.setString(1, pizzaName);
			pizzaStatement.execute();
		} finally {
			if (pizzaStatement != null & !pizzaStatement.isClosed()){
				pizzaStatement.close();
			}
		}
	}
	
	public void addTopping(Connection conn, String toppingName) throws SQLException {
		String toppingSql = "Insert into Hack2.topping (topping_name) value(?)";
		PreparedStatement toppingStatement = null;
		try {
			toppingStatement = conn.prepareStatement(toppingSql);
			toppingStatement.setString(1, toppingName);
			toppingStatement.execute();
		} finally {
			if (toppingStatement != null & !toppingStatement.isClosed()){
				toppingStatement.close();
			}
		}
	}
	
	public void addToppingToPizza(Connection conn, String toppingName, String pizzaName) throws SQLException {
		String toppingIdSql = "Select topping_id from Hack2.topping where topping_name = ?";
		String pizzaIdSql = "Select pizza_id from Hack2.pizza where pizza_name = ?";
		String insertMappingSql = "Insert into Hack2.pizzaToppingMap (pizza_id, topping_id) value(?, ?)";
		PreparedStatement pizzaIdStatement = null;
		PreparedStatement toppingIdStatement = null;
		PreparedStatement insertMappingStatement = null;
		try {
			int pizzaId = 0;
			int toppingId = 0;
			pizzaIdStatement = conn.prepareStatement(pizzaIdSql);
			pizzaIdStatement.setString(1,  pizzaName);
			pizzaIdStatement.setMaxRows(1);
			ResultSet results = pizzaIdStatement.executeQuery();
			if (results.next()){
				pizzaId = results.getInt("PIZZA_ID");
			}
			
			toppingIdStatement = conn.prepareStatement(toppingIdSql);
			toppingIdStatement.setString(1, toppingName);
			toppingIdStatement.setMaxRows(1);
			ResultSet toppingResults = toppingIdStatement.executeQuery();
			if (toppingResults.next()){
				toppingId = toppingResults.getInt("TOPPING_ID");
			}
			
			insertMappingStatement = conn.prepareStatement(insertMappingSql);
			insertMappingStatement.setInt(1, pizzaId);
			insertMappingStatement.setInt(2, toppingId);
			insertMappingStatement.execute();
		} finally {
			if (pizzaIdStatement != null & !pizzaIdStatement.isClosed()){
				pizzaIdStatement.close();
			}
			if (insertMappingStatement != null & !insertMappingStatement.isClosed()){
				insertMappingStatement.close();
			}
		}
	}
	
	

}
