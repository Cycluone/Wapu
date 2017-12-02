package com.yb.bean;

public class Entity {
	private String model;
	private String color;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// 重写了 hashCode 方法
	public int hashCode() {
		return model.hashCode() + 36;
	}

	// 重写了 equals 方法
	public boolean equals(Object obj) {
		if (!(obj instanceof Entity))
			throw new ClassCastException("类型异常");

		Entity e = (Entity) obj;
		return this.model.equals(e.model);
	}

	@Override
	public String toString() {
		return "Entity [model=" + model + ", color=" + color + "]";
	}
	
}
