
package com.melon_musk.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;

@WebServlet("/HomeC")
public class HomeC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AuthDAO.loginCheck(request)) {
			HomeDAO.getPlayList(request);
			HomeDAO.getArtistLike(request);
			HomeDAO.getAlbumLike(request);
			HomeDAO.getMusicLike(request);
			request.setAttribute("contentPage", "jsp/main/main_login.jsp");
		} else {
			request.setAttribute("contentPage", "jsp/main/main.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
