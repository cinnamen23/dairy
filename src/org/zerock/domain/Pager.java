package org.zerock.domain;

public class Pager {

	//현재 페이지 번호
	private int current;
	//총게시물 수 아닌가? 
	private int total;
	//start 시작하는 
	private int start;
	//end
	private int end;
	//prev
	private boolean prev;
	//next
	private boolean next;
	
	//default 
	private double viewCount = 10.0;
	
	
	public Pager(int pageNum, int totalCount){
		
		if(pageNum <= 0){     //이거는 페이지 파라미터가 이상한거 받으면 기본적으로 페이지 넘을 1로 해준다는건가?
			pageNum = 1;
		}
		
		this.current = pageNum;  //현재 페이지 
		this.total = totalCount;
		calcPage();
	}
	
	private void calcPage(){
		
		int tempEnd = (int)(Math.ceil(this.current/viewCount) * 10);  // 우선입력받은 현재 페이지중에서 마지막 페이지 구하기 current: 입력받은 페이지
		this.start = tempEnd - 9;									  // 현재 입력받은 페이지에서 첫번째 페이지 구하기 
		this.end = tempEnd * viewCount > total ? (int)Math.ceil(total/viewCount):tempEnd; //총 게시물 수가 지금구한  tempend보다 작을떄 조건 
		
		this.prev = this.start == 1?false:true;   //스타트가 10이나 20일때 저으로 돌아가는 로직 
		this.next = this.total > this.end * viewCount? true : false;   //현재 마지막 ㅊ페이지보다 뒤에 페이지가 더 있을때 넘어가는 로직 
	}

	
	
	public double getViewCount() {
		return viewCount;
	}

	public void setViewCount(double viewCount) {
		this.viewCount = viewCount;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Pager [current=" + current + ", total=" + total + ", start=" + start + ", end=" + end + ", prev=" + prev
				+ ", next=" + next + "]";
	}
	
	
	
}




