package org.zerock.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

  //달리고 

/**
 * Servlet Filter implementation class sampleFilter
 */
@WebFilter("/login")      //필터요 ~ 
public class sampleFilter implements Filter {

	private static final Logger logger
	=Logger.getLogger(sampleFilter.class);    //이 클래스에서 쓸려고 logger 을 
	//웹에는 5가지 레벨이 있어요 debug info warn error  fafal
    /**
     * Default constructor. 
     */
    public sampleFilter() {
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
		//얘는 한단계ㅒ 위의 추상화를 실시한다. 파라미터가 없을때 
		System.out.println("Sample Filter .....doFilter....");
		
	    logger.debug("debug");    // sysout 같은거 
		logger.info("INFO");		// sysout 같은거 
		logger.warn("warn");		// sysout 같은거 
		logger.error("ERROR");		// sysout 같은거 
		logger.fatal("FATAL");		// sysout 같은거 
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
