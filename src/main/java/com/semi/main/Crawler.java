package com.semi.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.semi.album.Album;
import com.semi.artist.Artist;
import com.semi.artist.Artists;
import com.semi.chart.Chart;
import com.semi.music.Music;
import com.semi.newMusic.NewMusic;

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

	public static void artistTrackCrwaler(HttpServletRequest request) {
		String URL = "https://www.melon.com/artist/song.htm?artistId=" +
				request.getParameter("artistId") +
				"#params%5BlistType%5D=A&params%5BorderBy%5D=POPULAR_SONG_LIST&params%5BartistId%5D=203912&po=pageObj&startIndex=1";

		Connection con = Jsoup.connect(URL);
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
