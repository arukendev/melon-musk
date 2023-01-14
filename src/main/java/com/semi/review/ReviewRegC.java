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

@WebServlet("/ReviewRegC")
public class ReviewRegC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		if(a==null) {
			request.setAttribute("alert", "세션이 만료되었습니다. 재로그인 해주세요.");
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
		request.setAttribute("a", a);
		request.setAttribute("contentPage", "jsp/review/review_reg.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		ReviewDAO.reviewReg(request);
		request.setAttribute("contentPage", "jsp/review/review_detail.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
