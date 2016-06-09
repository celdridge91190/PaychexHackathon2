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
	public List<Toppings> getAllToppings(Connection conn) throws SQLException {
		List<Toppings> toppings = new ArrayList<Toppings>();

		String getAllToppingsSql = "SELECT topping_id, topping_name from Hack2.topping";
		PreparedStatement getAllToppingsStatement = null;
		try {
			getAllToppingsStatement = conn.prepareStatement(getAllToppingsSql);
	
			ResultSet results = getAllToppingsStatement.executeQuery();
			while (results.next()) {
				Toppings topping = new Toppings();
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
	public List<Toppings> getToppingsForPizza(Connection conn, Pizza pizza) throws SQLException {
		List<Toppings> toppings = new ArrayList<Toppings>();
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
				Toppings topping = new Toppings();
				topping.setTopping_id(results.getInt("TOPPING_ID"));
				topping.setTopping_name(results.getString("TOPPING_NAME"));
			}
		} finally {
			if (toppingsForPizzaStatement != null && !toppingsForPizzaStatement.isClosed()){
				toppingsForPizzaStatement.close();
			}
		}
		return toppings;
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

}
