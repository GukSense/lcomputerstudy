package com.lcomputerstudy.testmvc.service;

import java.util.List;

import com.lcomputerstudy.testmvc.dao.CommentDAO;
import com.lcomputerstudy.testmvc.vo.Comment;

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
	public List<Comment> getCommentList(Comment comment) {
		return dao.getCommentList(comment);
	}
	public void commentRegistration(Comment comment) {
		dao.commentRegistration(comment);
	}
	public void deleteComment(Comment comment) {
		dao.deleteComment(comment);
	}
	public void editComment(Comment comment) {
		dao.editComment(comment);
	}
	public void replyComment(Comment comment) {
		dao.replyComment(comment);
	}
}
