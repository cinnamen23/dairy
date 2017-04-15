package org.zerock.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayController
 */
@WebServlet("/display")
public class DisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("DisplayController do get...");
		
		String fileName = request.getParameter("name");
		
		if(fileName == null){
			System.out.println("file Name is null");
			return;
		}
		
		if(fileName.endsWith(".jpg")){
			response.setContentType("image/jpeg");
		}else{
			response.setContentType("application/octet-stream");
			
			String enFileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="+ enFileName);
		}
		
		FileInputStream fin = new FileInputStream("C:\\zzz\\upload\\" + fileName);
		OutputStream os = response.getOutputStream();
		
		byte[] buffer = new byte[1024*8];
		
		while(true){
			int count = fin.read(buffer);
			if(count == -1){ break;}
			os.write(buffer,0,count);
		}
		fin.close();
		os.close();
		
		
	}
}







