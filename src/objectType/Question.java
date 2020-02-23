package objectType;

import java.util.List;

public class Question {
	String qid;
	String context;
	String createtime;
	List<Answer> alist;

	public Question() {
	}
	public Question(String qid, String context, String createtime, List<Answer> alist) {
		super();
		this.qid = qid;
		this.context = context;
		this.createtime = createtime;
		this.alist = alist;
	}
	public Question(String qid, String context, String createtime) {
		super();
		this.qid = qid;
		this.context = context;
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Question [qid=" + qid + ", qtxt=" + context + ", creattime=" + createtime + ", alist=" + alist + "]";
	}


	public String getQid() {
		return qid;
	}


	public void setQid(String qid) {
		this.qid = qid;
	}


	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public String getCreatetime() {
		return createtime;
	}


	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	public List<Answer> getAlist() {
		return alist;
	}


	public void setAlist(List<Answer> alist) {
		this.alist = alist;
	}



}
