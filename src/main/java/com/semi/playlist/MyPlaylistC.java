package com.semi.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.auth.Auth;
import com.semi.auth.AuthDAO;

@WebServlet("/MyPlaylistC")
public class MyPlaylistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
		// 내 플레이리스트 조회 

				AuthDAO.loginCheck(request);
				
				HttpSession hs = request.getSession();
				Auth a =(Auth)hs.getAttribute("account");
				
				
				
				if(a==null) {
					request.setAttribute("contentPage", "jsp/auth/login.jsp");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
				request.setAttribute("a", a);
				PlaylistDAO.getRdao().getMyPlaylist(request);
				request.setAttribute("contentPage", "jsp/playlist/playlist.jsp");	
				request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			
	
	
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
