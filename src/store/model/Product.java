package store.model;

import java.util.Scanner;

import store.controller.backend.CategoryController;
public class Product {
	private int id;
    private int categoryId;   
    private String code;      
    private String name;      
    private double price;
    public void display() {
		String categoryName = CategoryController.getNameById(this.categoryId);
		System.out.printf("%3d %-20s %-10s %-30s %,15.2f%n", this.id,
				categoryName, this.code, this.name, this.price);
	}
    public void edit() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\t\tSUA THONG TIN SAN PHAM");
			System.out.println("Chon mot thong tin can sua");
			System.out.println("\t1. Sửa loại sản phẩm");
			System.out.println("\t2. Sửa tên sản phẩm");
			System.out.println("\t3. Sửa đơn giá");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban?: ");
			int choose = Integer.parseInt(sc.nextLine());
			
			switch (choose) {
			case 1: 
				System.out.print("Chon chủng loại (nhap ma): ");
				String categoryCode = sc.nextLine();
	
				int vIndex = CategoryController.findIndexCategoryByCode(categoryCode);
				if (vIndex == -1) {
					System.out.println("Chủng loại không tồn tại!");
					return;
				}
				int categoryId = CategoryController.getCategories().get(vIndex).getId();
				this.setCategoryId(categoryId);
				System.out.println("Sua chủng loại thanh cong!");
				break;
			case 2:
				System.out.print("Nhap ten san pham: ");
				String name = sc.nextLine();
				if (name == null || name.isEmpty()) {
					System.out.println("Ten san pham khong duoc de trong");
					return;
				}
				this.setName(name);
				System.out.println("Sua ten thanh cong");
				break;
			case 3: 
				System.out.print("Nhap don gia san pham: ");
				double price = Double.parseDouble(sc.nextLine());
				if (price < 0) {
					System.out.println("Don gia khong duoc la so am!");
					return;
				}
				this.setPrice(price);
				System.out.println("Sua don gia thanh cong");
				break;
			case 0: return;
			default: System.out.println("Lua chon khong hop le");
			}
		} while (true);		
	}
	public Product() {
		super();
	}

	public Product(int id, int categoryId, String code, String name, double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}     
    
}
