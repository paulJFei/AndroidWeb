package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseUtil.ConnectUtility;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		try {
			ConnectUtility.deleteUser(account);
			//将执行结果返回客户端
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println(true);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
