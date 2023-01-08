package com.semi.music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.artist.Artists;
import com.semi.main.DBManager;

public class MusicDAO {

	public static void setMusic(HttpServletRequest request) {
		
		Music m = (Music) request.getAttribute("music");
		ArrayList<Artists> ars = (ArrayList<Artists>) request.getAttribute("artists");
		ArrayList<String> artistIds = new ArrayList<String>();
		ArrayList<String> artistNames = new ArrayList<String>();
		
		String artistId = "";
		String artistName = "";
		
		for (Artists ar : ars) {
			artistIds.add(ar.getId());
			artistNames.add(ar.getName());
		}
		artistId = String.join(",", artistIds);
		artistName = String.join(",", artistNames);
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into music values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, artistId);
			pstmt.setString(3, artistName);
			pstmt.setString(4, m.getAlId());
			pstmt.setString(5, m.getAlName());
			pstmt.setString(6, m.getAlImg());
			pstmt.setString(7, m.getDate());
			pstmt.setString(8, m.getName());
			pstmt.setString(9, m.getGenre());
			pstmt.setString(10, m.getLyrics());
			pstmt.setString(11, m.getLink());
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
			
		} catch (Exception e) {
			System.out.println("등록 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getMusic(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from music where mu_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("musicId"));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Music m = new Music();
				Artists as = null;
				ArrayList<Artists> artists = new ArrayList<Artists>();
				m.setId(rs.getString("mu_id"));
				m.setAlId(rs.getString("mu_al_id"));
				m.setAlName(rs.getString("mu_al_name"));
				m.setAlImg(rs.getString("mu_al_img"));
				m.setDate(rs.getString("mu_date"));
				m.setName(rs.getString("mu_name"));
				m.setGenre(rs.getString("mu_genre"));
				m.setLyrics(rs.getString("mu_lyrics"));
				m.setLink(rs.getString("mu_link"));
				String[] artistIds = rs.getString("mu_ar_id").split(",");
				String[] artistNames = rs.getString("mu_ar_name").split(",");
				for (int i = 0; i < artistIds.length; i++) {
					as = new Artists();
					as.setId(artistIds[i]);
					as.setName(artistNames[i]);
					artists.add(as);
				}
				request.setAttribute("music", m);
				request.setAttribute("artists", artists);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

}
