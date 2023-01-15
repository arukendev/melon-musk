package com.semi.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.main.Crawler;

@WebServlet("/ArtistMusicC")
public class ArtistMusicC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		Crawler.artistMusicCrwaler(request);
		Crawler.pageCrawler(request);
		ArtistDAO.getArtist(request);
		request.setAttribute("contentPage", "jsp/artist/artist_info.jsp");
		request.setAttribute("detailContentPage", "artist_music.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
