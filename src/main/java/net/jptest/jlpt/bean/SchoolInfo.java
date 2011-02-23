package net.jptest.jlpt.bean;

/**
 * 日语报名考场
 * example: {"id":1,"vacancy":0,"mc":"北京外国语大学考试中心","dm":"1020101","dq":"北京"}
 * @author hujiag@gmail.com
 */
public class SchoolInfo {
	private int id;
	private int vacancy; // 空缺
	private String mc; // 考场
	private String dm; // 考场ID
	private String dq; // 省市
	
	public SchoolInfo() {
	}
	public SchoolInfo(String mc) {
		this.mc = mc;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getDq() {
		return dq;
	}
	public void setDq(String dq) {
		this.dq = dq;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	
	@Override
	public String toString() {
		return "SchoolInfo [id=" + id + ", vacancy=" + vacancy + ", mc=" + mc
				+ ", dm=" + dm + ", dq=" + dq + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof SchoolInfo) {
			return this.mc.equals(((SchoolInfo)obj).getMc());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getMc().hashCode();
	}
	
}
