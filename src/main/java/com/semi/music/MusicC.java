package com.semi.music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.chart.ChartDAO;
import com.semi.main.Crawler;


@WebServlet("/MusicC")
public class MusicC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		if (MusicDAO.loginCheck(request)) {
			request.setAttribute("commentLoginCheck", "jsp/comment/comment_input.jsp");
		} else {
			request.setAttribute("commentLoginCheck", "jsp/comment/comment_no_input.jsp");
		}
		if (ChartDAO.musicIdCheck(request)) {
			MusicDAO.getMusic(request);
			request.setAttribute("contentPage", "jsp/music/music_info.jsp");
		} else {
			Crawler.musicCrawler(request);
			MusicDAO.setMusic(request);
			request.setAttribute("contentPage", "jsp/music/music_reg.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
