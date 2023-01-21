const birthValue = document.querySelector(".birth_value");
const birthInput = document.querySelector("input[name=birth]");
const textarea = document.querySelector("textarea");

if (birthInput) {
  const birth = birthValue.value.replace(".", "-").replace(".", "-");
  birthInput.value = birth;
}

const info = textarea.value.replace(/<br>/g, "\r\n");
textarea.value = info;
