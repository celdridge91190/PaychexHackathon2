package com.paychex.corp.hackpizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaDao {

	public List<Pizza> getAllPizzas() {
		// TODO Auto-generated method stub
		Pizza pusPizza = new Pizza();
		pusPizza.setPizza_id("123");
		pusPizza.setPizza_name("Pepperoni Pizza");
		
		Toppings topping = new Toppings();
		topping.setTopping_id("123");
		topping.setTopping_name("Pepperoni");
		pusPizza.getToppings().add(topping);
		
		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas.add(pusPizza);
		return pizzas;
	}

}
