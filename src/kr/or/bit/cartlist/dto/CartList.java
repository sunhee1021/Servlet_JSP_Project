package kr.or.bit.cartlist.dto;

public class CartList {
	private String contentId;
	private String email;

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CartList [contentId=" + contentId + ", email=" + email + "]";
	}

}
