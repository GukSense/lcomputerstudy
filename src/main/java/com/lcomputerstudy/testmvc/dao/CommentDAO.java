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
	public List<Comment> getCommentList(Comment comment) {
		List<Comment> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query ="select * from comment where comment_board=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getB_idx());
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Comment c = new Comment();
				c.setComment_num(rs.getInt("comment_num"));
				c.setWriter(rs.getString("comment_id"));
				c.setDate(rs.getString("comment_date"));
				c.setContent(rs.getString("comment_content"));
				c.setB_idx(rs.getInt("comment_board"));
				list.add(c);
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

	public void deleteComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String query = "DELETE FROM comment WHERE comment_num=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getComment_num());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE comment SET comment_content=?, comment_date = NOW() WHERE comment_num=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getComment_num());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt !=null ) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	} 

	
}
