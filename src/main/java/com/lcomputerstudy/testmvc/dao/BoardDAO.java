package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;

public class BoardDAO {
	private static BoardDAO dao = null;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public ArrayList<Board> getBoardList() {
		ArrayList<Board> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = new StringBuilder()
					.append("SELECT 	b_idx,\n")
					.append("		 	b_title,\n")
					.append("			b_content,\n")
					.append("			(\n")
					.append("			CASE 	WHEN 	(DATE_FORMAT(NOW(),'%Y-%m-%d') = DATE_FORMAT(b_date,'%Y-%m-%d'))\n")
					.append("			THEN	(DATE_FORMAT(b_date,'%H:%i'))\n")
					.append("			ELSE 	(DATE_FORMAT(b_date,'%Y-%m-%d'))\n")
					.append("			END\n")
					.append("			) AS b_date\n")
					.append("			FROM board\n")
					.toString(); 
			
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setTitle(rs.getString("b_title"));
				board.setContent(rs.getString("b_content"));
				board.setDate(rs.getString("b_date"));
				list.add(board);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(conn != null) {conn.close();}
				if(pstmt != null) {pstmt.close();}
				if(rs != null) {rs.close();}
			} catch(SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return list; 
	}
	
	
	
	public void writingRegiStraion(Board board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query = "insert into board(b_title,b_content,b_date) values(?,?,NOW()) ";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public Board viewContents(Board board) {
		Board b = null;
		String idx = Integer.toString(board.getB_idx());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM board WHERE b_idx=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				b = new Board();
				b.setB_idx(Integer.parseInt(rs.getString("b_idx")));
				b.setTitle(rs.getString("b_title"));
				b.setContent(rs.getString("b_content"));
				b.setDate(rs.getString("b_date"));
				b.setHits(rs.getInt("b_hits"));
//				b.setReviews(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();  
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return b;
	}
	public void hitsBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int idx = board.getB_idx();
		
		
		
		try {
			String query = "UPDATE board SET b_hits = b_hits +1 WHERE b_idx=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {conn.close();}
				if(pstmt != null) {pstmt.close();}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
