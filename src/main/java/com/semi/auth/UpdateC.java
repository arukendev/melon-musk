package com.semi.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateC")
public class UpdateC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AuthDAO.loginCheck(request);
		if (AuthDAO.profile(request)) {
			request.setAttribute("contentPage", "jsp/auth/profile_update.jsp");
		} else {
			request.setAttribute("contentPage", "jsp/main/main.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		AuthDAO.updateAccount(request);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
