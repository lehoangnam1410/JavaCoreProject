package store.controller.frontend;

import java.util.Scanner;

import store.controller.backend.CustomerController;
import store.controller.backend.OrderController;
import store.controller.backend.ProductController;
import store.controller.backend.ProductInOrderController;
import store.dto.Cart;
import store.dto.ProductCart;
import store.model.Customer;
import store.model.Order;
import store.model.ProductInOrder;


public class CartController {
	static Cart cart = new Cart();
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n\t\tCAC THAO TAC VOI GIO HANG");
			System.out.println("Moi quy khach chon thao tac");
			System.out.println("\t1. Xem gio hang");
			System.out.println("\t2. Them hang vao gio");
			System.out.println("\t3. Xoa hang khoi gio");
			System.out.println("\t4. Sua so luong hang trong gio");
			System.out.println("\t5. Dat hang");
			System.out.println("\t0. Quay lai");

			System.out.print("Lua chon cua ban?: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				display(cart);
				break;
			case 2:
				add(cart);
				break;
			case 3:
				remove(cart);
				break;
			case 4:
				edit(cart);
				break;
			case 5:
				order(cart);
				cart= new Cart();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);

	}
	public static void display(Cart cart) {
		System.out.println("\n\t\tGIO HANG CUA BAN");
		String customerName = CustomerController.getNameById(cart.getCustomerId());
		System.out.println("\tTen khach hang: " + customerName);
		String phoneNumber = CustomerController.getPhoneNumberById(cart.getCustomerId());
		System.out.println("\tSo dien thoai khach hang: " + phoneNumber);
		if (cart.totalCartProducts() <= 0) {
			System.out.println("\tGio hang cua ban chua co san pham nao");
			return;
		}
		System.out.println("\tTong so san pham: " + cart.totalCartProducts());
		System.out.println("Danh sach san pham");
		System.out.printf("%-30s %-8s %-15s %-15s%n", "Ten san pham", "So luong", "Don gia", "Thanh tien");
		for (ProductCart productCart : cart.getProductCarts()) {
			productCart.display();
		}
		System.out.printf("\tCong thanh tien: %,15.2f%n", cart.totalCartPrice());
	}

	static Scanner sc = new Scanner(System.in);

	public static void add(Cart cart) {

		System.out.println("\n\t\tTHEM HANG VAO GIO");
		System.out.print("Chon san pham can mua (nhap ma sp): ");
		String code = sc.nextLine();

		int index = ProductController.findProductByCode(code);
		if (index == -1) {
			System.out.println("San pham khong ton tai");
			return;
		}

		System.out.print("Nhap so luong can mua: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity <= 0) {
			System.out.println("So luong khong hop le");
			return;
		}


		int productId = ProductController.getProducts().get(index).getId();


		int cartIndex = cart.findProductCartById(productId);
		if (cartIndex == -1) {
			cart.getProductCarts().add(new ProductCart(productId, quantity));
		} else {
			cart.getProductCarts().get(cartIndex)
					.setQuantity(quantity + cart.getProductCarts().get(cartIndex).getQuantity());
		}
		System.out.println("Them san pham vao gio thanh cong!");
	}

	private static void remove(Cart cart) {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tXOA HANG KHOI GIO");
		System.out.print("Chon san pham can xoa (nhap ma sp): ");
		String code = sc.nextLine();
		// Tim xem sp co trong ds sp khong?
		int index = ProductController.findProductByCode(code);
		if (index == -1) {
			System.out.println("San pham khong ton tai");
			return;
		}
		int productId = ProductController.getProducts().get(index).getId();
		// kiem tra san pham co ton tai
		int cartIndex = cart.findProductCartById(productId);
		if(cartIndex==-1) {
			System.out.println("San pham khong ton tai trong gio hang");
			return;
		}
		cart.getProductCarts().remove(cartIndex);
		System.out.println("\tXoa san pham thanh cong");

	}

	private static void edit(Cart cart) {
		// TODO Auto-generated method stub
		System.out.println("\n\t\tSUA SO LUONG HANG TRONG GIO");
		System.out.print("Chon san pham can sua (nhap ma sp): ");
		String code = sc.nextLine();
		// Tim xem sp co trong ds sp khong?
		int index = ProductController.findProductByCode(code);
		if (index == -1) {
			System.out.println("San pham khong ton tai");
			return;
		}
		int productId = ProductController.getProducts().get(index).getId();
		// kiem tra san pham co ton tai
		int cartIndex = cart.findProductCartById(productId);
		if(cartIndex==-1) {
			System.out.println("San pham khong ton tai trong gio hang");
			return;
		}
		System.out.print("Nhap so luong san pham: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity < 0) {
			System.out.println("So luong khong duoc la so am!");
			return;
		}
		cart.getProductCarts().get(cartIndex).setQuantity(quantity);
		System.out.println("Sua so luong hang thanh cong");
	}
	private static void order(Cart cart) {
		// TODO Auto-generated method stub
		if (cart.getProductCarts().size() == 0) {
			System.out.println("Chua co hang trong gio!");
			return;
		}
		System.out.println("\n-----THANH TOAN GIO HANG-----");
		System.out.print("\tNhap ma khach hang:");
		String codeCustomer = sc.nextLine();
		int index1 = CustomerController.findIndexCustomerByCode(codeCustomer);
		if(index1 != -1) {
			cart.setCustomerId(CustomerController.getCustomers().get(index1).getId());
		}else {
			String name;
			String phoneNumber;
			do {
				System.out.print("Nhap ten khach hang: ");
				name = sc.nextLine();
				if (name == null || name.isEmpty()) {
					System.out.println("Ten KH khong duoc de trong");
					return;
				}
				System.out.print("Nhap so dien thoai khach hang: ");
				phoneNumber = sc.nextLine();
				if (!phoneNumber.matches("[0-9]{8,15}")) {
			        System.out.println("So dien thoai chi duoc chua so va co do dai tu 8 den 15 ky tu");
			        return;
			    }
			}
			while(name == null || name.isEmpty());
			int cusId = CustomerController.getAutoId();
			Customer customer = new Customer(cusId,"1000"+cusId,name,phoneNumber);
			CustomerController.setAutoId(cusId+1);
			CustomerController.getCustomers().add(customer);
			cart.setCustomerId(cusId);
		}
		display(cart);
		
		System.out.println("\t\t\tCam on quy khach, hen gap lai!");
		int oId =OrderController.getAutoId() ;
		Order o = new Order(oId,cart.getCustomerId(),"1000"+oId);
		OrderController.setAutoId(oId+1);
		OrderController.getOrders().add(o);
		for(ProductCart productCart: cart.getProductCarts()) {
			int pId=ProductInOrderController.getAutoId();
			ProductInOrder productInOrder = new ProductInOrder(pId,productCart.getProductId(),pId,productCart.getQuantity());
			ProductInOrderController.setAutoId(pId+1);
			ProductInOrderController.getInOrders().add(productInOrder);
		}
	}
}
