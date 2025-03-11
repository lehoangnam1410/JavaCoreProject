package store.dto;

import store.controller.backend.ProductController;
import store.model.Product;

public class ProductCart {
	private int productId;
	private int quantity;
	
	//Phuong thuc hien thi san pham trong gio hang
	
	public Product getProductByProductId() {
		for (Product product : ProductController.getProducts()) {
			if (product.getId() == this.productId) {
				return product;
			}
		}
		return new Product();
	}
	
	public void display() {
		Product product = getProductByProductId();
		System.out.printf("%-30s %8d %,15.2f %,15.2f%n", product.getName(),
				this.quantity, product.getPrice(),
				this.quantity * product.getPrice());
	}
	public double amount() {
		Product x = ProductController.getProductById(this.productId);
		return this.quantity * x.getPrice();
	}
	
	
	public ProductCart() {
		super();
	}
	public ProductCart(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
