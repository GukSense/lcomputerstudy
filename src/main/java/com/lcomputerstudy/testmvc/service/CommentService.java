package com.lcomputerstudy.testmvc.service;

import com.lcomputerstudy.testmvc.dao.CommentDAO;

public class CommentService {
	private static CommentService service = null;
	private static CommentDAO dao = null;
	
	public CommentService() {
	
	}
	public static CommentService getInstance() {
		if(service==null) {
			service = new CommentService();
			dao = CommentDAO.getInstance();
		}
		return service;
	}
}
