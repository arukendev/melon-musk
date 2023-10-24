package com.melon_musk.album;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;
import com.melon_musk.chart.ChartDAO;
import com.melon_musk.main.Crawler;
import com.melon_musk.music.MusicDAO;

@WebServlet("/AlbumC")
public class AlbumC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		Crawler.albumMusicCrawler(request);
		if (ChartDAO.albumIdCheck(request)) {
			AlbumDAO.getAlbum(request);
		} else {
			Crawler.albumCrawler(request);
			AlbumDAO.setAlbum(request);
		}
		AlbumDAO.getLikeCount(request);
		AlbumDAO.getLikeInfo(request);
		AlbumDAO.getComment(request);
		request.setAttribute("contentPage", "jsp/album/album_info.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
