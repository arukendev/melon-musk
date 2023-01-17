package com.semi.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;
import com.semi.review.ReviewDAO;

@WebServlet("/PlaylistPageC")
public class PlaylistPageC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthDAO.loginCheck(request);
		
		PlaylistDAO.getRdao().getAllPlMusic(request);
		PlaylistDAO.getRdao().paging(Integer.parseInt(request.getParameter("p")), request);
	
		
		request.setAttribute("contentPage", "jsp/playlist/regPlaylistMusic_test.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
