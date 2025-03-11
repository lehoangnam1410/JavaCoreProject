package store.model;

import store.controller.backend.CustomerController;
import store.controller.backend.ProductController;

public class ProductInOrder {
 private int id;
 private int productId;
 private int orderId;
 private int quantity;
 public void display() {
		Product product = ProductController.getProductById(this.productId);
		System.out.printf("%-20s %-8d %-,15.2f %-,15.2f%n", product.getName(),
				this.quantity, product.getPrice(),
				this.quantity * product.getPrice());
	}
 public Product getProductByProductId() {
		for (Product product : ProductController.getProducts()) {
			if (product.getId() == this.productId) {
				return product;
			}
		}
		return new Product();
	}
 public Customer getCustomerByProductId() {
	 for (Customer customer : CustomerController.getCustomers()) {
			if (customer.getId() == this.productId) {
				return customer;
			}
		}
		return new Customer();
 }
 public ProductInOrder() {
	super();
 }

public ProductInOrder(int id, int productId, int orderId, int quantity) {
	super();
	this.id = id;
	this.productId = productId;
	this.orderId = orderId;
	this.quantity = quantity;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
}

public int getOrderId() {
	return orderId;
}

public void setOrderId(int orderId) {
	this.orderId = orderId;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}
 
}
