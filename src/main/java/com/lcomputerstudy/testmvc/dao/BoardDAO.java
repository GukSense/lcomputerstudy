package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.User;

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
	
	public ArrayList<Board> getBoardList() {	//게시물리스트
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
					.append("			) AS b_date,\n")
					.append("			b_hits,\n")
					.append("			b_writer,\n")
					.append("			b_group,\n")
					.append("			b_order\n")
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
				board.setHits(rs.getInt("b_hits"));
				board.setWriter(rs.getString("b_writer"));
				board.setB_group(rs.getInt("b_group"));
				board.setB_order(rs.getInt("b_order"));
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
	
	
	
	public void writingRegiStraion(Board board) {	//글등록
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		User user = null;
		try {
			String query = "insert into board(b_title,b_content,b_date,b_writer,b_order,b_depth) values(?,?,NOW(),?,1,0) ";
			user = board.getUser();
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, user.getU_id());
			pstmt.executeUpdate();
			
			pstmt.close();
			String query2 = "UPDATE 	board\n"
					+ "SET  		b_group= LAST_INSERT_ID()\n"
					+ "WHERE		b_idx = LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(query2);
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
	public void replyBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		User user = null;
		try {
			user = board.getUser();
			String query = new StringBuilder()
					.append("INSERT INTO board(b_title, b_content,b_date,b_writer,b_group)\n")
					.append("VALUES (?,?,NOW(),?,?) ")
					.toString();			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, user.getU_id());
			pstmt.setInt(4, board.getB_group());
			pstmt.executeUpdate();
			pstmt.close();
			
			String query2 = "update board set b_depth = b_depth+1,b_order=?+1 where b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, board.getB_order());
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
	public void replyOrderIncre(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE 	board\n" + 
							"SET 	b_order =\n" + 
							"CASE 	WHEN b_order > ? THEN b_order = ? +1\n" + 
							"ELSE 	b_order\n" + 
							"END	\n" + 
							"WHERE 	b_group = b_group=? AND b_depth >=1";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getB_order());
			pstmt.setInt(2, board.getB_order());
			pstmt.setInt(3, board.getB_group());
			
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
	public Board viewContents(Board board) {	//컨텐츠보기
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
				b.setB_group(Integer.parseInt(rs.getString("b_group")));
				b.setB_order(Integer.parseInt(rs.getString("b_order")));
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
	public void hitsBoard(Board board) {	// 조회수
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
	
	public void editContent(Board board) {	// 수정
		Connection conn = null;
		PreparedStatement pstmt = null;
		String idx = Integer.toString(board.getB_idx());
		try {
			String query = "UPDATE board set b_title=?, b_content=? WHERE b_idx=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, idx);
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
	public void deleteBoard(Board board) {	//삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		String idx = Integer.toString(board.getB_idx());
		try {
			String query = "DELETE FROM board WHERE b_idx=?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idx);
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
