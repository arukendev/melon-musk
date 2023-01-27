package com.semi.playlist;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.auth.Auth;
import com.semi.main.DBManager;
import com.semi.music.Music;
import com.semi.review.Review;


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
		String sql = "select pl_name,mu_al_img, mu_name, mu_ar_name, mu_lyrics,mu_id,pl_au_id"
				+ ",pl_text,pl_img,pl_date,mu_al_name "
				+ "from playlist, playlist_music, music "
				+ "where pm_pl_id = pl_id and pm_mu_id = mu_id and pl_id = ?";
		
		
		
		
		try {
				System.out.println(request.getAttribute("pl_id"));
				String pl_id="";
				if (request.getAttribute("pl_id")!=null) {
					pl_id=(String) request.getAttribute("pl_id");
				}else {
					pl_id=request.getParameter("pl_id");
				}
				
				
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pl_id);
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
							,rs.getString("pl_au_id")
							,rs.getString("pl_text")
							,rs.getString("pl_img")
							,rs.getDate("pl_date")
							,rs.getString("mu_al_name")
							);
					playlistmusics.add(playlistmusic);
				}
				request.setAttribute("playlistmusics", playlistmusics);
				request.setAttribute("mu_ok",playlistmusics.get(0).getMu_name() );
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
							,rs.getInt("pl_view"),rs.getInt("pl_like"), rs.getDate("pl_date")
							, rs.getString("pl_au_id"), rs.getString("pl_text"), rs.getString("pl_img")
							);
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
		
		int cnt =7; 
		int total = musics.size(); 
		int pageCount = (int) Math.ceil((double)total/cnt); 
		
		
		int start = cnt * (page - 1) + 1;
		int end = (page == pageCount) ? total : start + cnt - 1;
		
		ArrayList<PlaylistDBMusic> items = new ArrayList<PlaylistDBMusic>();
		
		for (int i = start-1 ; i < end; i++) {
			items.add(musics.get(i));
		}
		
		
		req.setAttribute("musics", items);
		req.setAttribute("pageCount", pageCount);
		
	}
	
