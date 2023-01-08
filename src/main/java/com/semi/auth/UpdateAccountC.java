package com.semi.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAccountC")
public class UpdateAccountC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (AuthDAO.loginCheck(request)) {
			request.setAttribute("contentPage", "account/update.jsp");
			
		}else {
			request.setAttribute("contentPage", "home.jsp");
			
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ϴ� ��
		if (	AuthDAO.loginCheck(request)) {
			AuthDAO.updateAccount(request);
		}
	
		//���?
		
		request.setAttribute("contentPage", "account/myPage.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
