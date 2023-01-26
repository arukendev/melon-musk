package com.semi.playlist;

import java.io.IOException;
import java.util.ArrayList;

import javax.print.DocFlavor.STRING;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.auth.Auth;
import com.semi.auth.AuthDAO;

/**
 * Servlet implementation class PlDetailC
 */
@WebServlet("/PlDetailC")
public class PlDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		AuthDAO.loginCheck(request);
		
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		
	
		Boolean deleteOk = true;
		if(a==null) {
			request.setAttribute("contentPage", "jsp/auth/login.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			PlaylistDAO.getRdao().increaseView(request);
			PlaylistDAO.getRdao().getPlaylist(request);
			PlaylistDAO.getRdao().getPlaylist_onlyPl(request);
			String playlistPlId = (String) request.getAttribute("plAuId");
			
			
			
			String AuId = a.getAu_id();
			System.out.println(AuId);
			
			//플레이리스트 작성 id와 로그인 계정 id가 같거나 관리자아이디로 로그인하면 수정 삭제 할 수 있는 페이지로 이동
			if (playlistPlId.equals(AuId)|| AuId.equals("admin")  ) {
				request.setAttribute("deleteOK", deleteOk);
				request.setAttribute("contentPage", "jsp/playlist/playlistDetail_guest.jsp");	
				request.getRequestDispatcher("index.jsp").forward(request, response);
			//아니면 수정 삭제 불가능한 게스트 페이지로 이동
			}else{
				deleteOk = false;
				request.setAttribute("deleteOK", deleteOk);
				request.setAttribute("contentPage", "jsp/playlist/playlistDetail_guest.jsp");	
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			
			
			
			
		}
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
