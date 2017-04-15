package org.zerock.domain;

public class MemberVO {  //이거 만드는 이유가 무슨 맵으로 뺄려고 라는데 ...

	private String userid,userpw;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + "]";
	}
	
	
	
	
}
