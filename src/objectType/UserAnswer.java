package objectType;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by Cherish on 2019/1/11.
 */
/*
* 用户提交问卷时，该类存储用户关于每个问题的回答*/
public class UserAnswer {
    private String username;//用户名
    private String qid;//问题Id
    private String aid;//选项Id
    private String context;//选项描述
    private String judge;//该用户关于该问题的选项的正误判断

    public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserAnswer() {
        super();
    }

    public UserAnswer(String qid, String context) {
        this.qid = qid;
        this.context = context;
    }

    public UserAnswer( String username,String qid, String aid, String context) {
        super();
        this.username=username;
        this.qid = qid;
        this.aid = aid;
        this.context = context;
    }
    public UserAnswer( String username,String qid, String aid, String context, String judge) {
        super();
        this.username=username;
        this.qid = qid;
        this.aid = aid;
        this.context = context;
        this.judge=judge;
    }

}
