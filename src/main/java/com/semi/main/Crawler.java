package com.semi.main;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.semi.album.Album;
import com.semi.album.AlbumMusic;
import com.semi.artist.Artist;
import com.semi.artist.ArtistAlbum;
import com.semi.artist.ArtistMusic;
import com.semi.artist.Artists;
import com.semi.chart.Chart;
import com.semi.music.Music;
import com.semi.newMusic.NewMusic;
import com.semi.search.SearchAlbum;
import com.semi.search.SearchArtist;
import com.semi.search.SearchMusic;

public class Crawler {

	public static void chartCrawler(HttpServletRequest request) {
		String URL = "https://www.melon.com/chart/index.htm";

		Connection con = Jsoup.connect(URL);

		try {
			Document html = con.get();

			Elements imgElms = html.select(".image_typeAll img");
			Elements musicInfoElms = html.select(".song_info");
			Elements musicElms = html.select(".rank01 a");
			Elements artistElms = html.select(".rank02 span a:first-child");
			Elements albumElms = html.select(".rank03 a");

			Element imgElm = null;
			Element musicElm = null;
			Element musicInfoElm = null;
			Element artistElm = null;
			Element albumElm = null;
			
			String imgSrc = "";
			String musicTxt = "";
			String musicLink = "";
			String artistTxt = "";
			String artistLink = "";
			String albumTxt = "";
			String albumLink = "";
			
			ArrayList<Chart> charts = new ArrayList<Chart>();
			Chart c = null;

			for (int i = 0; i < imgElms.size(); i++) {
				imgElm = imgElms.get(i);
				musicElm = musicElms.get(i);
				musicInfoElm = musicInfoElms.get(i);
				artistElm = artistElms.get(i);
				albumElm = albumElms.get(i);
				imgSrc = imgElm.attr("src");
				musicTxt = musicElm.text();
				musicLink = musicInfoElm.attr("href").replaceAll("[^0-9]", "");
				artistTxt = artistElm.text();
				artistLink = artistElm.attr("href").replaceAll("[^0-9]", "");
				albumTxt = albumElm.text();
				albumLink = albumElm.attr("href").replaceAll("[^0-9]", "");
				c = new Chart();
				c.setRank(i + 1);
				c.setImg(imgSrc);
				c.setMusic(musicTxt);
				c.setMusicId(musicLink);
				c.setArtist(artistTxt);
				c.setArtistId(artistLink);
				c.setAlbum(albumTxt);
				c.setAlbumId(albumLink);
				charts.add(c);
			}
			request.setAttribute("charts", charts);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void newMusicCrawler(HttpServletRequest request) {
		String URL = "https://www.melon.com/new/index.htm";

		Connection con = Jsoup.connect(URL);
		
		try {
			Document html = con.get();
			
			Elements imgElms = html.select(".image_typeAll img");
			Elements musicInfoElms = html.select(".song_info");
			Elements musicElms = html.select(".rank01 a");
			Elements artistElms = html.select(".rank02 span a:first-child");
			Elements albumElms = html.select(".rank03 a");
			
			Element imgElm = null;
			Element musicElm = null;
			Element musicInfoElm = null;
			Element artistElm = null;
			Element albumElm = null;
			
			String imgSrc = "";
			String musicTxt = "";
			String musicLink = "";
			String artistTxt = "";
			String artistLink = "";
			String albumTxt = "";
			String albumLink = "";
			
			ArrayList<NewMusic> newMusics = new ArrayList<NewMusic>();
			NewMusic n = null;
			for (int i = 0; i < imgElms.size(); i++) {
				imgElm = imgElms.get(i);
				musicElm = musicElms.get(i);
				musicInfoElm = musicInfoElms.get(i);
				artistElm = artistElms.get(i);
				albumElm = albumElms.get(i);
				imgSrc = imgElm.attr("src");
				musicTxt = musicElm.text();
				musicLink = musicInfoElm.attr("href").replaceAll("[^0-9]", "");
				artistTxt = artistElm.text();
				artistLink = artistElm.attr("href").replaceAll("[^0-9]", "");
				albumTxt = albumElm.text();
				albumLink = albumElm.attr("href").replaceAll("[^0-9]", "");
				n = new NewMusic();
				n.setRank(i + 1);
				n.setImg(imgSrc);
				n.setMusic(musicTxt);
				n.setMusicId(musicLink);
				n.setArtist(artistTxt);
				n.setArtistId(artistLink);
				n.setAlbum(albumTxt);
				n.setAlbumId(albumLink);
				newMusics.add(n);
			}
			request.setAttribute("newMusic", newMusics);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void artistCrawler(HttpServletRequest request) {
		String URL = "https://www.melon.com/artist/detail.htm?artistId=" + request.getParameter("artistId");

		Connection con = Jsoup.connect(URL);

		try {
			Document html = con.get();
			Elements imgElms = html.select("#artistImgArea img");
			Elements nameElms = html.select(".title_atist");
			Elements memberIdElms = html.select(".wrap_atistname a");
			Elements memberNameElms = html.select(".wrap_atistname a span:first-child");
			Elements infoElms = html.select(".wrap_atist_info .atist_info *");
			
			
			Element infoElm = null;
			String type = "none";
			String company = "none";
			String debut = "none";
			String birth = "none";
			String info = "none";
			
			for (int i = 0; i < infoElms.size(); i++) {
				infoElm = infoElms.get(i);
				if (infoElm.text().equals("활동유형")) {
					type = infoElms.get(i + 1).text();
				}
				if (infoElm.text().equals("소속사")) {
					company = infoElms.get(i + 1).text();
				}
				if (infoElm.text().equals("데뷔")) {
					debut = infoElms.get(i + 1).getElementsByTag("span").text().replace("곡재생", "").replace("19금", "");
				}
				if (infoElm.text().equals("생일")) {
					birth = infoElms.get(i + 1).text();
				}
			}
			
			Element imgElm = imgElms.first();
			Element nameElm = nameElms.first();
			Element memberIdElm = null;
			Element memberNameElm = null;
			
			String memId = "none";
			String memName = "none";
			ArrayList<Artists> members = new ArrayList<Artists>();
			Artist a = new Artist();
			Artists as = null;
			if (memberIdElms.size() != 0) {
				for (int i = 0; i < memberIdElms.size(); i++) {
					memberIdElm = memberIdElms.get(i);
					memberNameElm = memberNameElms.get(i);
					memId = memberIdElm.attr("href").replaceAll("[^0-9]", "");
					memName = memberNameElm.text();
					as = new Artists();
					as.setId(memId);
					as.setName(memName);
					members.add(as);
				}
			}
			
			String name = nameElm.text().replace("아티스트명", "");
			String img = imgElm.attr("src");
			
			if (img.equals("https://cdnimg.melon.co.kr")) {
				img = "none";
			}
			
			a.setId(request.getParameter("artistId"));
			a.setName(name);
			a.setImg(img);
			a.setType(type);
			a.setCompany(company);
			a.setDebut(debut);
			a.setBirth(birth);
			a.setInfo(info);
			
			request.setAttribute("artist", a);
			request.setAttribute("members", members);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void albumCrawler(HttpServletRequest request) {
		String URL = "https://www.melon.com/album/detail.htm?albumId=" + request.getParameter("albumId");

		Connection con = Jsoup.connect(URL);

		try {
			Document html = con.get();
			
			Elements arIdElms = html.select(".artist .artist_name");
			Elements arNameElms = html.select(".artist .artist_name span:first-child");
			Elements nameElms = html.select(".song_name");
			Elements imgElms = html.select("#d_album_org img");
			Elements typeElms = html.select(".gubun");
			Elements infoElms = html.select(".meta .list *");
			
			Element infoElm = null;
			String date = "none";
			String genre = "none";
			String publisher = "none";
			String agency = "none";
			String info = "none";
			
			for (int i = 0; i < infoElms.size(); i++) {
				infoElm = infoElms.get(i);
				if (infoElm.text().equals("발매일")) {
					date = infoElms.get(i + 1).text();
				}
				if (infoElm.text().equals("장르")) {
					genre = infoElms.get(i + 1).text();
				}
				if (infoElm.text().equals("발매사")) {
					publisher = infoElms.get(i + 1).text();
				}
				if (infoElm.text().equals("기획사")) {
					agency = infoElms.get(i + 1).text();
				}
			}
			
			Element arIdElm = arIdElms.first();
			Element arNameElm = arNameElms.first();
			Element nameElm = nameElms.first();
			Element imgElm = imgElms.first();
			Element typeElm = typeElms.first();
			
			String arId = "";
			String arName = "";
			String name = nameElm.text().replace("앨범명 ", "");
			String img = imgElm.attr("src");
			String type = typeElm.text().replace("[", "").replace("]", "");
			
			Album a = new Album();
			Artists ar = null;
			ArrayList<Artists> artists = new ArrayList<Artists>();
			
			if (arIdElm == null) {
				arId = "0";
				arName = "Various Artists";
				ar = new Artists();
				ar.setId(arId);
				ar.setName(arName);
				artists.add(ar);
			} else {
				for (int i = 0; i < arNameElms.size(); i++) {
					arIdElm = arIdElms.get(i);
					arNameElm = arNameElms.get(i);
					arId = arIdElm.attr("href").replaceAll("[^0-9]", "");
					arName = arNameElm.text();
					ar = new Artists();
					ar.setId(arId);
					ar.setName(arName);
					artists.add(ar);
				}
			}
			
			a.setId(request.getParameter("albumId"));
			a.setName(name);
			a.setImg(img);
			a.setDate(date);
			a.setType(type);
			a.setGenre(genre);
			a.setPublisher(publisher);
			a.setAgency(agency);
			a.setInfo(info);
			
			request.setAttribute("album", a);
			request.setAttribute("artists", artists);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void musicCrawler(HttpServletRequest request) {
		String URL = "https://www.melon.com/song/detail.htm?songId=" + request.getParameter("musicId");

		Connection con = Jsoup.connect(URL);

		try {
			Document html = con.get();
			
			Elements arIdElms = html.select(".artist .artist_name");
			Elements arNameElms = html.select(".artist .artist_name span:first-child");
			Elements alIdElms = html.select(".list dd:nth-child(2) a");
			Elements alNameElms = html.select(".list dd:nth-child(2) a");
			Elements alImgElms = html.select(".thumb .image_typeAll img");
			Elements dateElms = html.select(".list dd:nth-child(4)");
			Elements nameElms = html.select(".song_name");
			Elements genreElms = html.select(".list dd:nth-child(6)");
			Elements lyricsElms = html.select("#d_video_summary");
			
			Element arIdElm = arIdElms.first();
			Element arNameElm = arNameElms.first();
			Element alIdElm = alIdElms.first();
			Element alNameElm = alNameElms.first();
			Element alImgElm = alImgElms.first();
			Element dateElm = dateElms.first();
			Element nameElm = nameElms.first();
			Element genreElm = genreElms.first();
			Element lyricsElm = lyricsElms.first();
			
			String lyrics = "none";
			String link = "none";
			
			if (lyricsElm != null) {
				lyrics = lyricsElm.html().replace("<!-- height:auto; 로 변경시, 확장됨 --> ", "");
			}
			
			String arId = "";
			String arName = "";
			Artists ar = null;
			ArrayList<Artists> artists = new ArrayList<Artists>();
			if (arIdElm == null) {
				arId = "0";
				arName = "Various Artists";
				ar = new Artists();
				ar.setId(arId);
				ar.setName(arName);
				artists.add(ar);
			} else {
				for (int i = 0; i < arNameElms.size(); i++) {
					arIdElm = arIdElms.get(i);
					arNameElm = arNameElms.get(i);
					arId = arIdElm.attr("href").replaceAll("[^0-9]", "");
					arName = arNameElm.text();
					ar = new Artists();
					ar.setId(arId);
					ar.setName(arName);
					artists.add(ar);
				}
			}
			String alId = alIdElm.attr("href").replaceAll("[^0-9]", "");
			String alName = alNameElm.text();
			String alImg = alImgElm.attr("src");
			String date = dateElm.text();
			String name = nameElm.text().replace("곡명 ", "");
			String genre = genreElm.text();
			
			Music m = new Music();
			m.setId(request.getParameter("musicId"));
			m.setAlId(alId);
			m.setAlName(alName);
			m.setAlImg(alImg);
			m.setDate(date);
			m.setName(name);
			m.setGenre(genre);
			m.setLyrics(lyrics);
			m.setLink(link);
			
			request.setAttribute("music", m);
			request.setAttribute("artists", artists);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pageCrawler(HttpServletRequest request) {
		String muIndex = request.getParameter("muIndex");
		String URL = "";
		if (muIndex != null) {
			URL = "https://www.melon.com/artist/song.htm?artistId="
					+ request.getParameter("artistId");
		} else {
			URL = "https://www.melon.com/artist/album.htm?artistId="
					+ request.getParameter("artistId");
		}
		
		Connection con = Jsoup.connect(URL);
		
		try {
			Document html = con.get();
			Elements musicNumberElms = html.select("#sort_layer .text");
			
			Element musicNumberElm = musicNumberElms.first();
			String musicNumber = musicNumberElm.text().replaceAll("[^0-9]", "");
			int musicNumberPages = 0;
			if (Integer.parseInt(musicNumber) % 50 == 0) {
				musicNumberPages = Integer.parseInt(musicNumber) / 50;
			} else {
				musicNumberPages = Integer.parseInt(musicNumber) / 50 + 1;
			}
			int musicNumberValues = 0;
			ArrayList<IndexNum> ins = new ArrayList<IndexNum>();
			IndexNum in = null;
			for (int i = 0; i < musicNumberPages; i++) {
				musicNumberValues = i * 50 + 1;
				in = new IndexNum();
				in.setNumber(i + 1);
				in.setValue(musicNumberValues);
				ins.add(in);
			}
			request.setAttribute("indexs", ins);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void artistMusicCrwaler(HttpServletRequest request) {
		String muIndex = request.getParameter("muIndex");
		String URL = "";
		if (muIndex == null) {
			URL = "https://www.melon.com/artist/songPaging.htm?startIndex=1&pageSize=50&listType=A&orderBy=POPULAR_SONG_LIST&artistId="
					+ request.getParameter("artistId");
		} else {
			URL = "https://www.melon.com/artist/songPaging.htm?startIndex=" + muIndex +"&pageSize=50&listType=A&orderBy=POPULAR_SONG_LIST&artistId="
					+ request.getParameter("artistId");
		}
		

		Connection con = Jsoup.connect(URL);
				
		try {
			Document html = con.get();
			
			Elements indexElms = html.select("tbody .no .wrap");
			Elements musicIdElms = html.select("tbody .btn_icon_detail");
			Elements musicNameElms = html.select("tbody .btn_icon_detail span");
			Elements artistElms = html.select("tbody #artistName span");
			
			Element indexElm = null;
			Element musicIdElm = null;
			Element musicNameElm = null;
			Element artistElm = null;
			
			String index = "";
			String musicId = "";
			String musicName = "";
			String artist = "";
			
			ArrayList<ArtistMusic> apList = new ArrayList<ArtistMusic>();
			ArtistMusic am = null;
			
			for (int i = 0; i < musicIdElms.size(); i++) {
				am = new ArtistMusic();
				indexElm = indexElms.get(i);
				musicIdElm = musicIdElms.get(i);
				musicNameElm = musicNameElms.get(i);
				artistElm = artistElms.get(i);
				
				index = indexElm.text();
				musicId = musicIdElm.attr("href").replaceAll("[^0-9]", "");
				musicName = musicNameElm.text();
				artist = artistElm.text();
				am.setRank(index);
				am.setId(musicId);
				am.setName(musicName);
				am.setArtist(artist);
				apList.add(am);
				if (muIndex == null) {
					if (i == 9) {
						break;
					}
				}
			}
			
			request.setAttribute("artistMusic", apList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void artistAlbumCrwaler(HttpServletRequest request) {
		String alIndex = request.getParameter("alIndex");
		String URL = "";
		if (alIndex == null) {
			URL = "https://www.melon.com/artist/albumPaging.htm?startIndex=1&pageSize=15&listType=0&orderBy=ISSUE_DATE&artistId="
					+ request.getParameter("artistId");
		} else {
			URL = "https://www.melon.com/artist/albumPaging.htm?startIndex=" + alIndex +"&pageSize=50&listType=0&orderBy=ISSUE_DATE&artistId="
					+ request.getParameter("artistId");
		}
		

		Connection con = Jsoup.connect(URL);
		
		try {
			
			Document html = con.get();
			
			Elements albumImgElms = html.select(".wrap_album04 a:first-child img");
			Elements albumIdElms = html.select(".wrap_album04 dt:first-child a");
			Elements albumNameElms = html.select(".wrap_album04 dt:first-child a");
			Elements albumTypeElms = html.select(".wrap_album04 dt:first-child .vdo_name");
			Elements albumDateElms = html.select(".wrap_album04 .atist_info .wrap_btn .cnt_view");
			Elements albumNumElms = html.select(".wrap_album04 .atist_info .wrap_btn .tot_song");
						
			Element albumImgElm = null;
			Element albumIdElm = null;
			Element albumNameElm = null;
			Element albumTypeElm = null;
			Element albumDateElm = null;
			Element albumNumElm = null;
			
			String albumImg = "";
			String albumId = "";
			String albumName = "";
			String albumType = "";
			String albumDate = "";
			String albumNum = "";
			
			ArrayList<ArtistAlbum> aaList = new ArrayList<ArtistAlbum>();
			ArtistAlbum aa = null;
			
			for (int i = 0; i < albumIdElms.size(); i++) {
				aa = new ArtistAlbum();
				albumImgElm = albumImgElms.get(i);
				albumIdElm = albumIdElms.get(i);
				albumNameElm = albumNameElms.get(i);
				albumTypeElm = albumTypeElms.get(i);
				albumDateElm = albumDateElms.get(i);
				albumNumElm = albumNumElms.get(i);
				
				albumImg = albumImgElm.attr("src");
				albumId = albumIdElm.attr("href").replaceAll("[^0-9]", "");
				albumName = albumNameElm.text();
				albumType = albumTypeElm.text().replace("[", "").replace("]", "");
				albumDate = albumDateElm.text();
				albumNum = albumNumElm.text();
				
				aa.setId(albumId);
				aa.setImg(albumImg);
				aa.setName(albumName);
				aa.setDate(albumDate);
				aa.setNum(albumNum);
				aa.setType(albumType);
				aaList.add(aa);
				
				if (alIndex == null) {
					if (i == 4) {
						break;
					}
				}
			}
			
			
			request.setAttribute("artistAlbum", aaList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void albumMusicCrawler(HttpServletRequest request) {
		String URL = "https://www.melon.com/album/detail.htm?albumId=" + request.getParameter("albumId");

		Connection con = Jsoup.connect(URL);

		try {
			Document html = con.get();
			Elements cdElms = html.select(".cd strong");
			Elements musicIdElms = html.select("tr[data-group-items=cd1] td:nth-child(3) a");
			Elements musicNameElms = html.select("tr[data-group-items=cd1] td:nth-child(3) a");
			Elements musicArtistIdElms = html.select(".wrap_song_info .rank02 span a:first-child");
			Elements musicArtistElms = html.select(".wrap_song_info .rank02 span");
			
			Element musicIdElm = null;
			Element musicNameElm = null;
			Element musicArtistIdElm = null;
			Element musicArtistElm = null;
			
			String musicId = "";
			String musicName = "";
			String musicArtistId = "";
			String musicArtist = "";
			
			ArrayList<AlbumMusic> ams = new ArrayList<AlbumMusic>();
			ArrayList<Integer> cdIndex = new ArrayList<Integer>();
			AlbumMusic am = null;
			if (cdElms.size() != 0) {
				for (int i = 0; i < cdElms.size(); i++) {
					musicIdElms = html.select("tr[data-group-items=cd" + (i + 1) + "] td:nth-child(3) a");
					musicNameElms = html.select("tr[data-group-items=cd" + (i + 1) + "] td:nth-child(3) a");
					musicArtistIdElms = html.select("tr[data-group-items=cd" + (i + 1) + "] .wrap_song_info .rank02 a:first-child");
					musicArtistElms = html.select("tr[data-group-items=cd" + (i + 1) + "] .wrap_song_info .rank02 a:first-child");
					cdIndex.add(i + 1);
					for (int j = 0; j < musicIdElms.size(); j++) {
						musicIdElm = musicIdElms.get(j);
						musicNameElm = musicNameElms.get(j);
						musicArtistIdElm = musicArtistIdElms.get(j);
						musicArtistElm = musicArtistElms.get(j);
						
						musicId = musicIdElm.attr("href").replaceAll("[^0-9]", "");
						musicName = musicNameElm.attr("title").replace(" 곡정보", "");
						musicArtistId = musicArtistIdElm.attr("href").replaceAll("[^0-9]", "");
						musicArtist = musicArtistElm.text();
						
						am = new AlbumMusic();
						am.setCd(i + 1);
						am.setNum(j + 1);
						am.setId(musicId);
						am.setName(musicName);
						am.setArtistId(musicArtistId);
						am.setArtist(musicArtist);
						
						ams.add(am);
					}
				}
			} else {
				for (int i = 0; i < musicIdElms.size(); i++) {
					musicIdElm = musicIdElms.get(i);
					musicNameElm = musicNameElms.get(i);
					musicArtistIdElm = musicArtistIdElms.get(i);
					musicArtistElm = musicArtistElms.get(i);
					
					musicId = musicIdElm.attr("href").replaceAll("[^0-9]", "");
					musicName = musicNameElm.attr("title").replace(" 곡정보", "");
					musicArtistId = musicArtistIdElm.attr("href").replaceAll("[^0-9]", "");
					musicArtist = musicArtistElm.text();
					
					am = new AlbumMusic();
					am.setCd(0);
					am.setNum(i + 1);
					am.setId(musicId);
					am.setName(musicName);
					am.setArtistId(musicArtistId);
					am.setArtist(musicArtist);
					
					ams.add(am);
				}
			}
			
			request.setAttribute("cdIndex", cdIndex);
			request.setAttribute("albumMusics", ams);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void searchCrawler(HttpServletRequest request) {

		String URL = "https://www.melon.com/search/artist/listArtists.htm?startIndex="
				+ request.getParameter("index")
				+ "&pageSize=20&q="
				+ request.getParameter("result")
				+ "&sq=&sort=weight&section=all&sex=&actType=&domestic=&genreCd=&actYear=";
		
		String[] sels = request.getParameterValues("sel");
		for (String sel : sels) {
			if (sel.equals("al")) {
				URL = "https://www.melon.com/search/album/index.htm?startIndex=1&pageSize=21&q="
						+ request.getParameter("result")
						+ "&sortorder=&section=all&sectionId=&genreDir=&sort=hit&mwkLogType=T";
				Connection con = Jsoup.connect(URL);
				try {
					Document html = con.get();
					
					Elements albumImgElms = html.select(".wrap_album04 a:first-child img");
					Elements albumTypeElms = html.select(".wrap_album04 dt span:first-child");
					Elements albumIdElms = html.select(".wrap_album04 dt a");
					Elements albumNameElms = html.select(".wrap_album04 dt a");
					Elements albumDateElms = html.select(".wrap_album04 .cnt_view");
					Elements albumNumElms = html.select(".wrap_album04 .tot_song");
					Elements artistNameElms = html.select(".wrap_album04 .atistname .checkEllipsisSearchAlbum");
										
					Element albumImgElm = null;
					Element albumTypeElm = null;
					Element albumIdElm = null;
					Element albumNameElm = null;
					Element albumDateElm = null;
					Element albumNumElm = null;
					Element artistNameElm = null;
					
					String albumImg = "";
					String albumType = "";
					String albumId = "";
					String albumName = "";
					String albumDate = "";
					String albumNum = "";
					String artistName = "";
					
					ArrayList<SearchAlbum> sals = new ArrayList<SearchAlbum>();
					SearchAlbum sal = null;
					
					for (int i = 0; i < albumIdElms.size(); i++) {
						albumImgElm = albumImgElms.get(i);
						albumTypeElm = albumTypeElms.get(i);
						albumIdElm = albumIdElms.get(i);
						albumNameElm = albumNameElms.get(i);
						albumDateElm = albumDateElms.get(i);
						albumNumElm = albumNumElms.get(i);
						artistNameElm = artistNameElms.get(i);
						
						albumImg = albumImgElm.attr("src");
						albumType = albumTypeElm.text().replace("[", "").replace("]", "");
						albumId = albumIdElm.attr("href");
						albumName = albumNameElm.text();
						albumDate = albumDateElm.text();
						albumNum = albumNumElm.text();
						artistName = artistNameElm.text();
						
						albumId = albumId.substring(albumId.indexOf(";")).replaceAll("[^0-9]", "");
						
						sal = new SearchAlbum();
						sal.setImg(albumImg);
						sal.setType(albumType);
						sal.setId(albumId);
						sal.setName(albumName);
						sal.setDate(albumDate);
						sal.setNum(albumNum);
						sal.setArtist(artistName);
						sals.add(sal);
					}
					
					request.setAttribute("searchAlbums", sals);
					request.setAttribute("contentPage", "jsp/search/search_album.jsp");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else if (sel.equals("mu")){
				
				URL = "https://www.melon.com/search/song/index.htm?startIndex=1&pageSize=50&q="
						+ request.getParameter("result")
						+ "&sort=hit&section=all&sectionId=&genreDir=&mwkLogType=T";
				
				Connection con = Jsoup.connect(URL);
				try {
					
					Document html = con.get();
					
					Elements musicElms = html.select("tbody tr td:nth-child(3) .btn_icon_detail");
					Elements artistElms = html.select("tbody tr td:nth-child(4) .checkEllipsisSongdefaultList");
					
					Element musicElm = null;
					Element artistElm = null;
					
					String musicId = "";
					String musicName = "";
					String artistName = "";
					
					SearchMusic sm = null;
					ArrayList<SearchMusic> sms = new ArrayList<SearchMusic>();
					
					for (int i = 0; i < musicElms.size(); i++) {
						musicElm = musicElms.get(i);
						artistElm = artistElms.get(i);
						
						musicId = musicElm.attr("href");
						musicName = musicElm.text();
						artistName = artistElm.text();
						
						
						musicId = musicId.substring(musicId.indexOf(";")).replaceAll("[^0-9]", "");
						musicName = musicName.replace(" 상세정보 페이지 이동", "");
						
						sm = new SearchMusic();
						sm.setMusicId(musicId);
						sm.setMusicName(musicName);
						sm.setArtistName(artistName);
						sms.add(sm);
					}
					request.setAttribute("serachMusics", sms);
					request.setAttribute("contentPage", "jsp/search/search_music.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Connection con = Jsoup.connect(URL);
				try {
					
					Document html = con.get();
					
					Elements artistImgElms = html.select(".wrap_atist12 a:first-child img");
					Elements artistIdElms = html.select(".wrap_atist12 dt a");
					Elements artistNameElms = html.select(".wrap_atist12 dt a");
					Elements artistInfoElms = html.select(".wrap_atist12 .gubun");
					
					ArrayList<SearchArtist> sars = new ArrayList<SearchArtist>();
					SearchArtist sar = null;
					
					for (int i = 0; i < artistIdElms.size(); i++) {
						Element artistImgElm = artistImgElms.get(i);
						Element artistIdElm = artistIdElms.get(i);
						Element artistNameElm = artistNameElms.get(i);
						Element artistInfoElm = artistInfoElms.get(i);
						
						String artistImg = artistImgElm.attr("src");
						String artistId = artistIdElm.attr("href");
						String artistName = artistNameElm.text();
						String artistInfo = artistInfoElm.text();
						
						artistId = artistId.substring(artistId.indexOf(";")).replaceAll("[^0-9]", "");
						if (artistImg.equals("https://cdnimg.melon.co.kr")) {
							artistImg = "files/main/mark.png";
						}
						
						
						sar = new SearchArtist();
						sar.setId(artistId);
						sar.setImg(artistImg);
						sar.setName(artistName);
						sar.setInfo(artistInfo);
						sars.add(sar);
					}
					
					request.setAttribute("searchArtists", sars);
					request.setAttribute("contentPage", "jsp/search/search_artist.jsp");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	public static void searchPage(HttpServletRequest request) {
		
		String URL = "https://www.melon.com/search/artist/index.htm?q="
				+ request.getParameter("result")
				+ "&section=&searchGnbYn=Y&kkoSpl=N&kkoDpType=";
		
		String[] sels = request.getParameterValues("sel");
		
		for (String sel : sels) {
			if (sel.equals("al")) {
				
				URL = "https://www.melon.com/search/album/index.htm?q="
						+ request.getParameter("result")
						+ "&section=&searchGnbYn=Y&kkoSpl=N&kkoDpType=";
				
				Connection con = Jsoup.connect(URL);
				
				try {
					Document html = con.get();
					Elements numberElms = html.select(".serch_totcnt em");
					
					Element numberElm = numberElms.first();
					String number = numberElm.text().replace(",", "");
					int numberPages = 0;
					if (Integer.parseInt(number) % 21 == 0) {
						numberPages = Integer.parseInt(number) / 21;
					} else {
						numberPages = Integer.parseInt(number) / 21 + 1;
					}
					int numberValues = 0;
					ArrayList<IndexNum> ins = new ArrayList<IndexNum>();
					IndexNum in = null;
					for (int i = 0; i < numberPages; i++) {
						numberValues = i * 21 + 1;
						in = new IndexNum();
						in.setNumber(i + 1);
						in.setValue(numberValues);
						ins.add(in);
					}
					request.setAttribute("indexs", ins);
					request.setAttribute("number", number);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (sel.equals("mu")) {
				
			} else {
				Connection con = Jsoup.connect(URL);
				
				try {
					Document html = con.get();
					Elements numberElms = html.select(".serch_totcnt em");
					
					Element numberElm = numberElms.first();
					String number = numberElm.text().replace(",", "");
					int numberPages = 0;
					if (Integer.parseInt(number) % 20 == 0) {
						numberPages = Integer.parseInt(number) / 20;
					} else {
						numberPages = Integer.parseInt(number) / 20 + 1;
					}
					int numberValues = 0;
					ArrayList<IndexNum> ins = new ArrayList<IndexNum>();
					IndexNum in = null;
					for (int i = 0; i < numberPages; i++) {
						numberValues = i * 20 + 1;
						in = new IndexNum();
						in.setNumber(i + 1);
						in.setValue(numberValues);
						ins.add(in);
					}
					request.setAttribute("indexs", ins);
					request.setAttribute("number", number);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	

}
