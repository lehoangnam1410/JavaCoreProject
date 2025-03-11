package store.controller.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import store.model.Category;

public class CategoryController {
	private static ArrayList<Category> categories = new ArrayList<>();

	public static ArrayList<Category> getCategories() {
		return categories;
	}

	public static void setCategories(ArrayList<Category> categories) {
		CategoryController.categories = categories;
	}
	static Scanner sc = new Scanner(System.in);

	public static int autoId = 1;
	public static void init() {
		categories.add(new Category(autoId++, "10001","Tai nghe"));
		categories.add(new Category(autoId++, "10002","Điện thoại"));
		categories.add(new Category(autoId++, "10003","Máy tính"));
		categories.add(new Category(autoId++, "10004","Sạc"));
		categories.add(new Category(autoId++, "10005","Chuột và bàn phím"));
		
	}
	public static void menuSystemCategory() {
		do {
			System.out.println("\n-------CAP NHAT THONG TIN CHUNG LOAI--------");
			System.out.println("Chon chuc nang cap nhat thong tin");
			System.out.println("\t1. Xem danh sach chung loai");
			System.out.println("\t2. Them chung loai moi");
			System.out.println("\t3. Sua thong tin chung loai");
			System.out.println("\t4. Xoa thong tin chung loai");
			System.out.println("\t5. Sap xep danh sach chung loai");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			
			switch (chon) {
			case 1: display(); break;
			case 2: add(); break;
			case 3: edit(); break;
			case 4: delete(); break;
			case 5: sort(); break;
			case 0: return;
			default:
				System.out.println("Lua chon khong hop le");
			}
			System.out.println("============================================");
		}while(true);
	}
	private static void display() {
		// TODO Auto-generated method stub
		System.out.println("\t\tDANH SACH CHUNG LOAI");
		System.out.printf("%-6s %-21s %-21s%n", "ID", "Code", "Kieu");
		for(Category x: categories) {
			x.display();
		}
	}
	private static void add() {
		// TODO Auto-generated method stub
		System.out.println("\n--------NHAP CHUNG LOAI MOI-------");
		System.out.print("Ten cua chung loai:");
		String name = sc.nextLine();
		if(name.trim().length()==0) {
			System.out.println("\tKhong duoc de trong Ten!");
			return;
		}
		categories.add(new Category(autoId,"1000"+autoId, name));
		autoId++;
		System.out.println("\tThem moi thanh cong!");
	}

	//Ham tim nha cung cap theo ma
	public static int findIndexProviderByCode(String code) {
		for (int index = 0; index < categories.size(); index++) {
			if (categories.get(index).getCode().equals(code)) {
				return index;
			}
		}
		return -1;
	}
	private static void edit() {
		System.out.println("\t\t\nSửa thông tin chủng loại");
		System.out.print("Chon chủng loại cần sửa (nhap ma chủng lọai): ");
		String code = sc.nextLine();
		//Tim xem co NCC nay hay khong?
		int index = findIndexCategoryByCode(code);
		if (index == -1) {
			System.out.println("Nha cung cap nay khong ton tai trong he thong");
			return;
		}
		categories.get(index).edit();
	}

	private static void delete() {
		System.out.println("\t\t\nxóa thông tin chủng loại");
		System.out.print("Chon chủng loại can xoa (nhap ma chủng loại): ");
		String code = sc.nextLine();
		int index = findIndexCategoryByCode(code);
		if (index == -1) {
			System.out.println("chủng loại nay khong ton tai trong he thong");
			return;
		}
		categories.remove(index);
		System.out.println("Xoa thanh cong!");
	}

	private static void sort() {
		Collections.sort(categories, new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});	
	}
	
	public static int findIndexCategoryByCode(String code) {
		for (int index = 0; index < categories.size(); index++) {
			if (categories.get(index).getCode().equals(code)) {
				return index;
			}
		}
		return -1;
	}
	
	public static String getNameById(int id) {
		for (int index = 0; index < categories.size(); index++) {
			if (categories.get(index).getId() == id) {
				return categories.get(index).getName();
			}
		}
		return null;
	}
	
	public static Category getCategoryByCode(String code) {
		for (int index = 0; index < categories.size(); index++) {
			if (categories.get(index).getCode().equals(code)) {
				return categories.get(index);
			}
		}
		return new Category();
	}

}
