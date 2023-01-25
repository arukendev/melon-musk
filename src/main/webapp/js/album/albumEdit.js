const textarea = document.querySelector("textarea");

const info = textarea.value.replace(/<br>/g, "\r\n");
textarea.value = info;
