const textarea = document.querySelector("textarea");

const lyrics = textarea.value.split("<br>");

textarea.value = lyrics.join("\n");
