package com.melon_musk.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;

/**
 * Servlet implementation class RegPlMusicC
 */
@WebServlet("/AddPlMusicC")
public class AddPlMusicC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//플리에 음악등록을 위한 등록페이지로 이동
		AuthDAO.loginCheck(request);
		
		PlaylistDAO.getRdao().getAllPlMusic(request);
		PlaylistDAO.getRdao().getPlaylist_onlyPl(request);
		request.setAttribute("contentPage", "jsp/playlist/addPlaylistMusic.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AuthDAO.loginCheck(request);
		//플리에 노래추가
		PlaylistDAO.getRdao().addPlMusic(request);
		
		PlaylistDAO.getRdao().getPlaylist(request);
		
		request.setAttribute("contentPage", "jsp/playlist/playlistDetail_guest.jsp");	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	
	
	}

}
