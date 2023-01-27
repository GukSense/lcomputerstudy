package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.vo.Board;

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
	
	public static ArrayList<Board> getBoardList(){
		return dao.getBoardList();
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
}
