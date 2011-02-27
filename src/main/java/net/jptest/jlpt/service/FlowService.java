package net.jptest.jlpt.service;

import java.util.ArrayList;
import java.util.List;

import net.jptest.jlpt.bean.Constants;
import net.jptest.jlpt.bean.Result;
import net.jptest.jlpt.bean.SchoolInfo;
import net.jptest.jlpt.bean.URL;
import net.jptest.jlpt.bean.User;
import net.jptest.jlpt.config.Config;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class FlowService {
	private Log log = LogFactory.getLog(this.getClass());
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
	 * 解析返回结果
	 * 
	 * @param text
	 *            返回结果
	 * @return Result 结果信息
	 * @throws AppException
	 * @throws JSONException
	 */
	private Result parseResult(String text) throws Exception {
		if (!text.startsWith("{")) {
			throw new Exception("返回结果错误:" + text);
		}
		JSONObject result = new JSONObject(text);
		if (text.contains("errorNum")) {
			return new Result(result.getString("errorNum"), result.getString("retVal"));
		} else {
			return new Result("", result.getString("retVal"));
		}
	}
	
	/**
	 * 解析考场信息
	 * 
	 * @param addressInfo
	 *            考场信息
	 * @return schoolInfos 解析后的考场信息
	 * @throws JSONException
	 */
	private List<SchoolInfo> parseAddressInfo(String addressInfo) throws JSONException {
		JSONArray addressList = new JSONArray(addressInfo);
		List<SchoolInfo> schoolInfos = new ArrayList<SchoolInfo>();
		for (int i = 0; i < addressList.length(); i++) {
			JSONObject address = (JSONObject) addressList.get(i);
			// 测试时，无座考场也解析
			if ("test".equals(Config.TEST_OR_REAL)) {
				if (user.getProvince().equals(address.getString("dq"))) {
					SchoolInfo schoolInfo = new SchoolInfo();
					schoolInfo.setId(address.getInt("id"));
					schoolInfo.setVacancy(address.getInt("vacancy"));
					schoolInfo.setMc(StringUtils.trim(address.getString("mc")));
					schoolInfo.setDm(StringUtils.trim(address.getString("dm")));
					schoolInfo.setDq(StringUtils.trim(address.getString("dq")));
					schoolInfos.add(schoolInfo);
				}
			// 生产环境，只解析有座考场
			} else {
				if (user.getProvince().equals(address.getString("dq"))
						&& Constants.HAS_VACANCY == address.getInt("vacancy")) {
					SchoolInfo schoolInfo = new SchoolInfo();
					schoolInfo.setId(address.getInt("id"));
					schoolInfo.setVacancy(address.getInt("vacancy"));
					schoolInfo.setMc(StringUtils.trim(address.getString("mc")));
					schoolInfo.setDm(StringUtils.trim(address.getString("dm")));
					schoolInfo.setDq(StringUtils.trim(address.getString("dq")));
					schoolInfos.add(schoolInfo);
				}
			}

		}

		if (schoolInfos.size() == 0) {
			log.info(user.getProvince() + "没有空位，进入提前报名模式");
			for (int i = 0; i < addressList.length(); i++) {
				JSONObject address = (JSONObject) addressList.get(i);
				if (user.getProvince().equals(address.getString("dq"))) {
					SchoolInfo schoolInfo = new SchoolInfo();
					schoolInfo.setId(address.getInt("id"));
					schoolInfo.setVacancy(address.getInt("vacancy"));
					schoolInfo.setMc(StringUtils.trim(address.getString("mc")));
					schoolInfo.setDm(StringUtils.trim(address.getString("dm")));
					schoolInfo.setDq(StringUtils.trim(address.getString("dq")));
					schoolInfos.add(schoolInfo);
				}

			}
		}
		return schoolInfos;

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
	
	/**
	 * 取消考场
	 * 
	 */
	private void cancleMc() throws Exception {
		wc.putCookie("step", "status");
		wc.putCookie("ksIdNo", user.getKsIDNO());
		wc.putCookie("sFlag", user.getKsIDNO());
		wc.putCookie("ksIdType", user.getKsIdType());
		wc.putCookie("remains", "1");
		wc.putCookie("bkkdmc", user.getBkkdmc());
		wc.putCookie("ksid", user.getKsid());
		wc.putCookie("ksLoginFlag", user.getKsLoginFlag());
		wc.putCookie("chkImgFlag", user.getKsIDNO());
		
		PostMethodWebRequest request = new PostMethodWebRequest("");
		request.setParameter("bkjb", String.valueOf(user.getBkjb()));
		request.setParameter("bkkd", String.valueOf(user.getBkkd()));
		request.setParameter("ksid", user.getKsid());
		request.setParameter("ksIdNo", user.getKsIDNO());
		request.setParameter("ksLoginFlag", user.getKsLoginFlag());

		WebResponse response = wc.getResource(request);
		// 用JONS解析结果
		Result result = parseResult(response.getText());

		if (!"1".equals(result.getRetVal())) {
			throw new Exception("取消考场失败" + user.toString());
		}
	}
	
	/**
	 * 选择考级，取得考场信息
	 * 
	 * @return List<SchoolInfo> 考场信息
	 */
	public List<SchoolInfo> chooseLevel() throws Exception {
		GetMethodWebRequest request = new GetMethodWebRequest(URL.CHOOSE_LEVEL + user.getBkjb());

		wc.setHeaderField("requesttype", "ajax");

		if (user.isPayed()) {
			wc.putCookie("step", "chooseaddr");
			wc.putCookie("ksIdType", user.getKsIdType());
			wc.putCookie("bkkdmc", user.getBkkdmc());
			wc.putCookie("payed", "true");
			wc.putCookie("bkjb", String.valueOf(user.getBkjb()));
			wc.putCookie("bkkd", String.valueOf(user.getBkkd()));
			wc.putCookie("isChangeKD", "1");
		} else {
			wc.putCookie("step", "login");

		}
		wc.putCookie("ksIdNo", user.getKsIDNO());
		wc.putCookie("sFlag", user.getKsIDNO());
		WebResponse addressInfo = wc.getResource(request);
		return parseAddressInfo(addressInfo.getText());
	}
	
//	/**
//	 * 预订考场
//	 * 
//	 * @param bkkd
//	 *            报考考点
//	 * @return boolean 是否预订成功
//	 * @throws AppException
//	 * @throws MalformedURLException
//	 * @throws IOException
//	 * @throws SAXException
//	 * @throws JSONException
//	 */
//	public boolean book(String bkkd) throws Exception {
//
//		wc.setHeaderField("requesttype", "ajax");
//		if (user.isPayed()) {
//			this.chkImgCode = getImgCode("status");
//			wc.putCookie("step", "status");
//			wc.putCookie("ksIdType", this.idType);
//			wc.putCookie("bkkdmc", this.bkkdmc);
//			wc.putCookie("payed", "true");
//			wc.putCookie("bkjb", this.level);
//			wc.putCookie("bkkd", this.bkkd);
//			wc.putCookie("isChangeKD", "1");
//		} else {
//			this.chkImgCode = getImgCode("selectLevel");
//			wc.putCookie("step", "chooseaddr");
//
//		}
//
//		wc.putCookie("ksIdNo", uid);
//		wc.putCookie("sFlag", uid);
//		wc.putCookie("remains", "1");
//		wc.putCookie("ksid", String.valueOf(ksid));
//		wc.putCookie("ksLoginFlag", ksLoginFlag);
//		wc.putCookie("chkImgSrc", this.chkImgSrc);
//		PostMethodWebRequest loginRequest;
//		if (this.payed) {
//			loginRequest = new PostMethodWebRequest(URL.CHANGE_BOOK);
//
//		} else {
//			loginRequest = new PostMethodWebRequest(URL.BOOK);
//		}
//		// bkjb=1&bkkd=17&ksid=272230&ksIdNo=420602198304131019&chkImgFlag=420602198304131019&ksLoginFlag=BnVS90PFagO24M1ySM6b3E8ZDk0STmut&chkImgCode=vavg
//		loginRequest.setParameter("bkjb", this.level);
//		loginRequest.setParameter("bkkd", bkkd);
//		loginRequest.setParameter("ksid", String.valueOf(ksid));
//		loginRequest.setParameter("ksIdNo", uid);
//		loginRequest.setParameter("chkImgFlag", uid);
//		loginRequest.setParameter("ksLoginFlag", ksLoginFlag);
//		loginRequest.setParameter("chkImgCode", chkImgCode);
//		WebResponse response = wc.getResource(loginRequest);
//
//		// 用JONS解析结果
//		Result result = parseResult(response.getText());
//		if ("1".equals(result.getRetVal())) {
//			return true;
//		} else {
//			throw new AppException(ErrorCode.valueOf("ERROR_" + result.getErrorNum()).toString());
//		}
//
//	}
//	

}
