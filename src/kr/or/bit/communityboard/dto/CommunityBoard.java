package kr.or.bit.communityboard.dto;

import java.util.Date;

public class CommunityBoard {
	private int cNumber;
	private String cTitle;
	private String cContent;
	private Date cWritedate;
	private String cFilename;
	private int cRefer;
	private int cDepth;
	private int cStep;
	private int cViewcount;
	private String email;
	
	
	public CommunityBoard() {}

	public CommunityBoard(int cNumber, String cTitle, String cContent, Date cWritedate, String cFilename, int cRefer,
			int cDepth, int cStep, int cViewcount, String email) {
		super();
		this.cNumber = cNumber;
		this.cTitle = cTitle;
		this.cContent = cContent;
		this.cWritedate = cWritedate;
		this.cFilename = cFilename;
		this.cRefer = cRefer;
		this.cDepth = cDepth;
		this.cStep = cStep;
		this.cViewcount = cViewcount;
		this.email = email;
	}

	public int getcNumber() {
		return cNumber;
	}

	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}

	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public Date getcWritedate() {
		return cWritedate;
	}

	public void setcWritedate(Date cWritedate) {
		this.cWritedate = cWritedate;
	}

	public String getcFilename() {
		return cFilename;
	}

	public void setcFilename(String cFilename) {
		this.cFilename = cFilename;
	}

	public int getcViewcount() {
		return cViewcount;
	}

	public void setcViewcount(int cViewcount) {
		this.cViewcount = cViewcount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CommunityBoard [cNumber=" + cNumber + ", cTitle=" + cTitle + ", cContent=" + cContent + ", cWritedate="
				+ cWritedate + ", cFilename=" + cFilename + ", cRefer=" + cRefer + ", cDepth=" + cDepth + ", cStep="
				+ cStep + ", cViewcount=" + cViewcount + ", email=" + email + "]";
	}

}
