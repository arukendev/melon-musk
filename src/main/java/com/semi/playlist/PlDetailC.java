package com.semi.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.Auth;
import com.semi.auth.AuthDAO;

/**
 * Servlet implementation class PlDetailC
 */
@WebServlet("/PlDetailC")
public class PlDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		AuthDAO.loginCheck(request);
		//플리 뮤직이 없는 경우 
		PlaylistDAO.getRdao().regPlMusic(request);
		
		PlaylistDAO.getRdao().getPlaylist(request);
		
		request.setAttribute("contentPage", "jsp/playlist/playlistDetail.jsp");	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
