package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
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
	
	public List<Board> getBoardList(Pagination pagination) {	//게시물리스트
		List<Board> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Search search = pagination.getSearch();
		String where = "";
		String column = pagination.getSearch().getSearch_target();
		String keyword = pagination.getSearch().getSearch_keyword();
		int pageNum = pagination.getPageNum();
		//String column = search.getSearch_target();
		//String keyword = search.getSearch_keyword();
	
		if(search != null) {			
			switch(column!= null ? column:"null") {
				case"title":
					where = "WHERE b_title LIKE ?";
					break;
				case"title_content":
					where = "WHERE b_content LIKE ?";
					break;
				case"nick_name":
					where = "WHERE b_writer LIKE ?";
					break;
				case "null":
					where = "";
					break;
			}		
		} 
			
		
		
		try {
			String query = new StringBuilder()
					.append("SELECT @ROWNUM := @ROWNUM -1 AS ROWNUM,\n")
					.append("		ta.*\n")
					.append("FROM\n")
					.append("		(\n")
					.append("			SELECT	(\n")
					.append("					CASE 	WHEN 	(DATE_FORMAT(NOW(),'%Y-%m-%d') = DATE_FORMAT(b_date,'%Y-%m-%d'))\n")
					.append("					THEN	(DATE_FORMAT(b_date,'%H:%i'))\n")
					.append("					ELSE 	(DATE_FORMAT(b_date,'%Y-%m-%d'))\n")
					.append("					END\n")
					.append("					) AS b_date,\n")
					.append("		 			b_idx,\n")
					.append("		 			b_title,\n")
					.append("					b_content,\n")
					.append("					b_hits,\n")
					.append("					b_writer,\n")
					.append("					b_group,\n")
					.append("					b_order,\n")
					.append("					b_depth\n")
					.append("		FROM 		board\n")
					.append(		where + "\n")
					.append("		) ta\n")
					.append("INNER JOIN (SELECT @rownum := (SELECT COUNT(*)-?+1 FROM board ta)) tb ON 1=1\n")
					.append("ORDER BY  	b_group DESC , b_order ASC \n")
					.append("LIMIT ?,?")
					.toString(); 
			
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			if (keyword != null) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, pageNum);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, Pagination.perPage);
			} else if(keyword == null){
				pstmt.setInt(1, pageNum);
				pstmt.setInt(2, pageNum);
				pstmt.setInt(3, Pagination.perPage);				
			}
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
				board.setB_depth(rs.getInt("b_depth"));
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
	public int getBoardCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT COUNT(*)AS count FROM board";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			count = rs.getInt("count");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
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
					.append("INSERT INTO board(b_title, b_content,b_date,b_writer,b_group,b_order,b_depth)\n")
					.append("VALUES (?,?,NOW(),?,?,?,?)")
					.toString();			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, user.getU_id());
			pstmt.setInt(4, board.getB_group());
			pstmt.setInt(5, board.getB_order());
			pstmt.setInt(6, board.getB_depth());
			pstmt.executeUpdate();
			pstmt.close();
			
			String query2 = "update board set b_order=b_order+1 where b_group = ? and b_order >= ? and b_idx != last_insert_id()";
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, board.getB_group());
			pstmt.setInt(2, board.getB_order());
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
				b.setB_depth(Integer.parseInt(rs.getString("b_depth")));
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
