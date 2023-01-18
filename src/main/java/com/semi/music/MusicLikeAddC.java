package com.semi.music;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.chart.ChartDAO;
import com.semi.main.Crawler;

@WebServlet("/MusicLikeAddC")
public class MusicLikeAddC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		MusicDAO.setLike(request);
		request.setAttribute("parameter", request.getParameter("musicId"));
		request.setAttribute("contentPage", "jsp/main/loading.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
