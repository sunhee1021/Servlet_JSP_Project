package kr.or.bit.ainboard.dto;

import java.util.Date;

public class AinBoard {
	private int cNumber;
	private String title;
	private String content;
	private Date writedate;
	private String filename;
	private int refer;
	private int depth;
	private int step;
	private int viewcount;
	private String email;
	private String filerealname;
	private long filesize;
	
	private String nickname;
	
	public AinBoard() {
	};

	//닉네임 있는버전
	public AinBoard(int cNumber, String title, String content, Date writedate, String filename, int refer, int depth,
			int step, int viewcount, String email, String nickname, String filerealname, int filesize) {
		super();
		this.cNumber = cNumber;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.filename = filename;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.viewcount = viewcount;
		this.email = email;
		this.nickname = nickname;
		this.filerealname = filerealname;
		this.filesize = filesize;
	}

	//닉네임없는버전
	public AinBoard(int cNumber, String title, String content, Date writedate, String filename, int refer, int depth,
			int step, int viewcount, String email) {
		super();
		this.cNumber = cNumber;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.filename = filename;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.viewcount = viewcount;
		this.email = email;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFilerealname() {
		return filerealname;
	}

	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	@Override
	public String toString() {
		return "AinBoard [cNumber=" + cNumber + ", title=" + title + ", content=" + content + ", writedate=" + writedate
				+ ", filename=" + filename + ", refer=" + refer + ", depth=" + depth + ", step=" + step + ", viewcount="
				+ viewcount + ", email=" + email + "]";
	}
	
}
