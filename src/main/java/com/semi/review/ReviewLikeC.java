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

/**
 * Servlet implementation class ReviewLikeC
 */
@WebServlet("/ReviewLikeC")
public class ReviewLikeC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		if(a==null) {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			ReviewDAO.reviewLike(request);
			ReviewDAO.getReview3(request);
			
			request.setAttribute("contentPage", "jsp/review/review_detail.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
