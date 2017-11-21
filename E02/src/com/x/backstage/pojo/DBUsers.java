package com.x.backstage.pojo;

public class DBUsers {
	private int id;
	private String name;
	private int age;
	private String address;
	private String phone;
	private int oid;
	 
	public DBUsers() {
		super();
	}
	public DBUsers(int id, String name, int age, String address, String phone,
			int oid) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.oid = oid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return "DBUsers [address=" + address + ", age=" + age + ", id=" + id
				+ ", name=" + name + ", oid=" + oid + ", phone=" + phone + "]";
	}

}
