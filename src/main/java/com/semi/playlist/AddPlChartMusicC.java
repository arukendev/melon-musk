package com.semi.playlist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.auth.Auth;
import com.semi.auth.AuthDAO;
import com.semi.chart.ChartDAO;
import com.semi.main.Crawler;
import com.semi.music.MusicDAO;

@WebServlet("/AddPlChartMusicC")
public class AddPlChartMusicC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 내 플레이리스트 조회 

		AuthDAO.loginCheck(request);
		
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		
		
		
		if(a==null) {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			if (!ChartDAO.musicIdCheck(request)) {
				Crawler.musicCrawler(request);
				MusicDAO.setMusic(request);
			}
		request.setAttribute("a", a);
		PlaylistDAO.getRdao().getMyPlaylist(request);
		request.setAttribute("contentPage", "jsp/playlist/myPlaylist.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
AuthDAO.loginCheck(request);
		
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
	
	
		if(a==null) {
			request.setAttribute("alert", " 로그인 정보가 사라졌어요ㅜ. 재로그인 해주세요.");
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
		request.setAttribute("a", a);
		//플리에 노래 추가하는 일
		PlaylistDAO.getRdao().addPlMusic(request);
		//노래가 추가된 플리 조회하기
		PlaylistDAO.getRdao().getPlaylist(request);
		request.setAttribute("contentPage", "jsp/playlist/playlistDetail.jsp");	
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
	
	
	
	
	
	}

}
