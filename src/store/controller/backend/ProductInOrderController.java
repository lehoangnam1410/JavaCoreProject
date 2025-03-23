package store.controller.backend;

import java.util.ArrayList;
import java.util.List;

import store.model.ProductInOrder;

public class ProductInOrderController {
	private static List<ProductInOrder> inOrders = new ArrayList<ProductInOrder>();

	public static List<ProductInOrder> getInOrders() {
		return inOrders;
	}

	public static void setInOrders(List<ProductInOrder> inOrders) {
		ProductInOrderController.inOrders = inOrders;
	}
	public static int autoId = 1;
	public static void init() {
		inOrders.add(new ProductInOrder(autoId++,1,1,100));
		inOrders.add(new ProductInOrder(autoId++,2,1,100));
		inOrders.add(new ProductInOrder(autoId++,3,2,100));
		inOrders.add(new ProductInOrder(autoId++,4,2,100));
		inOrders.add(new ProductInOrder(autoId++,4,3,100));
	}
	public static int getAutoId() {
		return autoId;
	}

	public static void setAutoId(int autoId) {
		ProductInOrderController.autoId = autoId;
	}
	public static ArrayList<ProductInOrder> getListProductByIdOrder(int iD) {
		ArrayList<ProductInOrder>productInOrders = new ArrayList<ProductInOrder>();
		for(ProductInOrder productInOrder : inOrders) {
			if(productInOrder.getId()==iD) productInOrders.add(productInOrder);
		}
		return productInOrders;
	}
	
}
