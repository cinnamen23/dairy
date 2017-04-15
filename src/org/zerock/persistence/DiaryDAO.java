package org.zerock.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.zerock.domain.Diary;


public class DiaryDAO {  //클래스 생성

	
	
	private static final DiaryDAO instance = new DiaryDAO();  //스타틱으로 클래스 변수 만들어두기   하나으 ㅣ클래스에[서 하나밖에 못만드나 보네  
	
	public static DiaryDAO getInstance() {            //그 클래스 변수 얻어오는 메소드 스타틱에 선언 

		return instance;
	} 
	
	
	
	
	private DiaryDAO() {  //이거 있으나 없으나 그냥 생성되지 않나 ?

	}

	static {			//이거는 스타틱으로 우선 만들어 놓는거 클래스만 로딩해놓는거  이렇게도 쓸수있구나 ....
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	public int getListCount()throws Exception{
		String sql = "select count(dno) from tbl_diary ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int total = -1;
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");

			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				total = rs.getInt(1);
				
			}

			pstmt.close();
			rs.close();
			System.out.println(con);


		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {try {rs.close();} catch (Exception e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if (con != null) {try {con.close();} catch (Exception e) {}}
			
		}
		
		return total;
		
	}
	
	
	
	
	
	
	
	public List<Diary> getList(int page)throws Exception{
	
		StringBuffer buffer = new StringBuffer();
		
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
		
		String sql = buffer.toString();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Diary> list = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, page);
			
			System.out.println(con);

			rs = pstmt.executeQuery();
			
			while(rs.next()){  //여기서 나오는얘들을 다 Diary형으로 저장해서 다 넣는다 
				
				Diary diary = new Diary();
				diary.setDno(rs.getInt(1));
				diary.setTitle(rs.getString(2));
				diary.setContent(rs.getString(3));
				diary.setWriter(rs.getString(4));
				diary.setRegdate(rs.getDate(5));
				diary.setUpdateDate(rs.getDate(6));
				
				list.add(diary);   //try catch 밖에서 선언하고 여기서 하면 저장된다 
				
			}
			

			//다이어리 하나하나에 있는거 출력 Diary 클래스에는 toString 가 있겠네 
			list.forEach(diary ->{    //diary->  이걸 람다식   이거를 실행하면서 diary를 파라미터로 전달한다.
				System.out.println(diary);
			});
			
			
			
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {//닫아주기 코드 이거는 무조건 해줘야된다 

			if (rs != null) {try {rs.close();} catch (Exception e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if (con != null) {try {con.close();} catch (Exception e) {}}
			
		} 
		return list;   //담은거 리턴해주기 
	}

	
	
	
	
	public void create(Diary diary) throws Exception {
		
		String sql = "insert into tbl_diary (dno, title,content,writer) values (seq_diary.nextval,?,?,?)"; 

		String sqlFile = "insert into tbl_diary_file (fno, dno, filename) values (seq_diary_file.nextval,seq_diary.currval,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.28:1521:XE", "seunggyu", "1234");

			con.setAutoCommit(false);  //이게 쿼리문을 두개 날려서 그런거 자동으로 닫아주려고 ...?

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, diary.getTitle());
			pstmt.setString(2, diary.getContent());
			pstmt.setString(3, diary.getWriter());

			int count = pstmt.executeUpdate();  //executeUpdate의 리턴값은 작용한 열의 개수(갱신 카운트로 간주되는)를 나타내는 정수

			System.out.println("tbl_diary insert count: " + count); //count 가 0이면 실행sql은 영들에 영향을 주지 않는 INSERT, UPDATE 또는 DELETE

			pstmt.close();  //첫번째 쿼리의 완벽한 실행을 위해서 닫아주기??

			if (diary.getFileLists() != null && diary.getFileLists().size() > 0) { //각각의 다이어리가 가지고 있는 파일리스트의 값을 뽑아내느거 널이면 아무것도 안하기 
				
				for (String fileName : diary.getFileLists()) {  //이걸로 데이타 베이스에 tbl_diary_file 쪽에 추가해주는거 

					pstmt = con.prepareStatement(sqlFile);
					pstmt.setString(1, fileName);

					int fileCount = pstmt.executeUpdate();
					System.out.println("file insert: " + fileCount);
					
					pstmt.close();  //잘 닫아주기 
				}
			}
			System.out.println(con);

			con.commit();

		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();

		} finally {

			if (rs != null) {try {rs.close();} catch (Exception e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if (con != null) {try {con.close();} catch (Exception e) {}}
		}

	}

}
