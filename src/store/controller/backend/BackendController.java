package store.controller.backend;

import java.util.Scanner;

public class BackendController {
	public static void menuUpdateSystemInformation() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n Cap nhat thong tin he thong");
			System.out.println("Chon chuc nang");
			System.out.println("1. Cap nhat danh sach chung loai (category)");
			System.out.println("2. Cap nhat thong tin san pham");
			System.out.println("3. Sua thong tin khach hang");
			System.out.println("0. Quay lai");
			System.out.print("Nhap lua chon: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
				case 1: {
					CategoryController.menuSystemCategory();
					break;
				}
				case 2: {
					ProductController.menu();
					break;
				}
				case 3: {
					CustomerController.menuSystemCategory();
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

}
