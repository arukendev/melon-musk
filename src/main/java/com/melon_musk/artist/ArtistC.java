package com.melon_musk.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.album.AlbumDAO;
import com.melon_musk.auth.AuthDAO;
import com.melon_musk.chart.ChartDAO;
import com.melon_musk.main.Crawler;

@WebServlet("/ArtistC")
public class ArtistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AuthDAO.loginCheck(request);
		
		Crawler.artistMusicCrwaler(request);
		Crawler.artistAlbumCrwaler(request);
		
		if (ChartDAO.artistIdCheck(request)) {
			ArtistDAO.getArtist(request);
		} else {
			Crawler.artistCrawler(request);
			ArtistDAO.setArtist(request);
		}
		
		ArtistDAO.getLikeInfo(request);
		ArtistDAO.getLikeCount(request);
		ArtistDAO.getComment(request);
		request.setAttribute("contentPage", "jsp/artist/artist_info.jsp");
		request.setAttribute("detailContentPage", "artist_main.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
