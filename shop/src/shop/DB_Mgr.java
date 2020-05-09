package shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Mgr {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Boolean isConnected = false;

	private static DB_Mgr mDB_Mgr = null;

	private DB_Mgr() {
	}

	public static DB_Mgr getDB_Mgr() {
		if (mDB_Mgr == null) {
			mDB_Mgr = new DB_Mgr();
		}

		return mDB_Mgr;
	}

	public boolean DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");
			System.out.println("DB Connect OK");
			isConnected = true;
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("DB Connect Error");
			isConnected = false;
		}

		return false;
	}
	public boolean DBTable_Insert(String tableName, String[] stringfieldName, String[] stringfieldValue, String[] intfieldName, int[] intfieldValue) {
		StringBuilder query_insert = new StringBuilder("INSERT INTO ");
		query_insert.append(" " + tableName + " (");
		for(int i = 0 ; i < stringfieldName.length ; i ++) {
			query_insert.append(stringfieldName[i] + ", ");
		}
		
		for(int i = 0 ; i < intfieldName.length ; i ++) {
			if(i != intfieldName.length-1) {
				query_insert.append(intfieldName[i] + ", ");
			}
			else {
				query_insert.append(intfieldName[i] + " ");
			}
		}
		query_insert.append(") values ("); 
		for(int i = 0 ; i < stringfieldValue.length ; i ++) {
			query_insert.append("\"" + stringfieldValue[i] + "\", ");
		}
		
		for(int i = 0 ; i < intfieldValue.length ; i ++) {
			if(i != intfieldValue.length-1) {
				query_insert.append(intfieldValue[i] + ", ");
			}
			else {
				query_insert.append(intfieldValue[i] + " ");
			}
		}
		query_insert.append(");"); 
		
		System.out.println(query_insert.toString());
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_insert.toString());
			System.out.println("query_insert OK");
			
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("query_insert Error");
		}
		
		return false;
	}
	
	public boolean DBTable_Update(int bo_num, String bo_title, String bo_content, int mem_num, String mem_email, String bo_img, String bo_category, String bo_password) {
		String query_update = "update shop.board set "
				+ " bo_title='" + bo_title + "',"
				+ " bo_content='" + bo_content + "',"
				+ " mem_num=" + mem_num + ","
				+ " mem_email='" + mem_email + "',"
				+ " bo_img='" + bo_img + "',"
				+ " bo_category='" + bo_category + "',"
				+ " bo_password='" + bo_password + "'"
				+ " where bo_num=" + bo_num;
		
		System.out.println("query_insert : " +query_update);
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_update);
			System.out.println("query_update OK");
			
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("query_update Error");
		}
		
		return false;
	}
	
	public int  DBTable_Select_Count (String tableName) {		
		StringBuilder query_select = new StringBuilder("select COUNT(*) from ");
		query_select.append(tableName);
		int board_count = 0;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select.toString());

			while (rs.next()) {
				board_count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		}
		return board_count;
	}
	
	public int  DBTable_Select_Count (String tableName, String like) {		
		StringBuilder query_select = new StringBuilder("select COUNT(*) from ");
		query_select.append(tableName);
		query_select.append(" where bo_title LIKE '%");
		query_select.append(like);
		query_select.append("%';");
		int board_count = 0;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select.toString());

			while (rs.next()) {
				board_count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		}
		return board_count;
	}
			
	public Object DBTable_Select(String tableName, int LIMIT, int iptNUM) {		
		String query_select = "select * from "
						+ tableName
						+ " order by bo_num desc "
						+ " LIMIT " + LIMIT + ", "+ iptNUM +";";
		Board[] mBoard= null;
		int board_count = 0;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select);

			while (rs.next()) {
				board_count++;
			}

			rs = stmt.executeQuery(query_select);
			mBoard = new Board[board_count];
			board_count = 0;

			while (rs.next()) {
				mBoard[board_count] = new Board();
				mBoard[board_count].bo_num = rs.getInt("bo_num");
				mBoard[board_count].bo_title = rs.getString("bo_title");
				mBoard[board_count].bo_content = rs.getString("bo_content");
				mBoard[board_count].mem_num = rs.getInt("mem_num");
				mBoard[board_count].mem_email = rs.getString("mem_email");
				mBoard[board_count].bo_img = rs.getString("bo_img");
				mBoard[board_count].bo_category = rs.getString("bo_category");
				mBoard[board_count].bo_date = rs.getString("bo_date");
				mBoard[board_count].bo_password = rs.getString("bo_password");
				board_count++;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		}
		
		return mBoard;
	}
	
	public Object DBTable_Select(String tableName, int LIMIT, int iptNUM, String like) {		
		String query_select = "select * from "
						+ tableName
						+ " where bo_title LIKE '%"
						+ like
						+ "%' "
						+ " order by bo_num desc "
						+ " LIMIT " + LIMIT + ", "+ iptNUM +";";
		Board[] mBoard= null;
		int board_count = 0;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select);

			while (rs.next()) {
				board_count++;
			}

			rs = stmt.executeQuery(query_select);
			mBoard = new Board[board_count];
			board_count = 0;

			while (rs.next()) {
				mBoard[board_count] = new Board();
				mBoard[board_count].bo_num = rs.getInt("bo_num");
				mBoard[board_count].bo_title = rs.getString("bo_title");
				mBoard[board_count].bo_content = rs.getString("bo_content");
				mBoard[board_count].mem_num = rs.getInt("mem_num");
				mBoard[board_count].mem_email = rs.getString("mem_email");
				mBoard[board_count].bo_img = rs.getString("bo_img");
				mBoard[board_count].bo_category = rs.getString("bo_category");
				mBoard[board_count].bo_date = rs.getString("bo_date");
				mBoard[board_count].bo_password = rs.getString("bo_password");
				board_count++;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		}
		
		return mBoard;
	}
	
	public Object DBTable_Select(int bo_num) {		
		String query_select = "select * from shop.board"
				+ " where bo_num=" + bo_num +";";
		Board mBoard= null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select);

			while (rs.next()) {
				mBoard = new Board();
				mBoard.bo_num = rs.getInt("bo_num");
				mBoard.bo_title = rs.getString("bo_title");
				mBoard.bo_content = rs.getString("bo_content");
				mBoard.mem_num = rs.getInt("mem_num");
				mBoard.mem_email = rs.getString("mem_email");
				mBoard.bo_img = rs.getString("bo_img");
				mBoard.bo_category = rs.getString("bo_category");
				mBoard.bo_date = rs.getString("bo_date");
				mBoard.bo_password = rs.getString("bo_password");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		}
		
		return mBoard;
	}
	
	public boolean DBTable_Delete(int bo_num) {		
		String query_delete = "delete from shop.board"
				+ " where bo_num=" + bo_num +";";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_delete);
			System.out.println("query_insert OK");
			
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("query_insert Error");
		}
		return false;
	}
}
