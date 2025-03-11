package store.controller.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import store.model.Product;

public class ProductController {
	
	private static int autoId = 1;
	
	private static ArrayList<Product> products = new ArrayList<>();
	
	
	
	public static int getAutoId() {
		return autoId;
	}

	public static void setAutoId(int autoId) {
		ProductController.autoId = autoId;
	}

	public static ArrayList<Product> getProducts() {
		return products;
	}

	public static void init() {
		products.add(new Product(autoId++, 1, "10001", "Tai nghe Z", 120000));
		products.add(new Product(autoId++, 2, "10002", "Samsung", 420000));
		products.add(new Product(autoId++, 2, "10003", "IP15", 130000));
		products.add(new Product(autoId++, 3, "10004", "Asus", 150000));
		products.add(new Product(autoId++, 3, "10005", "Lenovo", 1200000));
		
	}

	public static void setProducts(ArrayList<Product> products) {
		ProductController.products = products;
	}

	static Scanner sc = new Scanner(System.in);

	public static void menu() {

		do {
			System.out.println("\n\t\tCAP NHAT THONG TIN SAN PHAM	");
			System.out.println("Chon mot chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach san pham");
			System.out.println("\t2. Them san pham moi");
			System.out.println("\t3. Sua thong tin san pham");
			System.out.println("\t4. Xoa thong tin san pham");
			System.out.println("\t5. Sap xep danh sach san pham");
			System.out.println("\t6. Tìm kiếm sản phẩm theo từ khóa");
			System.out.println("\t0. Quay lại");

			System.out.print("Lựa chọn của bạn?: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				display();
				break;
			case 2:
				add();
				break;
			case 3:
				edit();
				break;
			case 4:
				delete();
				break;
			case 5:
				sort();
				break;
			case 6:
				search();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);

	}

	private static void search() {
		// TODO Auto-generated method 
		   boolean check = false;
		  String name;
		  System.out.println("Nhập tên sản phẩm cần tìm: ");
		  name = sc.nextLine();
			System.out.printf("%3s %-20s %-10s %-30s %-15s%n", "ID", 
					"Tên Chủng loại", "Mã sản phẩm", "Tên sản phẩm", "Đơn giá");
		for(Product product: products) {
			if(product.getName().toLowerCase().contains(name.toLowerCase())) {
				product.display();
				check =true;
			}
		}
		if (!check) {
	        System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + name + "'");
	    }
	}

	private static void display() {
		System.out.println("\n\t\tDANH SACH SAN PHAM");
		System.out.printf("%3s %-20s %-10s %-30s %-15s%n", "ID", 
				"Tên Chủng loại", "Mã sản phẩm", "Tên sản phẩm", "Đơn giá");
		for (Product product : products) {
			product.display();
		}		
	}

	private static void add() {
		System.out.println("\t\t\nTHEM MOI SAN PHAM");
		
		System.out.print("Chon Mã chủng loại (nhap ma): ");
		String categoryCode = sc.nextLine();
		int vIndex = CategoryController.findIndexCategoryByCode(categoryCode);
		if (vIndex == -1) {
			System.out.println("Chủng loại khong ton tai!");
			return;
		}
		
		System.out.print("Nhap ten san pham: ");
		String name = sc.nextLine();
		if (name == null || name.isEmpty()) {
			System.out.println("Ten san pham khong duoc de trong");
			return;
		}
		
		System.out.print("Nhap don gia san pham: ");
		double price = Double.parseDouble(sc.nextLine());
		if (price < 0) {
			System.out.println("Don gia khong duoc la so am!");
			return;}
		int categoryId = CategoryController.getCategoryByCode(categoryCode).getId();
		Product product = new Product(autoId, categoryId, "1000" + autoId, name, price);
		autoId++;
		products.add(product);
		System.out.println("Them moi san pham thanh cong!");	
	}

	private static void edit() {
		System.out.println("\t\t\nSUA THONG TIN SAN PHAM");
		System.out.print("Chon SAN PHAM can sua (nhap ma SP): ");
		String code = sc.nextLine();
		int index = findProductByCode(code);
		if (index == -1) {
			System.out.println("San pham nay khong ton tai trong he thong");
			return;
		}
		products.get(index).edit();	
	}

	private static void delete() {
		System.out.println("\t\t\nXOA THONG TIN SAN PHAM");
		System.out.print("Chon SP can xoa (nhap ma SP): ");
		String code = sc.nextLine();
		int index = findProductByCode(code);
		if (index == -1) {
			System.out.println("San pham nay khong ton tai trong he thong");
			return;
		}
		products.remove(index);
		System.out.println("Xoa san pham thanh cong!");
		
	}

	private static void sort() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});	
		
	}
	
	//Ham tim sp theo ma
	public static int findProductByCode(String code) {
		for (int index = 0; index < products.size(); index++) {
			if (products.get(index).getCode().equals(code)) {
				return index;
			}
		}
		return -1;
	}
	
	//Ham lay san pham theo id
	public static Product getProductById(int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return new Product();
	}
}
