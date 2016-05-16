package com.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.AccountDao;
import com.domain.Account;


import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.net.URLConnection;
import javax.xml.parsers.*;
import java.util.*;
 
import org.w3c.dom.*;

public class AccountDao {

	private static AccountDao instance = new AccountDao();

	public static AccountDao getInstance() {
		return instance;
	}

	private static Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/team_moa");//fastorder로해도... 무방한듯?
		return ds.getConnection();
	}

	public void insertAccount(Account account) throws Exception { // �쉶�썝媛��엯
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;

		try {
			conn = getConnection();
			sql = "insert into account(account_Id, pwd, name) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, account.getAccount_Id());
			pstmt.setString(++cnt, account.getPwd());
			pstmt.setString(++cnt, account.getName());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(null, pstmt, conn);
		}
	}

	public int confirmId(String id) throws Exception { // �븘�씠�뵒 以묐났 泥댄겕
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int x = -1;
		try {
			conn = getConnection();
			sql = "select * from account where account_Id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = 1;
			else
				x = -1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return x;
	}

	public int userCheck(Account account, String id, String passwd) throws Exception { //유저 로그인 체크
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String dbpasswd = "";
		int x = -1;

		try {
			conn = getConnection();
			sql = "select account_Idx, pwd from account where account_Id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpasswd = rs.getString("pwd");
				account.setAccount_Idx(rs.getString("account_Idx"));
				if (dbpasswd.equals(passwd))
					x = 1; // 로그인 성공
				else
					x = 0; // 로그인 실패
			} else
				x = -1;// 로그인 실패

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return x;
	}

	public Account getAccount(String id) throws Exception { // 怨꾩젙�젙蹂� 議고쉶
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;
		String sql = "";
		try {
			conn = getConnection();
			sql = "select * from account where account_Id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				account = new Account();
				account.setAccount_Id(rs.getString("account_Id"));
				account.setPwd(rs.getString("pwd"));
				account.setName(rs.getString("name"));
		
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return account;
	}

	

	public static void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) throws Exception { // DB
																									// �떕湲�
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
			}
	}
}