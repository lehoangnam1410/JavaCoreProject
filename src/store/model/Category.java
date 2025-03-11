package store.model;

import java.util.Scanner;

public class Category {
	private int id;
	private String code;
    private String name;
    
    public void display() {
    	System.out.printf("%-3s %-12s %-35s%n",this.id,this.code,this.name);
    }
    public void edit() {
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Nhập tên chủng loại: ");
    	String name = sc.nextLine();
    	if (name == null || name.isEmpty()) {
    		System.out.println("Tên không đc để trống");
    		return;
    	}
    	this.setName(name);
    	System.out.println("Sửa thành công");
    }
	public Category() {
		super();
	}
	public Category(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
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
    
	
}
