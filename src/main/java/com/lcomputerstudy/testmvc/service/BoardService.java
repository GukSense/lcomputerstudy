package com.lcomputerstudy.testmvc.service;

import java.util.List;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;

public class BoardService {
	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	public BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
	public void writingRegiStraion(Board board) {
		 dao.writingRegiStraion(board);
	} 
	
	public List<Board> getBoardList(Pagination pagination){
		return dao.getBoardList(pagination);
	}
	public Board viewContents(Board board) {
		return dao.viewContents(board);
	}
	public void hitsBoard(Board board) {
		dao.hitsBoard(board);
	}
	public void editContent(Board board) {
		dao.editContent(board);
	}
	public void deleteBoard(Board board) {
		dao.deleteBoard(board);
	}
	public void replyBoard(Board board) {
		dao.replyBoard(board);
	}
	public int getBoardCount() {
		return dao.getBoardCount();
	}

}
