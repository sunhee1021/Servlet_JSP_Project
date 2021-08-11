package kr.or.bit.action;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	//추상함수, 반드시 parameter로 controller에 doProcess의 parameter인
	//HttpServletRequest request, HttpServletResponse response 를 넣어야 함
    //당신이 만약 Action interface를 구현한다면
    //강제사항 : execute 함수를 반드시 구현해야 함
	//execute() { return new ActionForward();}
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
}
