package com.melon_musk.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon_musk.auth.Auth;
import com.melon_musk.auth.AuthDAO;

@WebServlet("/ReviewReportDetailC")
public class ReviewReportDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		ReviewDAO.getReview3(request);
		if(a!=null) {
		ReviewDAO.getLike(request);
		}
		ReviewDAO.getComment(request);
		request.setAttribute("contentPage", "jsp/review/review_detail.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
