package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.User;

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
	public List getCommentList() {
		List<Comment> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query ="select * from comment";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setWriter(rs.getString("comment_id"));
				comment.setDate(rs.getString("comment_date"));
				comment.setContent(rs.getString("comment_content"));
				list.add(comment);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn !=null) {conn.close();}
				if(pstmt!=null) {pstmt.close();}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
				
		
		
		return list;
	}
	
	public void commentRegistration(Comment comment) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query ="insert into comment (comment_date,comment_id, comment_content,comment_board) values(NOW(),?,?,?)";
			user = comment.getId();
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getU_id());
			pstmt.setString(2, comment.getContent());
			pstmt.setInt(3, comment.getB_idx());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn !=null) {conn.close();}
				if(pstmt!=null) {pstmt.close();}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
