package kr.or.bit.communityboard.dto;

import java.util.Date;

public class CommunityReplyBoard {
	private int crNumber;
	private String crContent;
	private Date crWritedate;
	private String email;
	private int cNumber;
	
	public CommunityReplyBoard() {}


	public CommunityReplyBoard(int crNumber, String crContent, Date crWritedate, String email, int cNumber) {
		super();
		this.crNumber = crNumber;
		this.crContent = crContent;
		this.crWritedate = crWritedate;
		this.email = email;
		this.cNumber = cNumber;
	}

	public int getCrNumber() {
		return crNumber;
	}

	public void setCrNumber(int crNumber) {
		this.crNumber = crNumber;
	}

	public String getCrContent() {
		return crContent;
	}

	public void setCrContent(String crContent) {
		this.crContent = crContent;
	}

	public Date getCrWritedate() {
		return crWritedate;
	}

	public void setCrWritedate(Date crWritedate) {
		this.crWritedate = crWritedate;
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

	@Override
	public String toString() {
		return "CommunityReplyBoard [crNumber=" + crNumber + ", crContent=" + crContent + ", crWritedate=" + crWritedate
				+ ", email=" + email + ", cNumber=" + cNumber + "]";
	}

}
