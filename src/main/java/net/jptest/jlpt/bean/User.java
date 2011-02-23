package net.jptest.jlpt.bean;

public class User {
	private String ksIDNO;
	private String ksPwd;
	private boolean payed;
	/** 考生出生日期 */
	private String ksCsrq;
	private int bkjb;
	private String ksXing;
	private String ksMing;
	private int bkkd;
	/** 考生照片 */
	private String ksImgSrc;
	/** 考生登录FLAG */
	private String ksLoginFlag;
	private String ksPXing;
	private String ksPMing;
	/** 准考证 */
	private String zkz;
	/** 考生ID类型 */
	private String ksIdType;
	/** 报考考点名称 */
	private String bkkdmc;
	/** 考生性别 */
	private String ksXb;
	/** 考生ID */
	private String ksid;
	/** 报考省 */
	private String province;
	
	public String getKsIDNO() {
		return ksIDNO;
	}
	public void setKsIDNO(String ksIDNO) {
		this.ksIDNO = ksIDNO;
	}
	public String getKsPwd() {
		return ksPwd;
	}
	public void setKsPwd(String ksPwd) {
		this.ksPwd = ksPwd;
	}
	public boolean isPayed() {
		return payed;
	}
	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	public String getKsCsrq() {
		return ksCsrq;
	}
	public void setKsCsrq(String ksCsrq) {
		this.ksCsrq = ksCsrq;
	}
	public int getBkjb() {
		return bkjb;
	}
	public void setBkjb(int bkjb) {
		this.bkjb = bkjb;
	}
	public String getKsXing() {
		return ksXing;
	}
	public void setKsXing(String ksXing) {
		this.ksXing = ksXing;
	}
	public String getKsMing() {
		return ksMing;
	}
	public void setKsMing(String ksMing) {
		this.ksMing = ksMing;
	}
	public int getBkkd() {
		return bkkd;
	}
	public void setBkkd(int bkkd) {
		this.bkkd = bkkd;
	}
	public String getKsImgSrc() {
		return ksImgSrc;
	}
	public void setKsImgSrc(String ksImgSrc) {
		this.ksImgSrc = ksImgSrc;
	}
	public String getKsLoginFlag() {
		return ksLoginFlag;
	}
	public void setKsLoginFlag(String ksLoginFlag) {
		this.ksLoginFlag = ksLoginFlag;
	}
	public String getKsPXing() {
		return ksPXing;
	}
	public void setKsPXing(String ksPXing) {
		this.ksPXing = ksPXing;
	}
	public String getKsPMing() {
		return ksPMing;
	}
	public void setKsPMing(String ksPMing) {
		this.ksPMing = ksPMing;
	}
	public String getZkz() {
		return zkz;
	}
	public void setZkz(String zkz) {
		this.zkz = zkz;
	}
	public String getKsIdType() {
		return ksIdType;
	}
	public void setKsIdType(String ksIdType) {
		this.ksIdType = ksIdType;
	}
	public String getBkkdmc() {
		return bkkdmc;
	}
	public void setBkkdmc(String bkkdmc) {
		this.bkkdmc = bkkdmc;
	}
	public String getKsXb() {
		return ksXb;
	}
	public void setKsXb(String ksXb) {
		this.ksXb = ksXb;
	}
	public String getKsid() {
		return ksid;
	}
	public void setKsid(String ksid) {
		this.ksid = ksid;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "User [ksIDNO=" + ksIDNO + ", ksPwd=" + ksPwd + ", payed="
				+ payed + ", ksCsrq=" + ksCsrq + ", bkjb=" + bkjb + ", ksXing="
				+ ksXing + ", ksMing=" + ksMing + ", bkkd=" + bkkd
				+ ", ksImgSrc=" + ksImgSrc + ", ksLoginFlag=" + ksLoginFlag
				+ ", ksPXing=" + ksPXing + ", ksPMing=" + ksPMing + ", zkz="
				+ zkz + ", ksIdType=" + ksIdType + ", bkkdmc=" + bkkdmc
				+ ", ksXb=" + ksXb + ", ksid=" + ksid + ", province="
				+ province + "]";
	}

}
