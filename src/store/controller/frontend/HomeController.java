package store.controller.frontend;

import store.controller.backend.ProductController;
import store.model.Product;

public class HomeController {
	public static void display() {
		System.out.println("\n\t\tDANH SACH SAN PHAM");
		System.out.printf("%3s %-20s %-10s %-30s %-8s %-15s%n", "ID", 
				"Ten chung loai", "Ma san pham", "Ten san pham", "So luong", "Don gia");
		for (Product product : ProductController.getProducts()) {
			product.display();
		}		
	}
}
