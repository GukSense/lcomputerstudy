package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.CommentService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
import com.lcomputerstudy.testmvc.vo.User;



@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		User user = null;
		UserService userService = null;
		
		BoardService boardService = null;
		Board board = null;
		List<Board> boardList = null;
		int page = 1;
		int count = 0;
		int hits = 0;
		String idx = null;
		String pw = null;
		
		Comment comment = null;
		CommentService commentService = null;
		HttpSession session = null;
		
		command = checkSession(request, response, command);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch (command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if(reqPage != null) {
					page = Integer.parseInt(reqPage);
				}
				userService = UserService.getInstance();
				count = userService.getUsersCount();
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(count);
				pagination.init();
				List<User> list = userService.getUsers(pagination);
				
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				
				view = "user/list";
				
				break;
			case "/user-insert.do":
				view = "user/insert";
				break;
			case "/user-insert-process.do":
				user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
				
				view = "user/insert-result";
				break;
			case "/user-detail.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				user = userService.viewUserDetail(user);
				
				view = "user/user-detail";
				request.setAttribute("user", user);
				break;
			case "/user-edit.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				userService = UserService.getInstance();
				user = userService.viewUserDetail(user);
				
				view = "user/userEdit";
				
				request.setAttribute("user", user);
				
				break;
			case"/user-edit-process.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("edit_u_idx")));
				user.setU_id(request.getParameter("edit_id"));
				user.setU_pw(request.getParameter("edit_password"));
				user.setU_name(request.getParameter("edit_name"));
				user.setU_tel(request.getParameter("edit_tel1") + "-" + request.getParameter("edit_tel2") + "-" + request.getParameter("edit_tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.editUsers(user);
				view = "user/editProcess";
	
				break;
			case"/user-delete.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				userService.deleteUser(user);
				view = "user/delete";
				break;
			case"/user-login.do":
				view = "user/login";
				break;
			case"/user-login-process.do":
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(idx,pw);
				if(user != null) {
					session = request.getSession();
//					session.setAttribute("u_idx", user.getU_idx());
//					session.setAttribute("u_id", user.getU_id());
//					session.setAttribute("u_pw", user.getU_pw());
//					session.setAttribute("u_name", user.getU_name());
					session.setAttribute("user", user);
					
					view = "user/login-result";					
				} else {
					view = "user/login-fail";				
				}
				break;
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				view = "user/login";
				break;
			case "/access-denied.do":
				view=  "user/access-denied";
				break;
//	여기서부터 계층형 게시판	---------------------------------------------------------
				
				
			case "/board-list.do":
				boardService = new BoardService();
				boardService = BoardService.getInstance();
				Search search = new Search();
				search.setSearch_target(request.getParameter("search_target"));
				search.setSearch_keyword(request.getParameter("search_keyword"));
				search.setSearch_category(request.getParameter("category"));
				String temPage = request.getParameter("page");
				if(temPage != null) {
					page = Integer.parseInt(temPage);
				}
				count = boardService.getBoardCount();
				Pagination pagi = new Pagination();
				pagi.setCount(count);
				pagi.setPage(page);
				pagi.setSearch(search);
				pagi.init();
				
				boardList = boardService.getBoardList(pagi);
				
				request.setAttribute("list", boardList);
				request.setAttribute("pagination", pagi);

				view = "board/list";
				break;
			case "/board-registration.do":
				view = "board/registration";
				break;
			case "/board-process.do":
				board = new Board();
				session = request.getSession();
				board.setTitle(request.getParameter("board-title"));
				board.setContent(request.getParameter("board-content"));
				board.setUser((User)session.getAttribute("user"));
				board.setB_category(request.getParameter("category"));
				boardService = BoardService.getInstance();
				boardService.writingRegiStraion(board);
				view = "board/registraion_result";
				
				break;
			case "/board-reply.do":
				board = new Board();
				board.setB_order(Integer.parseInt(request.getParameter("b_order")));
				board.setB_depth(Integer.parseInt(request.getParameter("b_depth")));				
				board.setB_group(Integer.parseInt(request.getParameter("b_group")));		
				
				view = "board/reply";
				
				request.setAttribute("board", board);
				break;
			case "/board-reply-process.do":
				board = new Board();
				session = request.getSession();
				board.setB_order(Integer.parseInt(request.getParameter("b_order"))+1);
				board.setB_depth(Integer.parseInt(request.getParameter("b_depth"))+1);
				board.setB_group(Integer.parseInt(request.getParameter("b_group")));
				board.setTitle(request.getParameter("reply-title"));
				board.setContent(request.getParameter("reply-content"));
				board.setUser((User)session.getAttribute("user"));				
				
				boardService = BoardService.getInstance();
				boardService.replyBoard(board);
				
				view = "board/replyResult";
				break;
			case "/board-view-content.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board.setB_order(Integer.parseInt(request.getParameter("b_order")));
				board.setB_depth(Integer.parseInt(request.getParameter("b_depth")));
				board.setB_group(Integer.parseInt(request.getParameter("b_group")));
				boardService = BoardService.getInstance();
				boardService.hitsBoard(board);
				board = boardService.viewContents(board);
				
				commentService = CommentService.getInstance();	//comment--------------------------
				comment = new Comment();
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				List<Comment> commentList = commentService.getCommentList(comment);
				
				view = "board/contents";
				
				request.setAttribute("list", commentList);
				request.setAttribute("board", board);
				request.setAttribute("hits", hits);
				break;
			case "/board-edit.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				board = boardService.viewContents(board);
				
				view = "board/boardEdit";
				request.setAttribute("board", board);
				break;
			case "/board-edit-result.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board.setTitle(request.getParameter("edit-title"));
				board.setContent(request.getParameter("edit-content"));
				boardService = BoardService.getInstance();
				boardService.editContent(board);
				view = "board/editResult";
				break;
			case "/board-delete.do":
				board = new Board();
				boardService = BoardService.getInstance();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService.deleteBoard(board);
				view = "board/deleteResult";	
				break;
//		댓글 comment	-------------------------------------------------------------
				
				
			case"/comment-regi.do":
				comment = new Comment();
				session = request.getSession();
				commentService = CommentService.getInstance();
				
				comment.setId((User)session.getAttribute("user"));
				comment.setContent(request.getParameter("content"));
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				commentService.commentRegistration(comment);
				
				request.setAttribute("comment", comment);
				view = "board/replyResult";
				break;
			case"/comment-delete.do":
				comment = new Comment();
				comment.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				commentService = CommentService.getInstance();
				commentService.deleteComment(comment);
				
				List<Comment> comment_deleteList = commentService.getCommentList(comment);
				request.setAttribute("list", comment_deleteList);
				request.setAttribute("comment", comment);
				view ="comment/comment_List";
				
				break;
				
			case"/comment-edit-update.do":
				comment = new Comment();
				comment.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setContent(request.getParameter("comment"));
				commentService = CommentService.getInstance();
				commentService.editComment(comment);
				
				List<Comment> comment_editList = commentService.getCommentList(comment);
				request.setAttribute("list", comment_editList);
				request.setAttribute("comment", comment);
				view = "comment/comment_List";
				
				break;
				
				
				
				
				
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
		rd.forward(request, response);
	
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = { 
			"/user-list.do",
			"/user-insert.do",
			"/user-insert-process.do",
			"/user-detail.do",
			"/user-edit-process.do",
			"/user-edit.do",
			"/logout.do"	
		};
		for (String item: authList) {
			if (item.equals(command)) {
				if(session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
	
	
}
