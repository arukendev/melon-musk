package com.semi.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.auth.AuthDAO;

/**
 * Servlet implementation class regPlaylistC
 */
@WebServlet("/RegPlaylistC")
public class RegPlaylistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	AuthDAO.loginCheck(request);
	
	//�ø��� ����� ��� ���� ��ȸ
	PlaylistDAO.getRdao().getAllPlMusic(request);
	//�ø������ �������� �̵�
	request.setAttribute("contentPage", "jsp/playlist/regPlaylist.jsp");
	request.getRequestDispatcher("index.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
