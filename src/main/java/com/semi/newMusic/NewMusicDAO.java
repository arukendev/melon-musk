package com.semi.newMusic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.auth.Auth;
import com.semi.main.DBManager;

public class NewMusicDAO {

	public static void getLikeInfo(HttpServletRequest request) {
		ArrayList<NewMusic> newMusics = (ArrayList<NewMusic>) request.getAttribute("newMusic");
		Auth a = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from music_like where muli_mu_id = ? and muli_au_id = ?";
		
		try {
			con = DBManager.connect();
			for (NewMusic newMusic : newMusics) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newMusic.getMusicId());
				if (a == null) {
					pstmt.setString(2, "");
				} else {
					pstmt.setString(2, a.getAu_id());
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					newMusic.setLike(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

}
