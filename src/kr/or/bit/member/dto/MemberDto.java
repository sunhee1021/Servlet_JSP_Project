package kr.or.bit.member.dto;
public class MemberDto {
	private String email;
	private String password;
	private String division;
	private String nickname;
	public MemberDto() {
		super();
	}
	public MemberDto(String email) {
		super();
		this.email = email;
	}
	public MemberDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public MemberDto(String email, String password, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDivision() {
		return division;
	}
	public String setDivision(String division) {
		return this.division = division;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Member [email=" + email + ", password=" + password + ", division=" + division + ", nickname=" + nickname
				+ "]";
	}
}