const birthValue = document.querySelector(".birth_value");
const birthInput = document.querySelector("input[name=birth]");

const birth = birthValue.value.replace(".", "-").replace(".", "-");
birthInput.value = birth;
