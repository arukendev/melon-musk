package com.semi.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	public static Connection connect() throws SQLException {
	
		String url = "jdbc:oracle:thin:@URJ817BYXD912F6F_high?TNS_ADMIN=C:/ljh/Wallet_URJ817BYXD912F6F";
		System.out.println("success");
		return DriverManager.getConnection(url, "SEMI", "Soldeskljh802");
		
		//재현 c##ljh1004 ljh1004
		//우영 c##cwy1231 cwy1231
		//재호 c##ojh1004 ojh1004
		//경록 c##pkr pkr
    //승명 c##sm1004 sm1004

	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if(rs!=null) {
				rs.close();
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
