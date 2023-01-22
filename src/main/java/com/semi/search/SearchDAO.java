package com.semi.search;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

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

}
