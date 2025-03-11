package store.model;

import java.util.ArrayList;
import java.util.List;

import store.controller.backend.CustomerController;
import store.controller.backend.ProductInOrderController;

public class Order {
	private int id;
	private int customerId;
	private String code;
	public void display() {
		//System.out.println("\nID:"+this.id);
		System.out.println("\nTên khách hàng: "+CustomerController.getNameById(customerId));
		System.out.println("Số điện thoại khách hàng: "+CustomerController.getPhoneNumberById(customerId));
		System.out.println("Mã đơn hàng: "+this.code);
		List<ProductInOrder> productInOrders = ProductInOrderController.getListProductByIdOrder(id);
		System.out.printf("%-20s %-8s %-15s %-15s \n","Tên sản phẩm","Số lượng","Đơn giá","Thành tiền");
		for(ProductInOrder productInOrder : productInOrders) {
			productInOrder.display();
		}
		System.out.printf("Tổng giá trị đơn hàng: %.2f\n",totalOrderPrice());
		
	}
	public double totalOrderPrice() {
		double total = 0;
		List<ProductInOrder> productInOrders = ProductInOrderController.getListProductByIdOrder(id);
		for (ProductInOrder productInOrder : productInOrders) {
			
			total += productInOrder.getQuantity() * 
					productInOrder.getProductByProductId().getPrice();
		}
		return total;
	}
	public Order() {
		super();
	}
	public Order(int id, int customerId, String code) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
}
