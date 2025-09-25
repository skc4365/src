package com.skc.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.skc.conn.DBConnection;

public class CRUDClass {

	public CRUDClass() {

//		createTable();

//		insertUsers("1", "홍길동");
//		insertUsers("2", "일지매");
//		insertUsers("3", "세종대왕");
//		insertUsers("4", "장영실");

//		deleteUsers("4");

//		updateUsers("2", "일지매_수정후");

		selectUsers();

	}

	private void updateUsers(String id, String name) {
		System.out.println("--------- 레코드 내용 수정 START -----------");
		String sql = "update users set name = ? where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			int rows = pstmt.executeUpdate();
			System.out.println("----- " + rows + "행이 수정됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
		System.out.println("--------- 레코드 내용 수정 END -----------");
	}

	private void deleteUsers(String id) {
		System.out.println("--------- 레코드 삭제 START -----------");
		String sql = "delete from users where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int rows = pstmt.executeUpdate();
			System.out.println("----- " + rows + "행이 삭제됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
		System.out.println("--------- 레코드 삭제 END -----------");
	}

	private void insertUsers(String id, String name) {
		System.out.println("--------- 레코드 추가 START -----------");
		String sql = "insert into users(id, name) values (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			int rows = pstmt.executeUpdate();
			System.out.println("-----" + rows + "행이 추가되었음");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt, conn);
		}
		System.out.println("--------- 레코드 추가 END -----------");
	}

	private void selectUsers() {
		System.out.println("--------- 레코드 내용 확인 START -----------");
		String sql = "select * from users";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				System.out.println("users 테이블에 조회된 결과 없음");
			} else {
				int rowCount = 0;
				do {
					rowCount++;
					String id = rs.getString("id");
					String name = rs.getString("name");
					System.out.println("ID: " + id + "   NAME: " + name);
				} while (rs.next());
				System.out.println("~~ 테이블에 " + rowCount + "행이 있음 ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		System.out.println("--------- 레코드 내용 확인 END -----------");
	}

	private void createTable() {
		System.out.println("--------- 새로운 테이블 생성 START -----------");
		String sql = "create table if not exists users(\r\n" + "	id varchar(50),\r\n" + "	name varchar(100)\r\n"
				+ ")";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("users 테이블이 존재합니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(stmt, conn);
		}
		System.out.println("--------- 새로운 테이블 생성 END -----------");
	}
}
