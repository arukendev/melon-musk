const textarea = document.querySelector("textarea");
const linkInput = document.querySelector("input[name=link]");

const lyrics = textarea.value.replace(/<br>/g, "\r\n");
textarea.value = lyrics;

const link = "https://youtu.be/" + linkInput.value;
linkInput.value = link;
