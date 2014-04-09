package com.anshion.excel;

public class User {
	
	String name;
	String age;
	String address;
	String phone;

	public User(String _name, String _age, String _address, String _phone){
		name = _name;
		age = _age;
		address = _address;
		phone = _phone;
		
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getAge(){
		return age;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getPhone(){
		return phone;
	}
}
