package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import databaseUtil.ConnectUtility;
import objectType.*;

/**
 * Servlet implementation class QueryQues
 */
public class QueryQues extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<RealAnswer> rlist=ConnectUtility.queryRealAnswer();
			Gson gson=new Gson();
			String str=gson.toJson(rlist);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
