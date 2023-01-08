const showSearch = document.querySelector(".index_nav_content button");

const toggleSearch = () => {
  searchBox.classList.toggle("hidden");
};

showSearch.addEventListener("click", toggleSearch);
