package kr.or.bit.sunboard.dto;

import java.sql.Date;

public class SunBoardDTO {
	private int c_number;
	private String title;
	private String content;
	private Date writedate; //default SYSDATE
	private String filename;
	private int viewcount; //조회수
	private String email;
	private long filesize;
	
	private int refer;  
	private int depth; //들여쓰기
	private int step;  //글 순서
	
	private String nickname;
	private String filerealname;
	
	public SunBoardDTO() {
		super();
	}

	public SunBoardDTO(int c_number, String title, String content, Date writedate, String filename, int viewcount,
			String email, int refer, int depth, int step, String nickname,String filerealname,long filesize) {
		super();
		this.c_number = c_number;
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
	
	public SunBoardDTO(int c_number, String title, String content, Date writedate, String filename, int viewcount,
			String email, int refer, int depth, int step, String filerealname,long filesize) {
		super();
		this.c_number = c_number;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.filename = filename;
		this.viewcount = viewcount;
		this.email = email;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.filerealname = filerealname;
		this.filesize = filesize;
	}

	public int getC_number() {
		return c_number;
	}

	public void setC_number(int c_number) {
		this.c_number = c_number;
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
		return "SunBoardDTO [c_number=" + c_number + ", title=" + title + ", content=" + content + ", writedate="
				+ writedate + ", filename=" + filename + ", viewcount=" + viewcount + ", email=" + email + ", filesize="
				+ filesize + ", refer=" + refer + ", depth=" + depth + ", step=" + step + ", nickname=" + nickname
				+ ", filerealname=" + filerealname + "]";
	}

	
	
}
