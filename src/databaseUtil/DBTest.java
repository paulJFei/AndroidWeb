package databaseUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import objectType.*;

public class DBTest {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
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
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
