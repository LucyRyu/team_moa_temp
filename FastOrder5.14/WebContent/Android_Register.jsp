<%@ page contentType="text/html; charset=euc-kr" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import ="com.dao.AccountDao" %>

<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id = "account" class = "com.domain.Account">
<jsp:setProperty name = "account" property = "*"/>
</jsp:useBean>
<%
//안드로이드 회원가입
	String id, pwd, name;

	id = request.getParameter("id");
	pwd = request.getParameter("pwd");
	name = request.getParameter("name");


	System.out.println(id);
	System.out.println(pwd);
	System.out.println(name);

	
	account.setAccount_Id(id);
	account.setPwd(pwd);
	account.setName(name);
	
	AccountDao manager = AccountDao.getInstance();
	manager.insertAccount(account);

	out.flush();
%>