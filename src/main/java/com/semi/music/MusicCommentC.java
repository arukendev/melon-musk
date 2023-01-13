package com.semi.music;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;


@WebServlet("/MusicCommentC")
public class MusicCommentC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		MusicDAO.getMusic(request);
		if (request.getParameter("commentId") == null) {
			MusicDAO.setComment(request);
		} else {
			MusicDAO.delComment(request);
		}
		
		MusicDAO.getComment(request);
		
		request.setAttribute("contentPage", "jsp/music/music_info.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

}
