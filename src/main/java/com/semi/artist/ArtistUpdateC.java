package com.semi.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.music.MusicDAO;

@WebServlet("/ArtistUpdateC")
public class ArtistUpdateC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		ArtistDAO.getArtist(request);
		request.setAttribute("contentPage", "jsp/artist/artist_update.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		ArtistDAO.updateArtist(request);
		request.setAttribute("parameter", request.getParameter("artistId"));
		request.setAttribute("contentPage", "jsp/main/loading.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
