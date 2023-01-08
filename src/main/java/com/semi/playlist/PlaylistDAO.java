package com.semi.playlist;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.main.DBManager;
import com.semi.music.Music;


public class PlaylistDAO {

	private static final PlaylistDAO RDAO = new PlaylistDAO()	;
	
	private PlaylistDAO() {
		// TODO Auto-generated constructor stub
	}

	public static PlaylistDAO getRdao() {
		return RDAO;
	}


	private  ArrayList<Playlist> playlists ;
	private  ArrayList<PlaylistMusic> playlistmusics ;
	private  ArrayList<Music> musics ;
	
	
	public  void getPlaylist(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pl_name,al_img, mu_name, ar_name, mu_lyrics "
				+ "from playlist, playlist_music, album, music, artist "
				+ "where pm_pl_id = pl_id "
				+ "and pm_mu_id = mu_id "
				+ "and mu_ar_id = ar_id "
				+ "and mu_al_id = al_id "
				+ "and pl_id = ?";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_id"));
				rs=pstmt.executeQuery();
				PlaylistMusic playlistmusic = null;
				playlistmusics = new ArrayList<PlaylistMusic>();
				while (rs.next()) {
					playlistmusic = new PlaylistMusic(
							rs.getString("pl_name")
							,rs.getString("al_img")
							,rs.getString("mu_name")
							,rs.getString("ar_name")
							,rs.getString("mu_lyrics")
							,request.getParameter("pl_id")
							);
					playlistmusics.add(playlistmusic);
				}
				request.setAttribute("playlistmusics", playlistmusics);
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
	}

	public  void getAllPlaylist(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from playlist ";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				Playlist playlist = null;
				playlists = new ArrayList<Playlist>();
				while (rs.next()) {
					playlist = new Playlist(rs.getInt("pl_id"), rs.getString("pl_name")
							,rs.getInt("pl_view"),rs.getInt("pl_like"), rs.getDate("pl_date"));
					playlists.add(playlist);
				}
			
					request.setAttribute("playlists", playlists);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	public  void delReview(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from review_test where r_no=?";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("noo"));
				
				if (pstmt.executeUpdate()==1) {
					request.setAttribute("r", "��������");
				}
				
				
				
				
				
				
				
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	
public  void updateReview(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update review_test set r_title=?,r_txt=? where r_no=?";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("title"));
				pstmt.setString(2, request.getParameter("txt"));
				pstmt.setString(3, request.getParameter("noo"));
				
				if (pstmt.executeUpdate()==1) {
					request.setAttribute("r", "��������");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	
	
	
	
	
	
	
	
	

	
	public void paging(int page,HttpServletRequest req) {
		
		req.setAttribute("curPageNo", page);
		
		int cnt =5; //�� �������� ������ ����
		int total = playlists.size(); //�� ������ ����
		int pageCount = (int) Math.ceil((double)total/cnt); //�� ������ ��
		req.setAttribute("pageCount", pageCount);
		int start = total- (cnt*(page - 1));
		int end = (page==pageCount) ? -1 : start-(cnt+1);
		ArrayList<Playlist> items = new ArrayList<Playlist>();
		
		for (int i = start-1; i > end; i--) {
			items.add(playlists.get(i));
		}
		
		req.setAttribute("reviews", items);
		
	}

	public void regReview(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into review_test values(review_test_seq.nextval,?,?,sysdate)";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("title"));
				pstmt.setString(2, request.getParameter("txt"));
				
				if (pstmt.executeUpdate()==1) {
					request.setAttribute("r", "��ϼ���");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void getOnePl() {
		// TODO Auto-generated method stub
		
	}

	public void getAllPlMusic(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select mu_id,ar_id,al_id,al_img,al_name,mu_name, ar_name, mu_lyrics from album, music, artist where mu_ar_id = mu_id and mu_ar_id = ar_id and mu_al_id = al_id";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			Music music = null;
			musics = new ArrayList<Music>();
			while (rs.next()) {
				musics.add(music);
			}
			request.setAttribute("musics", musics);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
