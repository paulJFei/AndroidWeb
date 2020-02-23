package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objectType.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import databaseUtil.ConnectUtility;

/**
 * Servlet implementation class CheckAnswer
 * @param <UserAnswer>
 */
public class CheckAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String answer =request.getParameter("answer");
		/*System.out.println(answer);*/
		Gson gson=new Gson();
		List<UserAnswer> list = gson.fromJson(answer, new TypeToken<List<UserAnswer>>() {
        }.getType());
		Map<String, String> map=new HashMap<String, String>();
		
		for (UserAnswer userAnswer : list) {
			
			try {
				String realanswer=ConnectUtility.queryRealAnswerByQid(userAnswer.getQid());
				if(userAnswer.getContext().contains(realanswer)) {
					userAnswer.setJudge("true");
				}else {
					userAnswer.setJudge("false");
				}
				ConnectUtility.addResult(userAnswer.getUsername(),userAnswer.getQid(), userAnswer.getAid(),userAnswer.getContext(), userAnswer.getJudge());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		for (UserAnswer userAnswer : list) {
			System.out.println(userAnswer.getUsername());
			System.out.println(userAnswer.getQid());
			System.out.println(userAnswer.getAid());
			System.out.println(userAnswer.getContext());
			System.out.println(userAnswer.getJudge());
		}
		String listUserAnswer=gson.toJson(list);
		List<RealAnswer> rlist;
		try {
			rlist = ConnectUtility.queryRealAnswer();
			String listRealAnswer=gson.toJson(rlist);
		    map.put("userAnswer", listUserAnswer)	;
		    map.put("realAnswer", listRealAnswer);
		    String mapJson=gson.toJson(map);
		    System.out.println(mapJson);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(mapJson);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
