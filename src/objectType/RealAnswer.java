package objectType;

public class RealAnswer {
	String qid;//问题Id
	String context;//问题描述
	String realans;//答案
	String konw;//答案详情
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
	public String getRealans() {
		return realans;
	}
	public void setRealans(String realans) {
		this.realans = realans;
	}
	public String getKonw() {
		return konw;
	}
	public void setKonw(String konw) {
		this.konw = konw;
	}
	public RealAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RealAnswer(String qid, String context, String realans, String konw) {
		super();
		this.qid = qid;
		this.context = context;
		this.realans = realans;
		this.konw = konw;
	}
	public RealAnswer(String realans, String konw) {
		super();
		this.realans = realans;
		this.konw = konw;
	}
	public RealAnswer(String context, String realans, String konw) {
		super();
		this.context = context;
		this.realans = realans;
		this.konw = konw;
	}
	

	
	
	

}
