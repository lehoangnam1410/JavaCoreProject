package store.controller.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import store.model.Customer;

public class CustomerController {
	private static ArrayList<Customer> customers = new ArrayList<>();

	
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}
	public static void setCustomers(ArrayList<Customer> customers) {
		CustomerController.customers = customers;
	}
	public static int getAutoId() {
		return autoId;
	}
	public static void setAutoId(int autoId) {
		CustomerController.autoId = autoId;
	}

	static Scanner sc = new Scanner(System.in);

	public static int autoId = 1;
	public static void init() {
		customers.add(new Customer(autoId++, "10001","Nguyen Van Tai","0384490724"));
		customers.add(new Customer(autoId++, "10002","Le Van Dai","0384493724"));
		customers.add(new Customer(autoId++, "10003","Nguyen Thai Huy","0384490725"));
		customers.add(new Customer(autoId++, "10004","Dang Tien Viet","0384490726"));
		customers.add(new Customer(autoId++, "10005","Truong Tam Phong","0384490727"));
		
	}
	public static void menuSystemCategory() {
		do {
			System.out.println("\n-------CAP NHAT THONG TIN KHACH HANG--------");
			System.out.println("Chon chuc nang cap nhat thong tin");
			System.out.println("\t1. Xem danh sach khach hang");
			System.out.println("\t2. Them khach hang moi");
			System.out.println("\t3. Sua thong tin khach hang");
			System.out.println("\t4. Xoa thong tin khach hang");
			System.out.println("\t5. Sap xep danh sach khach hang");
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
		System.out.println("\t\tDanh sach khach hang");
		System.out.printf("%-3s %-12s %-35s %-35s %n", "ID", "Code", "Ten","So dien thoai");
		for(Customer x: customers) {
			x.display();
		}
	}
	private static void add() {
		System.out.println("\n--------Nhap khach hang moi-------");
		System.out.print("Ten cua khach hang:");
		String name = sc.nextLine();
		if(name.trim().length()==0) {
			System.out.println("\tKhong duoc de trong Ten!");
			return;
		}
		System.out.print("Nhap so dien thoai khach hang: ");
		String phoneNumber = sc.nextLine();
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			System.out.println("So dien thoai khong duoc de trong");
			return;
		}
		if (!phoneNumber.matches("[0-9]{8,15}")) {
			System.out.println("So dien thoai chi duoc chua so va co do dai tu 8 den 15 ky tu");
			return;
		}
		customers.add(new Customer(autoId,"1000"+autoId, name,phoneNumber));
		autoId++;
		System.out.println("\tThem moi thanh cong!");
	}

	//Ham tim nha cung cap theo ma
	public static int findIndexProviderByCode(String code) {
		for (int index = 0; index < customers.size(); index++) {
			if (customers.get(index).getCode().equals(code)) {
				return index;
			}
		}
		return -1;
	}
	private static void edit() {
		System.out.println("\t\t\nSua thong tin khach hang");
		System.out.print("Chon khach hang can sua (nhap ma khach hang): ");
		String code = sc.nextLine();
		int index = findIndexCustomerByCode(code);
		if (index == -1) {
			System.out.println("Khach hang nay khong ton tai trong he thong");
			return;
		}
		customers.get(index).edit();
	}

	private static void delete() {
		System.out.println("\t\t\nXoa thong tin khach hang");
		System.out.print("Chon khach hang can xoa (nhap ma): ");
		String code = sc.nextLine();
		int index = findIndexCustomerByCode(code);
		if (index == -1) {
			System.out.println("Khach hang nay khong ton tai trong he thong");
			return;
		}
		customers.remove(index);
		System.out.println("Xoa thanh cong!");
	}

	private static void sort() {
		Collections.sort(customers, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});	
	}
	
	public static int findIndexCustomerByCode(String code) {
		for (int index = 0; index < customers.size(); index++) {
			if (customers.get(index).getCode().equals(code)) {
				return index;
			}
		}
		return -1;
	}
	public static int findCustomerByCode(String code) {
		for (int index = 0; index < customers.size(); index++) {
			if (customers.get(index).getCode().equals(code)) {
				return index;
			}
		}
		return -1;
	}

	public static String getNameById(int id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer.getName();
			}
		}
		return null;
	}
	
	public static String getPhoneNumberById(int id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer.getMobile();
			}
		}
		return null;
	}
}
