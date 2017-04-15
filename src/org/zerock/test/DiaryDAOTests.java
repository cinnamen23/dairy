package org.zerock.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.zerock.domain.Diary;

public class DiaryDAOTests {

	

	
	@Test
	public void testList()throws Exception{
		
		
		StringBuffer buffer = new StringBuffer();  //버퍼에 넣어보려구 
		
		//페이징 쿼리 
		buffer.append("select dno, title,content, writer, regdate, updatedate ");
		buffer.append("from ");
		buffer.append("( ");
		buffer.append("select rownum rn, dno, title,content, writer, regdate, updatedate " );
		buffer.append("from tbl_diary ");
		buffer.append("where dno > 0 ");
		buffer.append("and rownum <= ? * 10 ");
		buffer.append("order by dno desc ");
		buffer.append(") ");
		buffer.append("where rn > (? -1) *10 "); 
		
		String sql = buffer.toString();  //페이징 쿼리 세팅완료 
		
		
		int page = 2;		 //자 페이지에 2라고 받으면 

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Diary> list = new ArrayList<>();
		
		
		try {
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, page);
			
			System.out.println("jdbc랑 연결확인 테스트: "+con);  //연결됬습니다.. 

			rs = pstmt.executeQuery(); 
			//여기서 페이지당 의 조회 결과가 리턴값으로 나오니까 
			//이거 리턴값은 ResultSet 클래스의 객체가 나온다 
			
			while(rs.next()){   //dairy에 찍어서 리스트로 저장을 합니다 . 값이 없을떄까지 돈다 돌아요 돕니다 
				
				Diary diary = new Diary();
				diary.setDno(rs.getInt(1));
				diary.setTitle(rs.getString(2));
				diary.setContent(rs.getString(3));
				diary.setWriter(rs.getString(4));
				diary.setRegdate(rs.getDate(5));
				diary.setUpdateDate(rs.getDate(6));
				
				list.add(diary);    //값 세팅해서 리스트에 넣습니다.
				
			}
			
			//list 출력하는거   하나하나 전달해서 출력 
			  
			list.forEach(diary ->{
				System.out.println(diary);
			});
			
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (rs != null) {try {rs.close();} catch (Exception e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if (con != null) {try {con.close();} catch (Exception e) {}}

		}
	}

	
	
	
	
	@Test
	//tbl_diary 과 tbl_diary_file 에 데이타를 삽입하기 테스트  삽입 잘되네요 하하하
	public void testCreateWithFiles() throws Exception {
		String sql = "insert into tbl_diary (dno, title,content,writer) values (seq_diary.nextval,?,?,?)"; //이거는 한번 실행하고 
		
		String sqlFile = "insert into tbl_diary_file (fno, dno, filename) values (seq_diary_file.nextval,seq_diary.currval,?)";//이거는 파일수에 따라서 실행 되야 ,,,, 

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Diary diary = new Diary();
		diary.setTitle("잘들어갔니");
		diary.setContent("한글 테스트");
		diary.setWriter("user00");

		diary.addFile("aaa.jpg");
		diary.addFile("bbb.jpg");
		diary.addFile("ccc.xls");

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");

			con.setAutoCommit(false);  
			//기본적으로 Connection 객체에 오토 커밋이 자동으로 실행이되는데  여러개의 쿼리문을 하나의 작업으로 수행되어야 하기 떄문에 jsp의 오토커밋이 자동으로 작동
			//하지 못하게 하기 위한 명령 기본은 con.setAutoCommit(true); 로 되어있다. 

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, diary.getTitle());
			pstmt.setString(2, diary.getContent());
			pstmt.setString(3, diary.getWriter());

			int count = pstmt.executeUpdate(); //executeUpdate의 리턴값은 작용한 열의 개수(갱신 카운트로 간주되는)를 나타내는 정수

			System.out.println("이거 1 나오려나? tbl_diary insert count: " + count);
			

			pstmt.close();  //닫아주는이유는  다음쿼리 실행할라고 닫아주는거 같은데 ..

			for (String fileName : diary.getFileLists()) {  //다이어리에 파일이름들이 있는 리스트를 얻어와서 삽입하는거 

				pstmt = con.prepareStatement(sqlFile);
				pstmt.setString(1, fileName);

				int fileCount = pstmt.executeUpdate();
				System.out.println("이거 3나오려나?  tbl_diary_file insert: " + fileCount);
				System.out.println("int fileCount = pstmt.executeUpdate(); 누적은 안됨 누적되느줄.. ");
			}

			System.out.println("jdbc랑 연결확인 테스트: "+con);

			con.commit();  //위에서 오토커밋 false로 바꿔주고 여기서 커밋 해주는거 

		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();

		} finally {

			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}

		}

	}
	
	
	
	@Test
	//tbl_diary 에 데이터 삽입 테스트 
	public void testCreate() throws Exception {

		String sql = "insert into tbl_diary (dno, title,content,writer) values (seq_diary.nextval,?,?,?)";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Diary diary = new Diary();
		diary.setTitle("junit test");
		diary.setContent("게시팔 만들기");
		diary.setWriter("user23");

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, diary.getTitle());
			pstmt.setString(2, diary.getContent());
			pstmt.setString(3, diary.getWriter());

			int count = pstmt.executeUpdate();   // 이게 말하는게 1이면 데이타 한줄이 삽입 밑 변경되었습니다 

			System.out.println("데이타 베이스에 INSERT, UPDATE 또는 DELETE 가 count만큼 실행되었습니다??: "+count);

			System.out.println("jdbc랑 연결확인 테스트: "+con);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}

		}

	}
	
	
	
	@Test
	//jdbc랑 연결확인 테스트 
	public void testConnection() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");

			System.out.println("jdbc랑 연결확인 테스트: "+con);  //연결완료 

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}

		}

	}

}
