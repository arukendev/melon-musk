package com.semi.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.album.AlbumDAO;
import com.semi.auth.AuthDAO;
import com.semi.chart.ChartDAO;
import com.semi.main.Crawler;

@WebServlet("/ArtistC")
public class ArtistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		if (ArtistDAO.loginCheck(request)) {
			request.setAttribute("commentLoginCheck", "comment_input.jsp");
		} else {
			request.setAttribute("commentLoginCheck", "comment_no_input.jsp");
		}
		if (ChartDAO.artistIdCheck(request)) {
			ArtistDAO.getArtist(request);
			request.setAttribute("contentPage", "jsp/artist/artist_info.jsp");
		} else {
			Crawler.artistCrawler(request);
			ArtistDAO.setArtist(request);
			request.setAttribute("contentPage", "jsp/artist/artist_reg.jsp");
		}
		ArtistDAO.getComment(request);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
