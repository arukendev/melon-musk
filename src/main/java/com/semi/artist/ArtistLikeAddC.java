package com.semi.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.music.MusicDAO;

@WebServlet("/ArtistLikeAddC")
public class ArtistLikeAddC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		ArtistDAO.setLike(request);
		request.setAttribute("parameter", request.getParameter("artistId"));
		request.setAttribute("contentPage", "jsp/main/loading.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
