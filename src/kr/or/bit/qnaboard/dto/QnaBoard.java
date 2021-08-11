package kr.or.bit.qnaboard.dto;

import java.sql.Date;

public class QnaBoard {
	
	private int qnaNumber;
	private String qnaTitle;
	private String qnaFilename;
	private long qnaFilesize;
	private String qnaRealFilename;
	private String qnaContent;
	
	private Date qnaWritedate;
	private int qnaRefer;
	private int qnaDepth;
	private int qnaStep;
	private int qnaViewcount;
	private String email;
	private String qnaPassword;
	private int qnaStatus;
	
	// member join
	private String division;
	private String nickname;

	
	
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getQnaStatus() {
		return qnaStatus;
	}

	public void setQnaStatus(int qnaStatus) {
		this.qnaStatus = qnaStatus;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public int getQnaNumber() {
		return qnaNumber;
	}

	public void setQnaNumber(int qnaNumber) {
		this.qnaNumber = qnaNumber;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaFilename() {
		return qnaFilename;
	}

	public void setQnaFilename(String qnaFilename) {
		this.qnaFilename = qnaFilename;
	}

	public long getQnaFilesize() {
		return qnaFilesize;
	}

	public void setQnaFilesize(long qnaFilesize) {
		this.qnaFilesize = qnaFilesize;
	}

	public String getQnaRealFilename() {
		return qnaRealFilename;
	}

	public void setQnaRealFilename(String qnaRealFilename) {
		this.qnaRealFilename = qnaRealFilename;
	}

	public Date getQnaWritedate() {
		return qnaWritedate;
	}

	public void setQnaWritedate(Date qnaWritedate) {
		this.qnaWritedate = qnaWritedate;
	}

	public int getQnaRefer() {
		return qnaRefer;
	}

	public void setQnaRefer(int qnaRefer) {
		this.qnaRefer = qnaRefer;
	}

	public int getQnaDepth() {
		return qnaDepth;
	}

	public void setQnaDepth(int qnaDepth) {
		this.qnaDepth = qnaDepth;
	}

	public int getQnaStep() {
		return qnaStep;
	}

	public void setQnaStep(int qnaStep) {
		this.qnaStep = qnaStep;
	}

	public int getQnaViewcount() {
		return qnaViewcount;
	}

	public void setQnaViewcount(int qnaViewcount) {
		this.qnaViewcount = qnaViewcount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getQnaPassword() {
		return qnaPassword;
	}

	public void setQnaPassword(String qnaPassword) {
		this.qnaPassword = qnaPassword;
	}

	@Override
	public String toString() {
		return "QnaBoard [qnaNumber=" + qnaNumber + ", qnaTitle=" + qnaTitle + ", qnaFilename=" + qnaFilename
				+ ", qnaFilesize=" + qnaFilesize + ", qnaRealFilename=" + qnaRealFilename + ", qnaWritedate="
				+ qnaWritedate + ", qnaRefer=" + qnaRefer + ", qnaDepth=" + qnaDepth + ", qnaStep=" + qnaStep
				+ ", qnaViewcount=" + qnaViewcount + ", email=" + email + ", qnaPassword=" + qnaPassword + "]";
	}

}
