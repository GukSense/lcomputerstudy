package com.lcomputerstudy.testmvc.vo;

public class Comment {
	private User id;
	private String date;
	private String content;
	private int comment_num;
	private String Writer;
	private int b_idx;
	private int order;
	private int groupNum;
	private int depth;
	private User u_idx;
	private int user_idx;
	
	
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public User getU_idx() {
		return u_idx;
	}
	public void setU_idx(User u_idx) {
		this.u_idx = u_idx;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getWriter() {
		return Writer;
	}
	public void setWriter(String writer) {
		Writer = writer;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public User getId() {
		return id;
	}
	public void setId(User id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	

	
	
}
