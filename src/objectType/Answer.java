package objectType;

public class Answer{
	String qid;
	String aid;
	String context;
	String createtime;
	public Answer(){};
	public Answer(String qid,String aid,String context, String createtime){
		this.qid=qid;
		this.aid=aid;
		this.context=context;
		this.createtime=createtime;
	}
	
	public Answer(String qid, String aid, String context) {
		super();
		this.qid = qid;
		this.aid = aid;
		this.context = context;
	}
	@Override
	public String toString() {
		return "Answer [qid=" + qid + ", aid=" + aid + ", context=" + context + ", createtime=" + createtime + "]";
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
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
	
	
	
	
    
}

