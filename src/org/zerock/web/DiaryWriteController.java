package org.zerock.web;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.zerock.domain.Diary;
import org.zerock.persistence.DiaryDAO;

/**
 * Servlet implementation class DiaryWriteController
 */
@WebServlet("/write")
@MultipartConfig
public class DiaryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		System.out.println("do get writer");
		System.out.println(request.getSession().getServletContext().getMajorVersion());
		
		
		request.getRequestDispatcher("/WEB-INF/diary/write.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8"); //입력받은 문자 안깨지게 하려고 
		
		System.out.println("do post writer");   //포스트 들어왔다 
		
		Diary diary = new Diary();                      //get으로 보이는 곳에서 값을 입력하고 post로 바뀌면서  우선 값을 받는거 까지는 알겠고 
		diary.setTitle(request.getParameter("title"));   
		diary.setContent(request.getParameter("content"));
		diary.setWriter(request.getParameter("writer"));
		
		
		
		
		//이 파트라는놈이 뭔가 그 파일들을 나누어서 저장하는것 같으다.
		
		
		
		Collection<Part> parts = request.getParts(); //뭔가 데이터 주고받고 하기 위한 무언가 같음 
		
		parts.forEach(part ->{
			
			String name = part.getName();     //이농은 jsp에 name가 file1,file2,file3 인 놈을 차례대로 가지고 오는거 
			String type = part.getContentType();  //데이터 타입을 지가 알아서 가져온다 좋은거네 image/jpeg,application/haansoftxlsx
			if(type == null){
				return;
			}
			
			String fileName = part.getSubmittedFileName(); //파일 이름 가져오기 bbb.mp3,mario.jpg,test엑셀.xlsx 이런거 
			System.out.println(name+": " + fileName +"+String type = part.getContentType() 값은: "+type);
			if(fileName == null || fileName.trim().length() == 0){
				return;
			}
			
			UUID uid = UUID.randomUUID();   //뭔가모를 랜덤값을 뽑아서 uid 라는곳에 저장시킨다. UUID: 16진수의 범용고유식별자
			
			String savedName = uid.toString()+"_"+fileName;
			
			System.out.println(savedName); //701834d2-5540-43c4-a885-dd333d0fd1e0_bbb.mp3 같은이름
			
			try {
				part.write("C:\\zzz\\upload\\"+savedName);  //이경로에 이 이름으로 저장이 된다. 
				diary.addFile(savedName);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		//part를 사용한 저장하기 끝 !!!!!!! 이게 그 최신방법이라고 한거 같은데 강사님이 말한 
		
		
		
		System.out.println("저장이 끝나고 나서 =======================");
		System.out.println(diary);  //한번 출력해주시고 
		
		try {
			DiaryDAO.getInstance().create(diary);  //여기서 tbl_diary 와 tbl_diary_file 데이터 베이스에 쿼리써서 내용 업데이트 하는거 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//파일 저장했으면 리스트로 이동 하하하 
		
		response.sendRedirect("/list");  //이게 아닌가 ?
		
	}

}


