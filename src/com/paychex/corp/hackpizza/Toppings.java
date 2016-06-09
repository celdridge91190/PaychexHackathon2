package com.paychex.corp.hackpizza;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Topping")
public class Toppings {
	private int topping_id;
	private String topping_name;

	public Toppings() {
		
	}

	@XmlElement
	/**
	 * @param topping_id the topping_id to set
	 */
	public void setTopping_id(int topping_id) {
		this.topping_id = topping_id;
	}

	/**
	 * @return the topping_id
	 */
	public int getTopping_id() {
		return topping_id;
	}
	@XmlElement
	/**
	 * @param topping_name the topping_name to set
	 */
	public void setTopping_name(String topping_name) {
		this.topping_name = topping_name;
	}

	/**
	 * @return the topping_name
	 */
	public String getTopping_name() {
		return topping_name;
	}
}
