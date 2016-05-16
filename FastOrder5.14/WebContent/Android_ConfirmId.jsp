<%@ page contentType="text/html; charset=euc-kr" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@  page import="org.json.simple.JSONObject"%>
<%@  page import="org.json.simple.JSONArray"%>
<%@ page import="com.dao.AccountDao"%>

<%
System.out.println("뜨나ㅣ?");
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("email");;

	System.out.println(id);
	
	AccountDao manager = AccountDao.getInstance();
	int check=manager.confirmId(id);

	JSONArray jArray = new JSONArray(); // 배열
	JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체.

	if(check == 1){  //아이디 중복
		//session.setAttribute("memId",id);
		
		jObject.put("flag", "1");
	}else{  //아이디 중복 없음
		
		jObject.put("flag", "0");
	}

	jArray.add(0, jObject);

	out.println(jArray);
	out.flush();
	System.out.println("안드로이드 컴파일아이디 Clear?");
%>