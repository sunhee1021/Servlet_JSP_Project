package kr.or.bit.ainboard.dto;

import java.util.Date;

public class AinReply {
	private int crNumber;
	private String content;
	private Date writedate;
	private String email;
	private int cNumber;
	
	private String nickname;
	
	public AinReply() {}
	
	//닉네임 없는 버전
	public AinReply(int crNumber, String content, Date writedate, String email, int cNumber) {
		super();
		this.crNumber = crNumber;
		this.content = content;
		this.writedate = writedate;
		this.email = email;
		this.cNumber = cNumber;
	}
	
	//닉네임 유버전
	public AinReply(int crNumber, String content, Date writedate, String email, int cNumber, String nickname) {
		super();
		this.crNumber = crNumber;
		this.content = content;
		this.writedate = writedate;
		this.email = email;
		this.cNumber = cNumber;
		this.nickname = nickname;
	}
	
	public int getCrNumber() {
		return crNumber;
	}
	public void setCrNumber(int crNumber) {
		this.crNumber = crNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "AinReply [crNumber=" + crNumber + ", content=" + content + ", writedate=" + writedate + ", email="
				+ email + ", cNumber=" + cNumber + "]";
	}
	
}
