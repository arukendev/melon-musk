package com.semi.chart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.main.Crawler;

@WebServlet("/ChartC")
public class ChartC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Crawler.chartCrawler(request);
		if (AuthDAO.loginCheck(request)) {
			ChartDAO.getLikeInfo(request);
		}
		request.setAttribute("contentPage", "jsp/chart/chart.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
