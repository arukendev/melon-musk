const param = document.querySelector("#param");

if (location.href.includes("MusicLike")) {
  location.href = "MusicC?musicId=" + param.value;
}

if (location.href.includes("MusicComment")) {
  location.href = "MusicC?musicId=" + param.value;
}

if (location.href.includes("ArtistLike")) {
  location.href = "ArtistC?artistId=" + param.value;
}

if (location.href.includes("ArtistComment")) {
  location.href = "ArtistC?artistId=" + param.value;
}

if (location.href.includes("AlbumLike")) {
  location.href = "AlbumC?albumId=" + param.value;
}

if (location.href.includes("AlbumComment")) {
  location.href = "AlbumC?albumId=" + param.value;
}
