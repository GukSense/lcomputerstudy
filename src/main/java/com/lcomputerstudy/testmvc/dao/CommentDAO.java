package com.lcomputerstudy.testmvc.dao;

public class CommentDAO {
	private static CommentDAO dao = null;
	public CommentDAO() {
		
	}
	public static CommentDAO getInstance() {
		if(dao==null) {
			dao = new CommentDAO();
		}
		return dao;
	}
}
