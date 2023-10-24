package com.melon_musk.music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;
import com.melon_musk.chart.ChartDAO;
import com.melon_musk.main.Crawler;


@WebServlet("/MusicC")
public class MusicC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		if (ChartDAO.musicIdCheck(request)) {
			MusicDAO.getMusic(request);
		} else {
			Crawler.musicCrawler(request);
			MusicDAO.setMusic(request);
		}
		MusicDAO.getLikeCount(request);
		MusicDAO.getLikeInfo(request);
		MusicDAO.getComment(request);
		request.setAttribute("contentPage", "jsp/music/music_info.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
