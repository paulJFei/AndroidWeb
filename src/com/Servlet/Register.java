package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseUtil.ConnectUtility;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account + "--" + password);
		Boolean boo;
		try {
			boo=ConnectUtility.selectUserById(account);
			System.out.println(boo+"查询");
			if(boo==true) {
				PrintWriter out=response.getWriter();
				out.println(!boo);
				out.flush();
				out.close();
			}else {
				Boolean boo1=ConnectUtility.registerUser(account, password);
				PrintWriter out=response.getWriter();
				out.println(boo1);
				out.flush();
				out.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
