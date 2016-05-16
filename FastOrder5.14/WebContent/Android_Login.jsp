<%@ page contentType="text/html; charset=euc-kr" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@  page import="org.json.simple.JSONObject"%>
<%@  page import="org.json.simple.JSONArray"%>
<%@ page import="com.dao.AccountDao"%>

<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="account" class="com.domain.Account">
	<jsp:setProperty name="account" property="*" />
</jsp:useBean>
<%
	String id, pwd, idx;

	id = request.getParameter("id");
	pwd = request.getParameter("pwd");

	System.out.println(id);
	System.out.println(pwd);

	AccountDao manager = AccountDao.getInstance();
	int check = manager.userCheck(account, id, pwd);
	idx = account.getAccount_Idx();

	JSONArray jArray = new JSONArray(); // 배열
	JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체.

	if (check == 1) { //로그인 성공
		jObject.put("LoginCheck", 1);
		jObject.put("AccountIdx", idx);

	} else if (check == 0) { //비밀번호 틀림
		jObject.put("LoginCheck", 0);
	} else { //아이디 틀림   
		jObject.put("LoginCheck", -1);
	}
	jArray.add(0, jObject);

	out.println(jArray);
	out.flush();
%>