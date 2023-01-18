package com.semi.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.album.AlbumDAO;
import com.semi.auth.Auth;
import com.semi.auth.AuthDAO;

@WebServlet("/ReviewCommentC")
public class ReviewCommentC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		HttpSession hs = request.getSession();
		Auth a = (Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		if(a==null) {
			request.setAttribute("alert", "세션이 만료되었습니다. 재로그인 해주세요.");
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
		if (request.getParameter("commentId") == null) {
			ReviewDAO.setComment(request);
		} else {
			ReviewDAO.delComment(request);
		}
		
		ReviewDAO.getReview3(request);
		if(a!=null) {
			ReviewDAO.getLike(request);
			}
		ReviewDAO.getComment(request);
		
		request.setAttribute("contentPage", "jsp/review/review_detail.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
