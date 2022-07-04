package com.deep.rooted.beans;

public class Trade {

	String demandOrderId;
	String supplyOrderId;
	String product;
	String price;
	String quantity;

	public Trade() {

	}

	public Trade(String demandOrderId, String supplyOrderId, String product, String price, String quantity) {
		super();
		this.demandOrderId = demandOrderId;
		this.supplyOrderId = supplyOrderId;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}

	public String getDemandOrderId() {
		return demandOrderId;
	}

	public void setDemandOrderId(String demandOrderId) {
		this.demandOrderId = demandOrderId;
	}

	public String getSupplyOrderId() {
		return supplyOrderId;
	}

	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return demandOrderId + " " + supplyOrderId + " " + price + "/kg " + quantity + "kg\n";
	}

}
