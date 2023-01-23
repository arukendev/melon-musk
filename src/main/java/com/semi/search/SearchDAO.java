package com.semi.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.auth.Auth;
import com.semi.main.DBManager;
import com.semi.main.IndexNum;

public class SearchDAO {

	public static void setPage(int page, HttpServletRequest request) {
		ArrayList<IndexNum> ins = (ArrayList<IndexNum>) request.getAttribute("indexes");
		
		request.setAttribute("curPageNo", page);
		
		int cnt = 10;	// 한페이지당 보여줄 개수
		int total = ins.size();	// 전체데이터개수
		int pageCount = (int) Math.ceil((double)total/cnt);
		
		request.setAttribute("pageCount", pageCount);
		
		int start = (page - 1) * cnt + 1;
		int end = start + cnt - 1;
		
		if (end > total) {
			end = total;
		}
		
		ArrayList<IndexNum> items = new ArrayList<IndexNum>();
		for (int i = start - 1; i < end; i++) {
			items.add(ins.get(i));
		}
		
		request.setAttribute("indexes", items);
	}

	public static void getLikeInfo(HttpServletRequest request) {
		ArrayList<SearchMusic> sms = (ArrayList<SearchMusic>) request.getAttribute("serachMusics");
		Auth a = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from music_like where muli_mu_id = ? and muli_au_id = ?";
		
		try {
			con = DBManager.connect();
			for (SearchMusic sm : sms) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sm.getMusicId());
				if (a == null) {
					pstmt.setString(2, "");
				} else {
					pstmt.setString(2, a.getAu_id());
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					sm.setLike(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

}
