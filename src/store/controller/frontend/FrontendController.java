package store.controller.frontend;

import java.util.Scanner;


public class FrontendController {
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n\t\tCHUC NANG DANH CHO KHACH HANG");
			System.out.println("Moi quy khach chon chuc nang");
			System.out.println("\t1. Xem danh sach san pham cua cua hang");
			System.out.println("\t2. Quan ly gio hang");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban?: ");
			int choose = Integer.parseInt(sc.nextLine());
			
			switch (choose) {
			case 1: HomeController.display(); break;
			case 2: CartController.menu(); break;
			
			case 0: return;
			default: System.out.println("Lua chon khong hop le");
			}
		} while (true);
		
	}
}
