package com.melon_musk.album;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon_musk.auth.AuthDAO;
import com.melon_musk.music.MusicDAO;


@WebServlet("/AlbumCommentC")
public class AlbumCommentC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AuthDAO.loginCheck(request)) {
			AlbumDAO.getAlbum(request);
			if (request.getParameter("commentId") == null) {
				AlbumDAO.setComment(request);
			} else {
				AlbumDAO.delComment(request);
			}
			
			AlbumDAO.getComment(request);
			
			request.setAttribute("contentPage", "jsp/album/album_info.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
