const param = document.querySelector("#param");

if (
  location.href.includes("MusicUpdate") ||
  location.href.includes("MusicComment") ||
  location.href.includes("MusicLike")
) {
  location.href = "MusicC?musicId=" + param.value;
}

if (
  location.href.includes("ArtistUpdate") ||
  location.href.includes("ArtistComment") ||
  location.href.includes("ArtistLike")
) {
  location.href = "ArtistC?artistId=" + param.value;
}

if (
  location.href.includes("AlbumUpdate") ||
  location.href.includes("AlbumComment") ||
  location.href.includes("AlbumLike")
) {
  location.href = "AlbumC?albumId=" + param.value;
}
