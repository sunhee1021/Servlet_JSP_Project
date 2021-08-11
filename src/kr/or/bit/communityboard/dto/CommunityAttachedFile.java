package kr.or.bit.communityboard.dto;

public class CommunityAttachedFile {
	private int cFilesize;
	private String cFilename;
	private String cRealFilename;
	private int cNumber;

	public int getcFilesize() {
		return cFilesize;
	}

	public void setcFilesize(int cFilesize) {
		this.cFilesize = cFilesize;
	}

	public String getcFilename() {
		return cFilename;
	}

	public void setcFilename(String cFilename) {
		this.cFilename = cFilename;
	}

	public String getcRealFilename() {
		return cRealFilename;
	}

	public void setcRealFilename(String cRealFilename) {
		this.cRealFilename = cRealFilename;
	}

	public int getcNumber() {
		return cNumber;
	}

	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}

	@Override
	public String toString() {
		return "CommunityAttachedFile [cFilesize=" + cFilesize + ", cFilename=" + cFilename + ", cRealFilename="
				+ cRealFilename + ", cNumber=" + cNumber + "]";
	}

}
