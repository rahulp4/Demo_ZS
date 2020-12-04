package com.demo.fileproc.service.dto.customer;

import com.demo.fileproc.service.dto.DataObjectInterface;

public class CustomerEntityDTO implements DataObjectInterface {

	private String Id;
	private String fileRefId;
	private String name;
	private String address;
	private String attribute1;
	private String attribute2;

	
//	CREATE TABLE `DEMO_CUSTOMER` (
//			  `id` int(11) NOT NULL,
//			  `name` varchar(50) NOT NULL,
//			  `filerefid` varchar(50) NOT NULL,
//			  `address` varchar(50) NOT NULL,
//			  `attribute1` varchar(50) NOT NULL,
//			  `attribute2` varchar(50) NOT NULL,
//			  PRIMARY KEY (`id`)
//	
	private SalesPersonEntityDTO sales;
	
	public SalesPersonEntityDTO getSales() {
		return sales;
	}
	public void setSales(SalesPersonEntityDTO sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "CustomerEntityDTO [Id=" + Id + ", fileRefId=" + fileRefId + ", name=" + name + ", address=" + address
				+ ", attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", sales=" + sales + "]";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getFileRefId() {
		return fileRefId;
	}
	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
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
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
}
