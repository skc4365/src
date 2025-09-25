package com.skc.crud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.skc.conn.DBConnection;

public class CRUDClass {

	public CRUDClass() {

		createTable();
	}

	private void createTable() {
		System.out.println("--------- 새로운 테이블 생성 START -----------");

//		db연결
		String sql = "create table if not exists users(\r\n"
				+ "	id varchar(50),\r\n"
				+ "	name varchar(100)\r\n"
				+ ")";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
//			테이블생성 성공(result == false)
			boolean result = stmt.execute(sql);
			String str = (result) ? 
					"already 테이블users" : "new 테이블users";
			System.out.println(str);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(stmt, conn);
		}

		System.out.println("--------- 새로운 테이블 생성 END -----------");
	}
}
