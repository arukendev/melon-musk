package com.semi.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;

@WebServlet("/DeletePlaylistC")
public class DeletePlaylistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AuthDAO.loginCheck(request);
		PlaylistDAO.getRdao().deletePlaylist(request);
		PlaylistDAO.getRdao().deletePlMusic(request);;
		
		PlaylistDAO.getRdao().getAllPlaylist(request);	
		request.setAttribute("contentPage", "jsp/playlist/playlist.jsp");	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
	
	
	
	
	}

}
