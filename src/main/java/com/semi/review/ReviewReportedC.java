package com.semi.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.auth.Auth;
import com.semi.auth.AuthDAO;

@WebServlet("/ReviewReportedC")
public class ReviewReportedC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		
		if(request.getParameter("p")==null) {
			ReviewDAO.getReportedReview(request);
			ReviewDAO.paging(1, request);
			request.setAttribute("contentPage", "jsp/review/review_reported.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			ReviewDAO.getReportedReview(request);
			ReviewDAO.paging(Integer.parseInt(request.getParameter("p")), request);
			
			request.setAttribute("contentPage", "jsp/review/review_reported.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
