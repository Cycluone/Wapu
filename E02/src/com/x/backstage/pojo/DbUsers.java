package com.x.backstage.pojo;

public class DbUsers {
    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private String phone;

    private Integer oid;

    public DbUsers() {
		super();
	}

	public DbUsers(Integer id, String name, Integer age, String address,
			String phone, Integer oid) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.oid = oid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

	@Override
	public String toString() {
		return "DbUsers [address=" + address + ", age=" + age + ", id=" + id
				+ ", name=" + name + ", oid=" + oid + ", phone=" + phone + "]";
	}
    
}