public void pl_paging(int page,HttpServletRequest req) {
		
		req.setAttribute("curPageNo", page);
		
		int cnt =7; 
		int total = playlists.size(); 
		int pageCount = (int) Math.ceil((double)total/cnt); 
		
		
		int start = cnt * (page - 1) + 1;
		int end = (page == pageCount) ? total : start + cnt - 1;
		
		ArrayList<Playlist> items = new ArrayList<Playlist>();
		
		for (int i = start-1 ; i < end; i++) {
			items.add(playlists.get(i));
		}
		
		
		req.setAttribute("playlists", items);
		req.setAttribute("pageCount", pageCount);
		
	}

	


	public void getAllPlMusic(HttpServletRequest request) {
		
		
		
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
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		//String sql = "INSERT ALL INTO playlist VALUES(playlist_seq.nextval,?,0,0,sysdate) INTO playlist_music values(playlist_music_seq.nextval,playlist_seq.nextval,34819473) SELECT * FROM DUAL";
		//String sql = "insert into playlist values(playlist_seq.nextval,?,0,0,sysdate)";
		try {
			String path = request.getSession().getServletContext().getRealPath("files/playlist");
			System.out.println(path);
			
			MultipartRequest mr = new MultipartRequest(request,path,20*1024*1024,"utf-8",new DefaultFileRenamePolicy());
			
			
			String[] test =mr.getParameterValues("mu_id");
			//String insertMu ="";
			
			//for (String s : test) {
				//insertMu += "INTO playlist_music values(getplmusicid, playlist_seq.nextval,"+ s+") ";
			//}
			//sql = "INSERT ALL INTO playlist VALUES(playlist_seq.nextval,?,0,0,sysdate,?,?,?) "+ insertMu +"SELECT * FROM DUAL";
		
				sql = "INSERT INTO playlist VALUES(playlist_seq.nextval,?,0,0,sysdate,?,?,?) ";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mr.getParameter("pl_name"));
				pstmt.setString(2, a.getAu_id());
				pstmt.setString(3, mr.getParameter("pl_text"));
				if (mr.getFilesystemName("pl_img").equals(null)) {
					pstmt.setString(4, "none");
				}else {
					pstmt.setString(4, mr.getFilesystemName("pl_img"));
				}
				
				
				if (pstmt.executeUpdate()==1) {
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
			//if 차트에서 음악을 플리에 추가하는 경우
			if (request.getParameter("musicId")!=null) {
				sql= "INSERT INTO PLAYLIST_MUSIC VALUES(getplmusicid,?,?)";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				
				String muAndPlid = request.getParameter("musicId");
				String[] muId_plid = muAndPlid.split("\\+");
				String muId = muId_plid[0];
				String plId = muId_plid[1];
				
				System.out.println(muId);
				System.out.println(plId);
				request.setAttribute("pl_id", plId);
				
				pstmt.setString(1, plId);
				pstmt.setString(2, muId);
			}
			//else 플리에서 음원추가버튼을 통해 노래를 추가하는 경우
			else {
			String[] test=request.getParameterValues("mu_id");
			String insertMu ="";
			
			for (String s : test) {
				insertMu += "INTO playlist_music values(getplmusicid,"+ request.getParameter("pl_id") +","+ s+") ";
			}
			sql = "INSERT ALL "+ insertMu +"SELECT * FROM DUAL";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			}
				
				
				if (pstmt.executeUpdate()==1) {
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
			
		
			
			for (int i = 0; i < test.length; i++) {
				if (test.length==1 || test.length-i==1 ) {
					removePlMusic += test[i];
				}else {
					removePlMusic += test[i]+",";
				}
			}
			
			
			sql = "delete from playlist_music where pm_pl_id=? and pm_mu_id in("+ removePlMusic +")";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_id"));
				System.out.println(sql);
				System.out.println(request.getParameter("pl_id"));
				
				if (pstmt.executeUpdate()==1) {
					System.out.println("제거성공");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
		
		
		
		
	}

	public void increaseView(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			String sql = "update PLAYLIST set PL_VIEW=NVL(PL_VIEW, 0)+ 1  where PL_ID=?";
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("pl_id"));
				
				if (pstmt.executeUpdate()==1) {
					System.out.println("업데이트성공");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
	}

	public void getMyPlaylist(HttpServletRequest request) {
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		
		String sql = "select * from playlist where pl_au_id=?";
		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a.getAu_id());
				rs=pstmt.executeQuery();
				Playlist playlist = null;
				playlists = new ArrayList<Playlist>();
				while (rs.next()) {
					playlist = new Playlist(rs.getInt("pl_id"), rs.getString("pl_name")
							,rs.getInt("pl_view"),rs.getInt("pl_like"), rs.getDate("pl_date")
							, rs.getString("pl_au_id"), rs.getString("pl_text"), rs.getString("pl_img")
							);
					playlists.add(playlist);
				}
			
					request.setAttribute("playlists", playlists);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public void getPlaylist_onlyPl(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from playlist where pl_id=? ";
		
		try {
				System.out.println(request.getAttribute("pl_id"));
				String pl_id="";
				if (request.getAttribute("pl_id")!=null) {
					pl_id=(String) request.getAttribute("pl_id");
				}else {
					pl_id=request.getParameter("pl_id");
				}
				
				
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pl_id);
				rs=pstmt.executeQuery();
//				PlaylistMusic playlistmusic = null;
//				playlistmusics = new ArrayList<PlaylistMusic>();
				
				Playlist playlist = null;
				if(rs.next()) {
					playlist = new Playlist(rs.getInt("pl_id"), rs.getString("pl_name"), rs.getInt("pl_view"), 
							rs.getInt("pl_like"), rs.getDate("pl_date")
							, rs.getString("pl_au_id"), rs.getString("pl_text"), rs.getString("pl_img"));
					
				}
				request.setAttribute("playlist", playlist);
				request.setAttribute("plAuId", playlist.getPl_au_id());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
	}
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
