package com.semi.chart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.auth.Auth;
import com.semi.main.DBManager;

public class ChartDAO {

	public static boolean artistIdCheck(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ar_id from artist where ar_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("artistId"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return false;
	}
	
	public static boolean albumIdCheck(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select al_id from album where al_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("albumId"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return false;
	}

	public static boolean musicIdCheck(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select mu_id from music where mu_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("musicId"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return false;
	}

	public static void getLikeInfo(HttpServletRequest request) {
		ArrayList<Chart> charts = (ArrayList<Chart>) request.getAttribute("charts");
		Auth a = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from music_like where muli_mu_id = ? and muli_au_id = ?";
		
		try {
			con = DBManager.connect();
			for (Chart chart : charts) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, chart.getMusicId());
				if (a == null) {
					pstmt.setString(2, "");
				} else {
					pstmt.setString(2, a.getAu_id());
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					chart.setLike(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
}
