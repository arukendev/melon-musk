const searchBox = document.querySelector("#index_hCenter_search");
const searchOpen = document.querySelector(".index_nav_content button");
const searchClose = document.querySelector(".fa-times");

searchOpen.addEventListener("click", () => {
  searchBox.classList.remove("hidden");
});

searchClose.addEventListener("click", () => {
  searchBox.classList.add("hidden");
});
