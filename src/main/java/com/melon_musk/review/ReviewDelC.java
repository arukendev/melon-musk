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

@WebServlet("/ReviewDelC")
public class ReviewDelC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		if(a==null) {
			request.setAttribute("alert", "세션이 만료되었습니다. 재로그인 해주세요.");
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
		ReviewDAO.deleteReview(request);
		ReviewDAO.getNotice(request);
		ReviewDAO.getAllReview(request);
		ReviewDAO.paging(1, request);
		request.setAttribute("contentPage", "jsp/review/review.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
