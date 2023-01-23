const boxes = document.querySelectorAll(".main_con");

const observer = new IntersectionObserver((e) => {
  e.forEach((box) => {
    if (box.isIntersecting) {
      box.target.style.transform = "translateY(-50px)";
      box.target.style.opacity = 1;
    } else {
      box.target.style.transform = "translateY(50px)";
      box.target.style.opacity = 0;
    }
  });
});
observer.observe(boxes[0]);
observer.observe(boxes[1]);
observer.observe(boxes[2]);
