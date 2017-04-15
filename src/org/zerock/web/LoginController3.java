package org.zerock.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.domain.MemberVO;

/**
 * Servlet implementation class LoginController3
 */
@WebServlet("/login")
public class LoginController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login controller doget............");
		
		request.getRequestDispatcher("/WEB-INF/diary/login.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("login controller dogpost............");
		
		String userid=request.getParameter("userid");
		String userpw=request.getParameter("userpw");

		String remember = request.getParameter("rememberme");
		System.out.println("remember"+remember);
		
		
		//�솗�씤怨쇱젙  �솗�씤�빐�꽌 �굹�삩 寃곌낵
		MemberVO vo=new MemberVO();   
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		
		System.out.println("=================================================================");
		System.out.println("remember: "+remember);
		
		if(remember!=null&&remember.equals("on")){   //�솢 on �씠 �뱾�뼱媛�吏� ?
			
			Cookie memberCookie= new Cookie("member", userid); // 荑좏궎 留뚮뱾湲� 
			memberCookie.setMaxAge(60*10);   //荑좏궎 �윴���엫  10遺� 
			
			//荑좏궎 留뚮뱾怨� �윴���엫 吏��젙�빐�꽌 �돞 
			response.addCookie(memberCookie);  //�뿬湲곕떎媛� �꽭�뀡�쓽 �븘�씠�뵒媛믪쓣 �꽔�쑝硫� �뼱�뼥源�?  �꽭�뀡�쓽 �븘�씠�뵒瑜� �꽔�뼱�룄  媛��뒫�븿  �씠�젃寃� �븯硫� 荑좏궎吏�留� �븞�쟾�빐吏꾨떎 �꽭�뀡�븘�씠�뵒留� 蹂댁씠�땲源� �븞�젙�빐吏꾨떎 �씠嫄몃줈 �뵒鍮꾩뿉�꽌 媛�吏�怨� �삤硫� �븞�쟾�빐吏� 
			
		}
		
		request.getSession().setAttribute("member", vo);  //vo�뒗 媛앹껜怨� �쐞�뿉 userid �뒗 臾몄옄�뿴�씠�땲源� 臾몄옄�뿴�쓣 媛�吏�怨� �삱�븣 臾몄젣媛��맂�떎 . vo.�뀒xxx 濡� 媛��졇���빞�릺�땲源�  vo濡� �븞�꽆寃⑤룄 �맂�떎怨� �븯�꽕 ? �뀕�뀕�뀕�뀕
		//vo濡� �븞 �꽔�뼱以섎룄 �맖 
		
		response.sendRedirect("/list");
		
	}

}
