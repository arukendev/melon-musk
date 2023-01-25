const search = location.search;
const start = search.lastIndexOf("=");
const end = search.length;
const searchIndex = parseInt(search.substring(start + 1, end));
const indexes = document.querySelectorAll(".indexes");

indexes.forEach((e) => {
  const value = parseInt(e.textContent);
  if (searchIndex === (value - 1) * 50 + 1) {
    e.style.color = "#e1b12c";
  }
});
