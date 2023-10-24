package com.melon_musk.search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;
import com.melon_musk.main.Crawler;

@WebServlet("/SearchC")
public class SearchC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		if (request.getParameter("result").equals("")) {
			request.setAttribute("contentPage", "jsp/search/no_search.jsp");
		} else {
			Crawler.searchCrawler(request);
			Crawler.searchPage(request);
			SearchDAO.setPage(Integer.parseInt(request.getParameter("page")), request);
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
