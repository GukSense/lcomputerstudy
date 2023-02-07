package com.lcomputerstudy.testmvc.vo;

public class Search {
	private String search_target;
	private String search_keyword;
	private String search_category;
	
	public String FilterIfHaveBoth(String column) {
		String where ="";
		
		switch(column) {
		case"title":
			where = "WHERE b_title LIKE ? AND b_category =?";
			break;
		case"title_content":
			where = "WHERE b_content LIKE ? AND b_category =?";
			break;
		case"nick_name":
			where = "WHERE b_writer LIKE ? AND b_category =?";
			break;
		}
	
		return where;
	}
	
	public String FilterHaveOnlyOne(String column) {
		String where ="";
		
		switch(column!= null ? column:"null") {
		case"title":
			where = "WHERE b_title LIKE ?";
			break;
		case"title_content":
			where = "WHERE b_content LIKE ?";
			break;
		case"nick_name":
			where = "WHERE b_writer LIKE ?";
			break;
		case "null":
			where = "";
			break;
		}	
		return where;
	}
	
	
	public String getSearch_category() {
		return search_category;
	}
	public void setSearch_category(String search_category) {
		this.search_category = search_category;
	}
	public String getSearch_target() {
		return search_target;
	}
	public void setSearch_target(String search_target) {
		this.search_target = search_target;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	
	
}
