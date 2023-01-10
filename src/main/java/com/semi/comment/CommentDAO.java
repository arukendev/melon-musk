package com.semi.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import com.semi.auth.Auth;
import com.semi.main.DBManager;
import com.semi.review.Review;

public class CommentDAO {

	public static void setComment(HttpServletRequest request) {
		
		Auth auth = (Auth) request.getSession().getAttribute("account");
		Review review = (Review) request.getAttribute("review");
		String commenttext = request.getParameter("contents");
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert review_comment values(re_comment_seq, ?, ?, ?)";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auth.getAu_id());
			pstmt.setInt(2, review.getId());
			pstmt.setString(3, commenttext);
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r","댓글 등록완료");
			}
		} catch (Exception e) {
			request.setAttribute("r","등록 실패");
			e.printStackTrace();
		}finally {
			
			DBManager.close(con, pstmt, null);
		}
	}

}
