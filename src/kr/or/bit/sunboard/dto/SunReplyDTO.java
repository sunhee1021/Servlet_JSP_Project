package kr.or.bit.sunboard.dto;

import java.util.Date;

public class SunReplyDTO {
	
	private int cr_number;	//PK
	private String content;
	private Date writedate;
	private String email;  //FK	
	private int c_number;  //FK
	private String nickname;
	
	public SunReplyDTO() {
		super();
	}
	

	public SunReplyDTO(int cr_number, String content, Date writedate, String email, int c_number) {
		super();
		this.cr_number = cr_number;
		this.content = content;
		this.writedate = writedate;
		this.email = email;
		this.c_number = c_number;
	}
	
	public SunReplyDTO(int cr_number, String content, Date writedate, String email, int c_number, String nickname) {
		super();
		this.cr_number = cr_number;
		this.content = content;
		this.writedate = writedate;
		this.email = email;
		this.c_number = c_number;
		this.nickname = nickname;
	}
	
	public int getCr_number() {
		return cr_number;
	}

	public void setCr_number(int cr_number) {
		this.cr_number = cr_number;
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

	public int getC_number() {
		return c_number;
	}

	public void setC_number(int c_number) {
		this.c_number = c_number;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@Override
	public String toString() {
		return "SunReplyDTO [cr_number=" + cr_number + ", content=" + content + ", writedate=" + writedate + ", email="
				+ email + ", c_number=" + c_number + ", nickname=" + nickname + "]";
	}
	
	
}
