package org.zerock.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.domain.MemberVO;
import org.zerock.domain.Pager;
import org.zerock.persistence.DiaryDAO;

/**
 * Servlet implementation class DiaryListController
 */
@WebServlet("/list")
public class DiaryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("diary list controller get");
		
		int page = 1;     //우선 1이다 
		try{
			page = Integer.parseInt(request.getParameter("pageNum"));  //pagenum 받으면 
		}catch(Exception e){
		}

		try {
			
			request.setAttribute("list", DiaryDAO.getInstance().getList(page));  //이렇게 해두면 jsp 가서 'lsit'라는걸로  쓸수 있다  ★셋팅해놓는거
			
			int total = DiaryDAO.getInstance().getListCount();
			
			request.setAttribute("pager", new Pager(page,total));  //jsp에서 'pager'로 해서 사용할수 있다.  ★셋팅해놓는거
		
			MemberVO vo=new MemberVO();   
			vo.setUserid("안녕세상아 ");
			vo.setUserpw("안녕핫요 ");
			request.setAttribute("member", vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/diary/list.jsp").forward(request, response);   
		
	}


}
