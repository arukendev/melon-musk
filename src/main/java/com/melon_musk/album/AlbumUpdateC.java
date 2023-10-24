package com.melon_musk.album;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.artist.ArtistDAO;
import com.melon_musk.auth.AuthDAO;

@WebServlet("/AlbumUpdateC")
public class AlbumUpdateC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AuthDAO.loginCheck(request)) {
			AlbumDAO.getAlbum(request);
			request.setAttribute("contentPage", "jsp/album/album_update.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AuthDAO.loginCheck(request)) {
			AlbumDAO.updateAlbum(request);
			request.setAttribute("parameter", request.getParameter("albumId"));
			request.setAttribute("contentPage", "jsp/main/loading.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
