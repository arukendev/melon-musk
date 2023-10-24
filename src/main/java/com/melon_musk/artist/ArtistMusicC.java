package com.melon_musk.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;
import com.melon_musk.main.Crawler;
import com.melon_musk.search.SearchDAO;

@WebServlet("/ArtistMusicC")
public class ArtistMusicC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		Crawler.artistMusicCrwaler(request);
		Crawler.pageCrawler(request);
		ArtistDAO.getArtist(request);
		ArtistDAO.getLikeInfo(request);
		ArtistDAO.getLikeCount(request);
		SearchDAO.setPage(Integer.parseInt(request.getParameter("page")), request);
		request.setAttribute("contentPage", "jsp/artist/artist_info.jsp");
		request.setAttribute("detailContentPage", "artist_music.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
