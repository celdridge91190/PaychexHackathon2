package com.paychex.corp.hackpizza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Pizza")
public class Pizza implements Serializable {
	private int pizza_id;
	private String pizza_name;
	private List<Toppings> toppings = new ArrayList<Toppings>();
	
	public Pizza() {
		
	}
	
	@XmlElement
	/**
	 * @param pizza_id the pizza_id to set
	 */
	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}
	/**
	 * @return the pizza_id
	 */
	public int getPizza_id() {
		return pizza_id;
	}
	
	@XmlElement
	/**
	 * @param pizza_name the pizza_name to set
	 */
	public void setPizza_name(String pizza_name) {
		this.pizza_name = pizza_name;
	}
	/**
	 * @return the pizza_name
	 */
	public String getPizza_name() {
		return pizza_name;
	}
	
	@XmlElement
	/**
	 * @param toppings the toppings to set
	 */
	public void setToppings(List<Toppings> toppings) {
		this.toppings = toppings;
	}
	/**
	 * @return the toppings
	 */
	public List<Toppings> getToppings() {
		return toppings;
	}
}
