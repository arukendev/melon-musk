package com.melon_musk.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;

@WebServlet("/PlaylistC")
public class PlaylistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	AuthDAO.loginCheck(request);
	PlaylistDAO.getRdao().getAllPlaylist(request);
	request.setAttribute("contentPage", "jsp/playlist/playlist.jsp");	
	request.getRequestDispatcher("index.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//나의 플레이리스트 조회
	
	
	
	}

}
