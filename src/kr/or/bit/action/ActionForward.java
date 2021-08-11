package kr.or.bit.action;

/*
 * 이 클래스의 역할?
 * 
 * servlet 이 요청을 받으면
 * 1. 화면 보여주세요
 * 2. 그 화면에서 입력한거 처리해주세요(로직제어)
 * 
 * 화면을 처리할거냐, 로직을 처리할거냐를 담고 있음 & view의 경로 (ActionForward 클래스가)
 * */
public class ActionForward {
	
	private boolean isRedirect = false;  //다른화면으로 이동할거야 말거야(화면전환여부)를 담는 속성 isRedirect
	private String path = null;  		 //이동경로
	
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
