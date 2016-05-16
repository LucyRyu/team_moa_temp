package com.domain;

public class Account {

	private String account_Idx;
	private String account_Id="";
	private String pwd;
	private String name;
	
	
	public String getAccount_Idx(){
		return account_Idx;
	}
	public void setAccount_Idx(String account_Idx){
		this.account_Idx = account_Idx;
	}
	
	public String getAccount_Id(){
		return account_Id;
	}
	public void setAccount_Id(String account_Id){
		this.account_Id = account_Id;
	}

	public String getPwd(){
		return pwd;
	}
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "account [id = " + account_Id + ", pwd = " + pwd + ", name = " + name + "]";
	}
}
