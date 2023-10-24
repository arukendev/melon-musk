package com.melon_musk.newMusic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;
import com.melon_musk.main.Crawler;

@WebServlet("/NewMusicC")
public class NewMusicC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Crawler.newMusicCrawler(request);
		if (AuthDAO.loginCheck(request)) {
			NewMusicDAO.getLikeInfo(request);
		}
		request.setAttribute("contentPage", "jsp/chart/new_music.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
