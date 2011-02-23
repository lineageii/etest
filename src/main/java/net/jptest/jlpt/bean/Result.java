package net.jptest.jlpt.bean;

public class Result {
	private String errorNum;
	
	private String retVal;

	public Result() {
	}

	public Result(String errorNum, String retVal) {
		this.errorNum = errorNum;
		this.retVal = retVal;
	}

	public String getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(String errorNum) {
		this.errorNum = errorNum;
	}

	public String getRetVal() {
		return retVal;
	}

	public void setRetVal(String retVal) {
		this.retVal = retVal;
	}
	
	
}
