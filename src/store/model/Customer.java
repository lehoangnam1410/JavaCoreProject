package store.model;

import java.util.Scanner;


public class Customer {
	private int id;
	private String code;
	private String name;
	private String mobile;
	public void display() {
		System.out.printf("%3d %-12s %-35s %-35s \n",
				this.id, this.code, this.name,this.mobile);
	}
	
	public void edit() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\t\tSUA THONG TIN KHACH HANG");
			System.out.println("Chon mot thong tin can sua");
			System.out.println("\t1. Sua ten khach hang");
			System.out.println("\t2. Sua so dien thoai");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban?: ");
			int choose = Integer.parseInt(sc.nextLine());
			
			switch (choose) {
			case 1: 
				System.out.print("Nhap ten khach hang: ");
				String name = sc.nextLine();
				if (name == null || name.isEmpty()) {
					System.out.println("Ten khach hang khong duoc de trong");
					return;
				}
				this.setName(name);
				System.out.println("Sua ten khach hang thanh cong!");
				break;
			case 2:
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
				this.setMobile(phoneNumber);
				System.out.println("Sua thanh cong!");
				break;
			
			case 0: return;
			default: System.out.println("Lua chon khong hop le");
			}
		} while (true);	
		
	}
	public Customer() {
		super();
	}
	
	public Customer(int id, String code, String name, String mobile) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
