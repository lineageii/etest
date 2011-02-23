package net.jptest.jlpt.bean;

import net.jptest.jlpt.config.Config;

public interface URL {

	String HTTP = "http://";
	String JLPT = "jlpt.";
	String INDEX = HTTP + JLPT + Config.URL + "/index.do?flag=D1";
	String LOGIN =  HTTP + JLPT + Config.URL + "/login.do?flag=H1";
	String CHOOSE_LEVEL =  HTTP + JLPT + Config.URL + "/chooseAddr.do?bkjb=";
	String QUERY_BOOK =  HTTP + JLPT + Config.URL + "/queryBook.do?flag=D1";
	String BOOK =  HTTP + JLPT + Config.URL + "/book.do?flag=D1";
	String CHANGE_BOOK = HTTP + JLPT + Config.URL + "/changebook.do?flag=D1";
	String CHK_IMG =  HTTP + JLPT + Config.URL + "/chkImg.do?flag=D1";
	String REGISTER =  HTTP + JLPT + Config.URL + "/register.do?flag=D1";
	String INFO = HTTP + JLPT + Config.URL + "/info.do?flag=D1";
	String CANCEL = HTTP + JLPT + Config.URL + "/cancel.do?flag=D1";
	
	String REGINFO = HTTP + JLPT + Config.URL + "/reginfo.do?flag=H1";
	
	
	String UPLOAD_PIC = HTTP + JLPT + Config.URL + "/uploadPic.do";
	String BEGIN_UPLOAD = HTTP + "up." + Config.URL + "/beginUpload.do";
	
	String UPLOAD = HTTP + "up." + Config.URL + "/upload.do";
	String CROP_IMAGE = HTTP + "up." + Config.URL + "/cropImage.do";
	String COMPLETE = HTTP + "up." + Config.URL + "/complete.do";
	
	String FIND_PWD_QUES = HTTP + JLPT + Config.URL + "/findpwdQues.do?flag=H1";
	
	/** 取得报名用户URL */
	String JSON = "http://riyubaoming.com/dj/json.php";
	
	/** 更新日语报名状态 */
	String UPDATE_US = "http://riyubaoming.com/dj/update_us.php";
	
	/** 上传照片URL */
	String UP_PIC = "http://riyubaoming.com/dj/uploads/";
	
	
	
	

}
