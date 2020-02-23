package databaseUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import objectType.*;



/*
 * 这是一个关于操作数据库的工具类
 * 
 * */


public class ConnectUtility {
	// 根据key在properties文件中查询值ֵ
	public static String readProperty(String key) throws IOException {
		Properties pro = new Properties();
		InputStream ins = ConnectUtility.class.getClassLoader().getResourceAsStream("database.properties");
		pro.load(ins);
		String str = pro.getProperty(key);
		return str;
	}

	// 连接数据库
	public static Connection linkDB() throws IOException, ClassNotFoundException, SQLException {
		String url = ConnectUtility.readProperty("url");
		String drive = ConnectUtility.readProperty("drive");
		String username = ConnectUtility.readProperty("username");// ���ݿ�����
		String passwd = ConnectUtility.readProperty("passwd");// ���ݿ�����
		Class.forName(drive);
		Connection con = DriverManager.getConnection(url, username, passwd);
		return con;
	}

	// 查询登陆用户是否在数据库中
	public static boolean selectUser(String account, String passwd)
			throws ClassNotFoundException, IOException, SQLException {
		Connection con = ConnectUtility.linkDB();
		Boolean boo;
		String sql = "select * from user where name = ? and passwd= ?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, account);
		pre.setString(2, passwd);
		ResultSet res = pre.executeQuery();
		boo = res.first();
		con.close();
		return boo;
	}

	// 查询注册用户是否在数据库中
	public static boolean selectUserById(String account) throws ClassNotFoundException, IOException, SQLException {
		Connection con = ConnectUtility.linkDB();
		Boolean boo;
		String sql = "select * from user where name = ?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, account);
		ResultSet res = pre.executeQuery();
		boo = res.first();
		con.close();
		return boo;
	}

	// 将用户的注册信息写入数据库
	public static Boolean registerUser(String account, String password) {
		Connection con = null;
		Boolean boo;
		try {
			con = ConnectUtility.linkDB();
			String sql = "insert into user (name,passwd) values (?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, account);
			pre.setString(2, password);
			pre.execute();
			con.close();
			boo=ConnectUtility.selectUserById(account);
			System.out.println("注册"+boo);
			return boo;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//查看密码是否修改成功
	public static boolean selectPassword(String account,String password) {
		Connection con = null;
		try {
			con = ConnectUtility.linkDB();
			String sql = "select passwd from user where name = ? ";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, account);
			ResultSet res = pre.executeQuery();
			res.first();
			String passwd=res.getString(1);
			if(passwd.contains(password)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//修改指定账户的密码
	public static Boolean updatePassword(String account,String password) {
		Connection con = null;
		Boolean boo;
		try {
			con=ConnectUtility.linkDB();
			String sql = "UPDATE user SET passwd =?  WHERE name = ? ";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, password);
			pre.setString(2, account);
			pre.execute();
			con.close();
			boo=ConnectUtility.selectPassword(account, password);
			return boo;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//查询所有问题答案的描述
	public  static List<RealAnswer> queryRealAnswer() throws Exception {
		String sql="select * from realanswer";
		Connection con=ConnectUtility.linkDB();
		PreparedStatement pre=con.prepareStatement(sql);
		ResultSet res=pre.executeQuery();
		List<RealAnswer> list=new ArrayList<RealAnswer>();
		while(res.next()){
			String qid=res.getString(1);
			String context=res.getString(2);
			String realans=res.getString(3);
			String konw=res.getString(4);
			RealAnswer rans=new RealAnswer(qid,context,realans,konw);
			list.add(rans);
		}
		con.close();
		return list;
	}
	//查询所有问题并形成一个存放Question对象的集合
	public static List<Question> queryQuestion() throws Exception {
		Connection con=ConnectUtility.linkDB();
		String sql="select * from question";
		PreparedStatement pre=con.prepareStatement(sql);
		ResultSet res=pre.executeQuery();
		List<Question> list=new ArrayList<Question>();
		while(res.next()){
			String qid=res.getString(1);
			String context=res.getString(2);
			String createtime=res.getString(3);
			Question que=new Question(qid,context,createtime);
			list.add(que);
		}
		con.close();
		return list;
	}
	//根据指定题号查询选项类answer，并把几个答案形成一个list集合
	 public static List<Answer> queryAnswer(String queid)throws Exception{
	    	Connection con=ConnectUtility.linkDB();
	    	String sql="select * from answer where qid=?";
	    	PreparedStatement pre=con.prepareStatement(sql);
	    	pre.setString(1, queid);
	  	    ResultSet res=pre.executeQuery();
	  	    List<Answer> alist=new ArrayList<Answer>();
	  	    while(res.next()){
	  	    	String qid=res.getString(1);
	  	    	String aid=res.getString(2);
	  	    	String context=res.getString(3);
	  	    	Answer an=new Answer(qid,aid,context,null);
	  	    	alist.add(an);
	  	    }
	  	    con.close();
	  	    return alist;  
	    }
	//查询指定指定问题的正确答案，返回String类型
	 public static String queryRealAnswerByQid(String qqid) throws Exception{
			String sql="select * from realanswer where qid=?";
			Connection con=ConnectUtility.linkDB();
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, qqid);
			ResultSet res=pre.executeQuery();
			List<RealAnswer> list=new ArrayList<RealAnswer>();
			while(res.next()){
				String qid=res.getString(1);
				String context=res.getString(2);
				String realans=res.getString(3);
				String konw=res.getString(4);
				RealAnswer rans=new RealAnswer(qid,context,realans,konw);
				list.add(rans);
	           }
			RealAnswer real=list.get(0);
			String correct=real.getRealans();
			return correct;
		}
	 //将用户的答题情况写入数据库
	 public static void addResult(String uname,String qid,String aid,String context,String judge) throws Exception{
			Connection con=ConnectUtility.linkDB();
			String sql="insert into result values(?,?,?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1,uname);
			pre.setString(2,qid);
			pre.setString(3,aid);
			pre.setString(4,context);
			pre.setString(5,judge);
			pre.execute();
			con.close();
		}
	 //查询指定用户是否已经参与答题
	 public static boolean checkUser(String uname) {
		 try {
			Connection con=ConnectUtility.linkDB();
			String sql="select * from result where uname=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1,uname);
			ResultSet res=pre.executeQuery();
			return res.first();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	 }
	 //删除指定用户；
	 public static void deleteUser(String account) throws Exception{
		 	Connection con=ConnectUtility.linkDB();
			String sql="delete from user where name=?";
		    PreparedStatement pre=con.prepareStatement(sql);
		    pre.setString(1, account);
		    pre.execute();
		    pre.close();	
		    con.close();		
		}
}
