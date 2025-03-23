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
	public static void menuManageOrdersAndRevenue() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n ------QUAN LY DON HANG VA DOANH THU-----");
			System.out.println("Chon chuc nang");
			System.out.println("1. Hien thi danh sach cac hoa don (don hang)");
			System.out.println("2. Xoa 1 don hang khoi danh sach");
			System.out.println("3. Hien thi tong doanh thu co duoc tu tat ca cac hoa don");
			System.out.println("4. Hien thi tong so tien thu duoc theo khach hang");
			System.out.println("5. Hien thi tong so tien thu duoc theo san pham da ban");
			System.out.println("0. Quay lai");
			System.out.print("Nhap lua chon: ");
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
					System.out.println("Khong co lua chon nao phu hop.");
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
		System.out.println("Nhap ma don hang can xoa");
		String code = sc.nextLine();
		int index = indexOfCode(code);
		if(index==-1) {
			System.out.println("Don hang khong ton tai");
			return;
		}
		int idOrder = orders.get(index).getId();
		// xoa du lieu trong bang product_in_order
		for(int i =0;i<ProductInOrderController.getInOrders().size();i++) {
			if(ProductInOrderController.getInOrders().get(i).getId() == idOrder) 
				ProductInOrderController.getInOrders().remove(i);
		}
		orders.remove(index);
		System.out.println("Da xoa");
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
		System.out.printf("Tong tien tat ca cac don hang: %.2f",sum);
	}
	
	public static void totalPriceByCustomer() {
		ArrayList<Integer> idCustomers = new ArrayList<Integer>();
		List<ProductInOrder> productsInOrders = ProductInOrderController.getInOrders();
		List<Order> orders = OrderController.getOrders();

		for (ProductInOrder pio : productsInOrders) {
			if (!idCustomers.contains(pio.getCustomerByProductId().getId())) {
				idCustomers.add(pio.getCustomerByProductId().getId());
			}
		}

		System.out.printf("%3s %-30s %-15s\n", "STT", "Ten khach hang", "Thanh tien");

		for (int j = 0; j < idCustomers.size(); j++) {
			double totalPrice = 0;
			for (Order order : orders) {
				if (order.getCustomerId() == idCustomers.get(j)) {
					totalPrice += order.totalOrderPrice();
				}
			}
			System.out.printf("%3d %-30s %-15.2f\n", j + 1, 
				CustomerController.getNameById(idCustomers.get(j)), totalPrice);
		}
	}

	public static void totalPriceByProduct() {
		System.out.printf("%3s %-30s %-15s %-15s\n", "STT", "Ten san pham", 
			"So luong da ban", "Doanh thu");

		List<ProductInOrder> productsInOrders = ProductInOrderController.getInOrders();
		ArrayList<Integer> productIds = new ArrayList<>();

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
					totalRevenue += pio.getQuantity() * 
						ProductController.getProductById(productId).getPrice();  
				}
			}

			System.out.printf("%3d %-30s %-15d %-15.2f\n", i + 1, 
				ProductController.getProductById(productId).getName(), 
				totalQuantity, totalRevenue);
		}
	}

}
