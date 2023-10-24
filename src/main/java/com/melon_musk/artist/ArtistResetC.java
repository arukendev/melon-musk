package com.melon_musk.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;

@WebServlet("/ArtistResetC")
public class ArtistResetC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AuthDAO.loginCheck(request)) {
			ArtistDAO.delArtist(request);
			request.setAttribute("parameter", request.getParameter("artistId"));
			request.setAttribute("contentPage", "jsp/main/loading.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
