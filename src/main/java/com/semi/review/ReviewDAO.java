package com.semi.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.auth.Auth;
import com.semi.main.Comment;
import com.semi.main.DBManager;
import com.semi.review.Review;


public class ReviewDAO {
	private static ArrayList<Review> reviews;
	
	public static void getAllReview(HttpServletRequest request) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from review order by re_id desc";

			reviews = new ArrayList<Review>();
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				Review r = null;
				while(rs.next()) {
					// Bean
					r = new Review();
					r.setId(rs.getInt("re_id"));
					r.setName(rs.getString("re_name"));
					r.setImg(rs.getString("re_img"));
					r.setText(rs.getString("re_text"));
					r.setView(rs.getInt("re_view"));
					r.setLike(rs.getInt("re_like"));
					r.setDate(rs.getDate("re_date"));
					r.setAu_id(rs.getString("re_au_id"));
					r.setComment(rs.getInt("re_comment"));
					r.setReported(rs.getInt("re_report"));
					reviews.add(r);
				}
				request.setAttribute("reviews", reviews);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(con, pstmt, rs);
			}
			
		}

	public static void reviewReg(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			

			String sql = "insert into review values(review_seq.nextval,"
					+ "?, ?, ?, 0, 0, current_date, ?, 0, 0)";
			
			String path = request.getSession().getServletContext().getRealPath("files/review");
			System.out.println(path);
			
			MultipartRequest mr = new MultipartRequest(request,path,20*1024*1024,"utf-8",new DefaultFileRenamePolicy());
			String text = mr.getParameter("text");
			text = text.replaceAll("\\r\\n", "<br>");
			String img=null;
			if(mr.getFilesystemName("img")!=null) {
				img="*file^"+mr.getFilesystemName("img");
			}
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mr.getParameter("name"));
			pstmt.setString(2, img);
			pstmt.setString(3, text);
			pstmt.setString(4, a.getAu_id());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("????????????");
			}
			
			getReview2(request, mr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public static void paging(int page, HttpServletRequest request) {
		
			int cnt = 10;
			int total = reviews.size();
			int pageCount = (int)Math.ceil((double)total / cnt);
			
			int start = cnt * (page - 1) + 1;
			int end = (page == pageCount) ? total : start + cnt - 1;
			
			ArrayList<Review> items = new ArrayList<Review>();
			for (int i = start-1 ; i < end; i++) {
				items.add(reviews.get(i));
			}
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("reviews", items);
			
	}

	public static void getReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();
			int viewCnt = rs.getInt("re_view");
			viewCnt++;
			Review r = new Review(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_img"), rs.getString("re_text"), rs.getDate("re_date"), viewCnt, rs.getInt("re_like"),  rs.getString("re_au_id"), rs.getInt("re_comment"), rs.getInt("re_report"));
			countUpdate(viewCnt, rs.getInt("re_id"));
			request.setAttribute("review", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	// ?????? ??? ?????????????????? ?????? ???????????? ?????? ????????? re_id??? ?????? ????????? review ?????????.
	public static void getReview2(HttpServletRequest request, MultipartRequest mr) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_name = ? order by re_date desc";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mr.getParameter("name"));
			rs = pstmt.executeQuery();
			rs.next();
			int viewCnt = rs.getInt("re_view");
			viewCnt++;
			countUpdate(viewCnt, rs.getInt("re_id"));
			Review r = new Review(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_img"), rs.getString("re_text"), rs.getDate("re_date"), viewCnt, rs.getInt("re_like"), rs.getString("re_au_id"), rs.getInt("re_comment"), rs.getInt("re_report"));
			request.setAttribute("review", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	private static void countUpdate(int viewCnt, int re_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_view = ? where re_id = ?"
					);
			pstmt.setInt(1, viewCnt);
			pstmt.setInt(2, re_id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void updateReview(HttpServletRequest request) {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
			
			try {
				request.setCharacterEncoding("utf-8");
				String path = request.getSession().getServletContext().getRealPath("files/review");
				System.out.println(path);
				
				String img = request.getParameter("img");
				MultipartRequest mr = new MultipartRequest(request,path,20*1024*1024,"utf-8",new DefaultFileRenamePolicy());
				String text = mr.getParameter("text");
				text = text.replaceAll("\\r\\n", "<br>");
				if(mr.getFilesystemName("img")!=null) {
					img="*file^"+mr.getFilesystemName("img");
				} else if(mr.getFilesystemName("img")==null) {
					img=null;
				}
				
				con = DBManager.connect();
				pstmt = con.prepareStatement(
						"update review set re_name= ?, re_img= ?, re_text= ? where re_id= ?"
						);
				pstmt.setString(1, mr.getParameter("name"));
				pstmt.setString(2, img);
				pstmt.setString(3, text);
				pstmt.setString(4, mr.getParameter("no"));
				
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void deleteReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
			"delete from review where re_id = ?"
					);
			String re_id = request.getParameter("no");
			pstmt.setString(1, re_id);
			if(pstmt.executeUpdate() == 1) {
				System.out.println("?????? ?????? ??????");
				deleteReviewComment(re_id);
			}
		} catch (Exception e) {
			System.out.println("?????? ?????? ??????");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	private static void deleteReviewComment(String re_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"delete from review_comment where reco_re_id = ?"
					);
			pstmt.setString(1, re_id);
			if(pstmt.executeUpdate() >= 0) {
				System.out.println("????????? ????????? ????????????");
			}
			
		} catch (Exception e) {
			System.out.println("????????? ?????? ?????? ??????");
			e.printStackTrace();
		}
	}

	// getReview??? ???????????? ????????? ??? ?????????. ???????????? ?????????, ???????????? ??? ??? ??? ????????? ???????????? ??? ?????????.
	public static void getReview3(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();
			Review r = new Review(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_img"), rs.getString("re_text"), rs.getDate("re_date"), rs.getInt("re_view"), rs.getInt("re_like"),  rs.getString("re_au_id"), rs.getInt("re_comment"), rs.getInt("re_report"));
			request.setAttribute("review", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}


	public static void reviewLike(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"insert into review_like values(review_like_seq.nextval, ?, ?)"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("no"));
			pstmt.executeUpdate();
			
			reviewLikeUpdate(request);
			
			Like l = new Like();
			l.setAu_id(a.getAu_id());
			l.setRe_id(Integer.parseInt(request.getParameter("no")));
			
			request.setAttribute("like", l);
			
			
		} catch (Exception e) {
			request.setAttribute("alert", "???????????? 1?????? ???????????????.");
			e.printStackTrace();
		} 
		
		finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	private static void reviewLikeUpdate(HttpServletRequest request) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_like = re_like+1, re_view = re_view-1 where re_id = ?"
					);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	
	private static void reviewLikeCancelUpdate(HttpServletRequest request) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_like = re_like-1, re_view = re_view -1 where re_id = ?"
					);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void getLike(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"select * from review_like where reli_au_id=? and reli_re_id=?"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("no"));
			rs = pstmt.executeQuery();
			if(rs.next()) {			
			Like l = new Like();
			l.setAu_id(rs.getString("reli_au_id"));
			l.setRe_id(rs.getInt("reli_re_id"));
			System.out.println("{$like.au_id} = "+l.getAu_id());
			request.setAttribute("like", l);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void reviewLikeCancel(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"delete from review_like where reli_au_id=? and reli_re_id=?"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("no"));
			pstmt.executeUpdate();
			reviewLikeCancelUpdate(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void setComment(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a = (Auth) hs.getAttribute("account");
		String re_id = request.getParameter("no");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"insert into review_comment values(re_comment_seq.nextval, ?, ?, ?, current_date)"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, re_id);
			pstmt.setString(3, request.getParameter("txt"));
			
			if(pstmt.executeUpdate()==1) {
				System.out.println("????????????");
				reviewCommentUpdate(re_id);
			}
		} catch (Exception e) {
			System.out.println("????????????");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	private static void reviewCommentUpdate(String re_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_comment = re_comment +1, re_view= re_view - 1 where re_id = ?"
					);
			pstmt.setString(1, re_id);
			if(pstmt.executeUpdate()==1) {
				System.out.println("????????? +1");
			}
			
		} catch (Exception e) {
			System.out.println("????????? ??????????????? ??????");
			e.printStackTrace();
		}
		
	}

	public static void delComment(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"delete review_comment where reco_id = ?"
					);
			pstmt.setString(1, request.getParameter("commentId"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("????????????");
				reviewDeleteUpdate(request.getParameter("no"));
			}
		} catch (Exception e) {
			System.out.println("????????????");
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	private static void reviewDeleteUpdate(String re_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_comment = re_comment -1, re_view = re_view-1 where re_id = ?"
					);
			pstmt.setString(1, re_id);
			if(pstmt.executeUpdate()==1) {
				System.out.println("????????? -1");
			}
			
		} catch (Exception e) {
			System.out.println("????????? ??????????????? ??????");
			e.printStackTrace();
		}
	}

	public static void getComment(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select au_id, au_img, au_name, reco_id, reco_date, reco_txt "
				+ "from auth, review, review_comment "
				+ "where reco_au_id = au_id and reco_re_id = re_id and re_id = ? "
				+ "order by reco_date desc";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			
			ArrayList<Comment> comments = new ArrayList<Comment>(); 
			Comment c = null;
			while (rs.next()) {
				c = new Comment();
				c.setAuthId(rs.getString("au_id"));
				c.setImg(rs.getString("au_img"));
				c.setName(rs.getString("au_name"));
				c.setCommentId(rs.getString("reco_id"));
				c.setDate(rs.getDate("reco_date"));
				c.setTxt(rs.getString("reco_txt"));
				comments.add(c);
			}
			
			request.setAttribute("comments", comments);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void getBestReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		reviews = new ArrayList<Review>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"select * from review where re_like >= 3 and re_au_id != 'admin' order by re_id"
					);
			rs = pstmt.executeQuery();
			
			Review r = null;
			while(rs.next()) {
				r = new Review();
				r.setId(rs.getInt("re_id"));
				r.setName(rs.getString("re_name"));
				r.setImg(rs.getString("re_img"));
				r.setText(rs.getString("re_text"));
				r.setView(rs.getInt("re_view"));
				r.setLike(rs.getInt("re_like"));
				r.setDate(rs.getDate("re_date"));
				r.setAu_id(rs.getString("re_au_id"));
				r.setComment(rs.getInt("re_comment"));
				r.setReported(rs.getInt("re_report"));

				reviews.add(r);
			}
			request.setAttribute("reviews", reviews);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void reportReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_report = re_report +1 where re_id = ?"
					);
			pstmt.setString(1, request.getParameter("no"));
			if(pstmt.executeUpdate()==1) {
				System.out.println("?????? +1");
			} 
			
		} catch (Exception e) {
			System.out.println("db error");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getReportedReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		reviews = new ArrayList<Review>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"select * from review where re_report >= 1 order by re_id"
					);
			rs = pstmt.executeQuery();
			
			Review r = null;
			while(rs.next()) {
				r = new Review();
				r.setId(rs.getInt("re_id"));
				r.setName(rs.getString("re_name"));
				r.setImg(rs.getString("re_img"));
				r.setText(rs.getString("re_text"));
				r.setView(rs.getInt("re_view"));
				r.setLike(rs.getInt("re_like"));
				r.setDate(rs.getDate("re_date"));
				r.setAu_id(rs.getString("re_au_id"));
				r.setComment(rs.getInt("re_comment"));
				r.setReported(rs.getInt("re_report"));

				reviews.add(r);
			}
			request.setAttribute("reviews", reviews);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void getNotice(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_au_id = 'admin' order by re_id";

		reviews = new ArrayList<Review>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Review r = null;
			while(rs.next()) {
				// Bean
				r = new Review();
				r.setId(rs.getInt("re_id"));
				r.setName(rs.getString("re_name"));
				r.setImg(rs.getString("re_img"));
				r.setText(rs.getString("re_text"));
				r.setView(rs.getInt("re_view"));
				r.setLike(rs.getInt("re_like"));
				r.setDate(rs.getDate("re_date"));
				r.setAu_id(rs.getString("re_au_id"));
				r.setComment(rs.getInt("re_comment"));
				r.setReported(rs.getInt("re_report"));
				reviews.add(r);
			}
			request.setAttribute("notices", reviews);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void getMyReviews(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_au_id = ? order by re_id desc";

		reviews = new ArrayList<Review>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ((Auth)request.getSession().getAttribute("account")).getAu_id());
			rs = pstmt.executeQuery();
			
			Review r = null;
			while(rs.next()) {
				// Bean
				r = new Review();
				r.setId(rs.getInt("re_id"));
				r.setName(rs.getString("re_name"));
				r.setImg(rs.getString("re_img"));
				r.setText(rs.getString("re_text"));
				r.setView(rs.getInt("re_view"));
				r.setLike(rs.getInt("re_like"));
				r.setDate(rs.getDate("re_date"));
				r.setAu_id(rs.getString("re_au_id"));
				r.setComment(rs.getInt("re_comment"));
				r.setReported(rs.getInt("re_report"));
				reviews.add(r);
			}
			request.setAttribute("reviews", reviews);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}	
	
}

	
	
	


