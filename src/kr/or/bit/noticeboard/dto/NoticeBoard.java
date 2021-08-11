package kr.or.bit.noticeboard.dto;

import java.sql.Date;

public class NoticeBoard {
	private int N_NUMBER;
	private String N_TITLE;
	private String N_FILENAME;
	private long N_FILESIZE;
	private String N_REAL_FILENAME;
	private int N_VIEWCOUNT;
	private String EMAIL;
	private String N_CONTENT;
	private Date N_WRITEDATE;
	
	
	public int getN_NUMBER() {
		return N_NUMBER;
	}
	public void setN_NUMBER(int n_NUMBER) {
		N_NUMBER = n_NUMBER;
	}
	public String getN_TITLE() {
		return N_TITLE;
	}
	public void setN_TITLE(String n_TITLE) {
		N_TITLE = n_TITLE;
	}
	public String getN_FILENAME() {
		return N_FILENAME;
	}
	public void setN_FILENAME(String n_FILENAME) {
		N_FILENAME = n_FILENAME;
	}
	public long getN_FILESIZE() {
		return N_FILESIZE;
	}
	public void setN_FILESIZE(long n_FILESIZE) {
		N_FILESIZE = n_FILESIZE;
	}
	public String getN_REAL_FILENAME() {
		return N_REAL_FILENAME;
	}
	public void setN_REAL_FILENAME(String n_REAL_FILENAME) {
		N_REAL_FILENAME = n_REAL_FILENAME;
	}
	public int getN_VIEWCOUNT() {
		return N_VIEWCOUNT;
	}
	public void setN_VIEWCOUNT(int n_VIEWCOUNT) {
		N_VIEWCOUNT = n_VIEWCOUNT;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	
	public String getN_CONTENT() {
		return N_CONTENT;
	}
	public void setN_CONTENT(String n_CONTENT) {
		N_CONTENT = n_CONTENT;
	}
	public Date getN_WRITEDATE() {
		return N_WRITEDATE;
	}
	public void setN_WRITEDATE(Date n_WRITEDATE) {
		N_WRITEDATE = n_WRITEDATE;
	}
	public NoticeBoard() {
	}
	public NoticeBoard(int n_NUMBER, String n_TITLE, String n_FILENAME, long n_FILESIZE, String n_REAL_FILENAME,
			int n_VIEWCOUNT, String eMAIL, String n_CONTENT, Date n_WRITEDATE) {
		super();
		N_NUMBER = n_NUMBER;
		N_TITLE = n_TITLE;
		N_FILENAME = n_FILENAME;
		N_FILESIZE = n_FILESIZE;
		N_REAL_FILENAME = n_REAL_FILENAME;
		N_VIEWCOUNT = n_VIEWCOUNT;
		EMAIL = eMAIL;
		N_CONTENT = n_CONTENT;
		N_WRITEDATE = n_WRITEDATE;
	}
	
	
}
