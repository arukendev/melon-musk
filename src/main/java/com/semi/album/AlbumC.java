package com.semi.album;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.chart.ChartDAO;
import com.semi.main.Crawler;

@WebServlet("/AlbumC")
public class AlbumC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		if (ChartDAO.albumIdCheck(request)) {
			AlbumDAO.getAlbum(request);
			request.setAttribute("contentPage", "jsp/album/album_info.jsp");
		} else {
			Crawler.albumCrawler(request);
			AlbumDAO.setAlbum(request);
			request.setAttribute("contentPage", "jsp/album/album_reg.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
