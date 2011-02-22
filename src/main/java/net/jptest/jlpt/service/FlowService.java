package net.jptest.jlpt.service;

import net.jptest.jlpt.bean.User;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class FlowService {
	private WebConversation wc;
	private User user;

	public WebConversation getWc() {
		return wc;
	}

	public void setWc(WebConversation wc) {
		this.wc = wc;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 考生登录
	 * http://jlpt.etest.net.cn/login.do?flag=19
	 * Cookie: step=login; ksIdNo=420602198304131019; sFlag=420602198304131019
	 * 
	 * ksIDNO	420602198304131019
	 * ksPwd	pengyan
	 * clientTime	
	 * btnlogin	登录
	 * _	
	 * @throws Exception
	 */
	public void login() throws Exception {
		wc.setHeaderField("requesttype", "ajax");
		wc.putCookie("step", "login");
		wc.putCookie("ksIdNo", user.getKsIDNO());
		wc.putCookie("sFlag", user.getKsIDNO());
		
		PostMethodWebRequest post = new PostMethodWebRequest("");
		post.setParameter("ksIDNO", user.getKsIDNO());
		post.setParameter("ksPwd", user.getKsPwd());
		post.setParameter("clientTime", "");
		post.setParameter("btnlogin", "登录");
		WebResponse response = wc.getResource(post);
	}
	
}
