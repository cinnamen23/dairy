package org.zerock.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Diary {

	private int dno;
	private String title,content,writer;
	private Date regdate, updateDate;
	
	private List<String> fileLists;  //파일명 담을라고 만든거구나 이게 
	
	
	public void addFile(String fileName){
		if(fileLists == null){
			this.fileLists = new ArrayList<String>();
		}
		fileLists.add(fileName);
	}
	
	
	
	public List<String> getFileLists() {
		return fileLists;
	}



	public void setFileLists(List<String> fileLists) {
		this.fileLists = fileLists;
	}



	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	@Override
	public String toString() {
		return "Diary [dno=" + dno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", updateDate=" + updateDate + ", fileLists=" + fileLists + "]";
	}

	
}
