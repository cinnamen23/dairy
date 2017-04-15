package org.zerock.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/login","/doA","/doB"})  //留됯퀬�떢�쓣�븣�뒗 �씠�윴�떇�쑝濡� �쟻�쑝硫� �맖 諛곗뿴�씠 �맂�떎洹몃Ⅴ�꽕  {} <- �씠嫄곕�� 留뚮뱾�뼱二쇰㈃   嫄곗튌 寃껊뱾 紐⑤몢�떎 
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
			//�꽭�뀡遺��꽣 留됱븘遊낆떆�떎 
		HttpServletRequest httpRequest= (HttpServletRequest)request;
		HttpServletResponse httpResponse= (HttpServletResponse)response;
		
		System.out.println("Login filter run......");
		
		//荑좏궎 �뵲吏�湲�
		Cookie[] cks = httpRequest.getCookies();
		
		if(cks==null||cks.length==0){
			httpResponse.sendRedirect("/diary/login3"); //튕구기 
		}
		
		
		
		chain.doFilter(request, response);
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
