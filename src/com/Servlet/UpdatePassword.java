package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseUtil.ConnectUtility;

/**
 * Servlet implementation class UpdatePassword
 */
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		boolean boo;
		boo=ConnectUtility.updatePassword(account, password);
		//将执行结果返回客户端
		PrintWriter out=response.getWriter();
		out.println(boo);
		out.flush();
		out.close();
	}

}
