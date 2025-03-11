package store.controller.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import store.model.Customer;
import store.model.Order;
import store.model.ProductInOrder;

public class OrderController {
	public static int autoId=1;
	public static void init() {
		orders.add(new Order(autoId++,1,"10001"));
		orders.add(new Order(autoId++,2,"10002"));
		orders.add(new Order(autoId++,3,"10003"));
		orders.add(new Order(autoId++,4,"10004"));
	}
	public static int getAutoId() {
		return autoId;
	}

	public static void setAutoId(int autoId) {
		OrderController.autoId = autoId;
	}

	private static List<Order> orders = new ArrayList<Order>();

	public static List<Order> getOrders() {
		return orders;
	}

	public static void setOrders(List<Order> orders) {
		OrderController.orders = orders;
	}
	public static void  menuManageOrdersAndRevenue() {
		 Scanner sc = new Scanner(System.in);
		 do {
	   	 System.out.println("\n ------Quản lý đơn hàng và doanh thu-----");
	   	 System.out.println("Chon chuc nang");
	        System.out.println("1. Hiển thị danh sách các hóa đơn(đơn hàng)");
	        System.out.println("2. Xóa 1 đơn hàng khỏi danh sách");
	        System.out.println("3. Hiển thị tổng doanh thu có được từ tất cả các hóa đơn");
	        System.out.println("4. Hiển thị tổng số tiền thu được theo khách hàng");
	        System.out.println("5. Hiển thị tổng số tiền thu được theo sản phẩm đã bán");
	        System.out.println("0. Quay lại");
	        System.out.print("Nhập lựa chọn: ");
	       int choose = Integer.parseInt(sc.nextLine());

	       switch (choose) {
	           case 1: {
	        	   display();
	               break;
	           }
	           case 2: {
	        	   remove();
	           	break;
	           }
	           case 3: {
	        	   totalAllPrice();
	           	break;
	           }
	           case 4: {
	        	   totalPriceByCustomer();
	        	   break;
	           }
	           case 5: {
	        	   totalPriceByProduct();
	        	   break;
	           }   
	           case 0: {
	              
	               return;
	           }
	           default: {
	               System.out.println("Không có lựa chọn nào phù hợp.");
	           }
	       }
	   } while (true);
	 }
	static Scanner sc = new Scanner(System.in);
	public static int indexOfCode(String code) {
		for(int i=0;i< orders.size();i++) {
			if(orders.get(i).getCode().equals(code)) return i;
		}
		return -1;
	}
	private static void remove() {
		System.out.println("Nhập mã đơn hàng cần xóa");
		String code = sc.nextLine();
		int index = indexOfCode(code);
		if(index==-1) {
			System.out.println("Đơn hàng không tồn tại");
			return;
		}
		int idOrder = orders.get(index).getId();
		// xóa dữ liệu trong bảng product_in_order
		for(int i =0;i<ProductInOrderController.getInOrders().size();i++) {
			if(ProductInOrderController.getInOrders().get(i).getId() == idOrder) ProductInOrderController.getInOrders().remove(i);
		}
		orders.remove(index);
		System.out.println("Đã xóa");
	}
	public static void display() {
		for(Order o : orders) {
			o.display();
		}
	}
	public static void totalAllPrice() {
		double sum=0;
		for(Order o : orders) {
			sum += o.totalOrderPrice();
		}
		System.out.printf("Tổng tiền tất cả các đơn hàng: %.2f",sum);
	}
	
	public static void totalPriceByCustomer() {
	    ArrayList<Integer> idCustomers = new ArrayList<Integer>();
	    List<ProductInOrder> productsInOrders = ProductInOrderController.getInOrders(); // Lấy danh sách sản phẩm trong đơn hàng
	    List<Order> orders = OrderController.getOrders(); // Lấy danh sách đơn hàng

	    // Loại bỏ trùng lặp ID khách hàng
	    for (ProductInOrder pio : productsInOrders) {
	        if (!idCustomers.contains(pio.getCustomerByProductId().getId())) {
	            idCustomers.add(pio.getCustomerByProductId().getId());
	        }
	    }

	    System.out.printf("%3s %-30s %-15s\n", "STT", "Tên khách hàng", "Thành tiền");

	    for (int j = 0; j < idCustomers.size(); j++) {
	        double totalPrice = 0;
	        for (Order order : orders) {
	            if (order.getCustomerId() == idCustomers.get(j)) {
	                totalPrice += order.totalOrderPrice();
	            }
	        }
	        System.out.printf("%3d %-30s %-15.2f\n", j + 1, CustomerController.getNameById(idCustomers.get(j)), totalPrice);
	    }
	}


	public static void totalPriceByProduct() {
	    System.out.printf("%3s %-30s %-15s %-15s\n", "STT", "Tên sản phẩm", "Số lượng đã bán", "Doanh thu");

	    List<ProductInOrder> productsInOrders = ProductInOrderController.getInOrders();
	    ArrayList<Integer> productIds = new ArrayList<>();
	    // Kiểm tra xem có bn productId trong mảng(loại bỏ các id trùng lặp)
	    for (ProductInOrder pio : productsInOrders) {
	        if (!productIds.contains(pio.getProductId())) {
	            productIds.add(pio.getProductId());
	        }
	    }

	    for (int i = 0; i < productIds.size(); i++) {
	        int productId = productIds.get(i);
	        int totalQuantity = 0;
	        double totalRevenue = 0;

	        for (ProductInOrder pio : productsInOrders) {
	            if (pio.getProductId() == productId) {
	            	 totalQuantity += pio.getQuantity();  
	                 totalRevenue += pio.getQuantity() * ProductController.getProductById(productId).getPrice();  
	            }
	        }

	        System.out.printf("%3d %-30s %-15d %-15.2f\n", i + 1, ProductController.getProductById(productId).getName(), totalQuantity, totalRevenue);
	    }
	}

}
