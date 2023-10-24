package com.melon_musk.music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;


@WebServlet("/MusicLikeDelC")
public class MusicLikeDelC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AuthDAO.loginCheck(request)) {
			MusicDAO.delLike(request);
			request.setAttribute("parameter", request.getParameter("musicId"));
			request.setAttribute("contentPage", "jsp/main/loading.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
