package com.demo.fileproc.service.dto.customer;

import com.demo.fileproc.service.dto.DataObjectInterface;

public class SalesPersonEntityDTO implements DataObjectInterface {
	private String Id;
	private String name;
	private String address;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "SalesPersonEntityDTO [Id=" + Id + ", name=" + name + ", address=" + address + "]";
	}


}
