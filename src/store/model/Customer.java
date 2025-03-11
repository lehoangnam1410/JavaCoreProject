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
			System.out.println("\t\tSUA THONG TIN KHÁCH HÀNG");
			System.out.println("Chon mot thong tin can sua");
			System.out.println("\t1. Sửa tên khách hàng");
			System.out.println("\t2. Sửa số điện thoại");
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
				System.out.print("Nhap số điện thoại khách hàng: ");
				String phoneNumber = sc.nextLine();
				if (phoneNumber == null || phoneNumber.isEmpty()) {
					System.out.println("số điện thoại khong duoc de trong");
					return;
				}
				if (!phoneNumber.matches("[0-9]{8,15}")) {
			        System.out.println("So dien thoai chi duoc chua so và có độ dài từ 8 đến 15 ký tự");
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
