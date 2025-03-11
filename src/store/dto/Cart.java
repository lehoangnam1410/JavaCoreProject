package store.dto;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private int id;
	private int customerId;
	private List<ProductCart> productCarts = new ArrayList<>();
	public int findProductCartById(int productId) {
		for (int index = 0; index < productCarts.size(); index++) {
			if (productCarts.get(index).getProductId() == productId) {
				return index;
			}
		}
		return -1;
	}
	
	
	public int totalCartProducts() {
		int total = 0;
		for (ProductCart productCart : productCarts) {
			total += productCart.getQuantity();
		}
		return total;
	}
	
	public double totalCartPrice() {
		double total = 0;
		for (ProductCart productCart : productCarts) {
			
			total += productCart.getQuantity() * 
					productCart.getProductByProductId().getPrice();
		}
		return total;
	}
	public Cart(int id, int customerId, List<ProductCart> productCarts) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.productCarts = productCarts;
	}

	public Cart() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<ProductCart> getProductCarts() {
		return productCarts;
	}

	public void setProductCarts(List<ProductCart> productCarts) {
		this.productCarts = productCarts;
	}
}
