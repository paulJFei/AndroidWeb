package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.GapContent;

import com.google.gson.Gson;

import databaseUtil.ConnectUtility;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Login() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean checkboo;
		Boolean userboo;
		Map<String, String> map=new HashMap<String, String>();
		try {
			userboo = ConnectUtility.selectUser(username, password);//检查用户是否已经在数据库中
			checkboo=ConnectUtility.checkUser(username);//检查用户是否已经答题,已经答题为ture
			map.put("userboo", userboo.toString());
			map.put("checkboo", checkboo.toString());
			Gson gson=new Gson();
			String mapJson=gson.toJson(map);
			System.out.println(userboo.toString());
			//返回信息到客户端
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(mapJson);
			out.flush();
			out.close();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
