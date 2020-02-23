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
 * Servlet implementation class SelectAllQues
 */
public class SelectAllQues extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Question> qlist;
		try {
			qlist = ConnectUtility.queryQuestion();
			for (Question que : qlist) {
				String id = que.getQid();
				List<Answer> alist = ConnectUtility.queryAnswer(id);
				que.setAlist(alist);
			}
			Gson gson=new Gson();
			String str=gson.toJson(qlist);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out =response.getWriter();
			out.println(str);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
