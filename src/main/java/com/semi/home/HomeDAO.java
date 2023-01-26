package com.semi.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.auth.Auth;
import com.semi.main.DBManager;

public class HomeDAO {

	public static void getPlayList(HttpServletRequest request) {

		Auth a = (Auth) request.getSession().getAttribute("account");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pl_id, pl_name, pl_img "
				+ "from playlist, auth " 
				+ "where pl_au_id = au_id "
				+ "and au_id = ?";

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			rs = pstmt.executeQuery();
			MyPlayList mpl = null;
			ArrayList<MyPlayList> mpls = new ArrayList<MyPlayList>();
			while (rs.next()) {
				mpl = new MyPlayList();
				mpl.setId(rs.getString("pl_id"));
				mpl.setName(rs.getString("pl_name"));
				mpl.setImg(rs.getString("pl_img"));
				mpls.add(mpl);
			}
			request.setAttribute("mpls", mpls);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getArtistLike(HttpServletRequest request) {
		Auth a = (Auth) request.getSession().getAttribute("account");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ar_id, ar_name, ar_img " 
				+ "from artist, artist_like, auth " 
				+ "where arli_au_id = au_id "
				+ "and ar_id = arli_ar_id "
				+ "and au_id = ?";

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			rs = pstmt.executeQuery();
			MyArtistLike marl = null;
			ArrayList<MyArtistLike> marls = new ArrayList<MyArtistLike>();
			while (rs.next()) {
				marl = new MyArtistLike();
				marl.setId(rs.getString("ar_id"));
				marl.setName(rs.getString("ar_name"));
				marl.setImg(rs.getString("ar_img"));
				marls.add(marl);
			}
			request.setAttribute("marls", marls);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getAlbumLike(HttpServletRequest request) {
		Auth a = (Auth) request.getSession().getAttribute("account");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select al_id, al_name, al_img " 
				+ "from album, album_like, auth " 
				+ "where alli_au_id = au_id "
				+ "and al_id = alli_al_id "
				+ "and au_id = ?";

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			rs = pstmt.executeQuery();
			MyAlbumLike mall = null;
			ArrayList<MyAlbumLike> malls = new ArrayList<MyAlbumLike>();
			while (rs.next()) {
				mall = new MyAlbumLike();
				mall.setId(rs.getString("al_id"));
				mall.setName(rs.getString("al_name"));
				mall.setImg(rs.getString("al_img"));
				malls.add(mall);
			}
			request.setAttribute("malls", malls);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getMusicLike(HttpServletRequest request) {
		Auth a = (Auth) request.getSession().getAttribute("account");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select mu_id, mu_name " 
				+ "from music, music_like, auth " 
				+ "where muli_au_id = au_id "
				+ "and mu_id = muli_mu_id "
				+ "and au_id = ?";

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			rs = pstmt.executeQuery();
			MyMusicLike mml = null;
			ArrayList<MyMusicLike> mmls = new ArrayList<MyMusicLike>();
			while (rs.next()) {
				mml = new MyMusicLike();
				mml.setId(rs.getString("mu_id"));
				mml.setName(rs.getString("mu_name"));
				mmls.add(mml);
			}
			request.setAttribute("mmls", mmls);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

}
