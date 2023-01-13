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


	private  ArrayList<Playlist> playlists;
	private  ArrayList<PlaylistMusic> playlistmusics ;
	private  ArrayList<PlaylistDBMusic> musics ;
	
	
	public  void getPlaylist(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pl_name,mu_al_img, mu_name, mu_ar_name, mu_lyrics,mu_id "
				+ "from playlist, playlist_music, music "
				+ "where pm_pl_id = pl_id and pm_mu_id = mu_id and pl_id = ?";
		
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
							,rs.getString("mu_al_img")
							,rs.getString("mu_name")
							,rs.getString("mu_ar_name")
							,rs.getString("mu_lyrics")
							,request.getParameter("pl_id")
							,rs.getString("mu_id")
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
		String sql = "select * from playlist";
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


	public void getAllPlMusic(HttpServletRequest request) {
		
		
		System.out.println(request.getParameter("pl_id"));
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from music";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			PlaylistDBMusic music = null;
			musics = new ArrayList<PlaylistDBMusic>();
			while (rs.next()) {
				music = new PlaylistDBMusic(rs.getString("mu_id"),rs.getString("mu_ar_id"),rs.getString("mu_ar_name")
						, rs.getString("mu_al_id"), rs.getString("mu_al_name"), rs.getString("mu_al_img")
						, rs.getString("mu_date"), rs.getString("mu_name"), rs.getString("mu_genre")
						, rs.getString("mu_lyrics"), rs.getString("mu_link"));
				musics.add(music);
			}
			
			request.setAttribute("musics", musics);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public void regPlaylist(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		//String sql = "INSERT ALL INTO playlist VALUES(playlist_seq.nextval,?,0,0,sysdate) INTO playlist_music values(playlist_music_seq.nextval,playlist_seq.nextval,34819473) SELECT * FROM DUAL";
		//String sql = "insert into playlist values(playlist_seq.nextval,?,0,0,sysdate)";
		try {
			String[] test=request.getParameterValues("mu_id");
			String insertMu ="";
			
			for (String s : test) {
				insertMu += "INTO playlist_music values(getplmusicid, playlist_seq.nextval,"+ s+") ";
			}
			sql = "INSERT ALL INTO playlist VALUES(playlist_seq.nextval,?,0,0,sysdate) "+ insertMu +"SELECT * FROM DUAL";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_name"));
				
				
				if (pstmt.executeUpdate()==1) {
					request.setAttribute("r", "등록성공!");
					System.out.println("등록성공");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void addPlMusic(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			String[] test=request.getParameterValues("mu_id");
			String insertMu ="";
			
			for (String s : test) {
				insertMu += "INTO playlist_music values(getplmusicid,"+ request.getParameter("pl_id") +","+ s+") ";
			}
			sql = "INSERT ALL "+ insertMu +"SELECT * FROM DUAL";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				
				
				if (pstmt.executeUpdate()==1) {
					request.setAttribute("r", "추가성공!");
					System.out.println("추가성공");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
		
		
		
	}

	public void deletePlaylist(HttpServletRequest request) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from playlist where pl_id=?";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_id"));
				
				if (pstmt.executeUpdate()==1) {
					System.out.println("플리삭제성공");
						request.setAttribute("r", "삭제성공");
						
				}
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
	}

	public void deletePlMusic(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from playlist_music where pm_pl_id=?";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_id"));
				
				if (pstmt.executeUpdate()==1) {
					System.out.println("음악삭제성공");
					request.setAttribute("r", "전부삭제성공");
				}
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
	}

	public void removePlMusic(HttpServletRequest request) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			String[] test=request.getParameterValues("mu_id");
			String removePlMusic ="";
			
			for (String s : test) {
				removePlMusic += " and pm_mu_id="+s;
				System.out.println(removePlMusic);
			}
			sql = "delete from playlist_music where pm_pl_id=?"+ removePlMusic ;
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_id"));
				System.out.println(sql);
				System.out.println(request.getParameter("pl_id"));
				
				if (pstmt.executeUpdate()==1) {
					request.setAttribute("r", "제거성공!");
					System.out.println("제거성공");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
		
		
		
		
	}
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
