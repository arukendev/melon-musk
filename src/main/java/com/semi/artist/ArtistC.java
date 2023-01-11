package com.semi.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.chart.ChartDAO;
import com.semi.main.Crawler;

@WebServlet("/ArtistC")
public class ArtistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		if (ChartDAO.artistIdCheck(request)) {
			ArtistDAO.getArtist(request);
			request.setAttribute("contentPage", "jsp/artist/artist_info.jsp");
		} else {
			Crawler.artistCrawler(request);
			Crawler.artistTrackCrwaler(request);
			ArtistDAO.setArtist(request);
			request.setAttribute("contentPage", "jsp/artist/artist_reg.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
