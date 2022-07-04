package com.deep.rooted.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/**
 * supply and demand bean  
 * @author Dhamodiran D
 *
 */
public class SupplyAndDemand {
	
	String orerId;
	String time;
	String product;
	Integer price;
	Integer quantity;
	
	public SupplyAndDemand(String str) {
		
		//parsing string into SupplyAndDemand members
		List<String> inputList = Arrays.asList(str.split(" "));
		
		this.orerId = inputList.get(0);
		this.time = inputList.get(1);
		this.product = inputList.get(2);
		this.price	= Integer.valueOf(inputList.get(3).replaceAll("\\D+", ""));
		this.quantity = Integer.valueOf(inputList.get(4).replaceAll("\\D+", ""));
		
	}
	
	public String getOrerId() {
		return orerId;
	}
	public void setOrerId(String orerId) {
		this.orerId = orerId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(orerId, product, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplyAndDemand other = (SupplyAndDemand) obj;
		return Objects.equals(orerId, other.orerId) && Objects.equals(product, other.product)
				&& Objects.equals(time, other.time);
	}
	@Override
	public String toString() {
		return "SupplyAndDemand [orerId=" + orerId + ", time=" + time + ", product=" + product + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
}
