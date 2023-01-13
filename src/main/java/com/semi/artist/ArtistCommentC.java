package com.semi.artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.album.AlbumDAO;
import com.semi.auth.AuthDAO;
import com.semi.music.MusicDAO;

@WebServlet("/ArtistCommentC")
public class ArtistCommentC extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		ArtistDAO.getArtist(request);

		if (request.getParameter("commentId") == null) {
			ArtistDAO.setComment(request);
		} else {
			ArtistDAO.delComment(request);
		}
		
		ArtistDAO.getComment(request);
		
		request.setAttribute("contentPage", "jsp/artist/artist_info.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
