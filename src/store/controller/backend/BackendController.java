package store.controller.backend;

import java.util.Scanner;


public class BackendController {
	public static void  menuUpdateSystemInformation() {
		 Scanner sc = new Scanner(System.in);
		 do {
	   	 System.out.println("\n Cập nhật thông tin hệ thống");
	   	 System.out.println("Chon chuc nang");
	        System.out.println("1. Cập nhật danh sách chủng loại(category)");
	        System.out.println("2. Cập nhật thông tin sản phẩm");
	        System.out.println("3. Sửa thông tin khách hàng");
	        System.out.println("0. Quay lại");
	        System.out.print("Nhập lựa chọn: ");
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
	               System.out.println("Không có lựa chọn nào phù hợp.");
	           }
	       }
	   } while (true);
	}
		
		 
}
