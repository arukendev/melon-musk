package com.melon_musk.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthC")
public class AuthC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AuthDAO.loginCheck(request);
		request.setAttribute("contentPage", "jsp/auth/reg.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AuthDAO.setAccount(request);
		AuthDAO.loginCheck(request);
		
		request.setAttribute("contentPage", "jsp/auth/login.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
