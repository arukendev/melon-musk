function call() {
  const name = document.querySelector("#name");
  const id = document.querySelector("#id");
  const pw = document.querySelector("#pw");
  const pwConfirm = document.querySelector("#pw-confirm");
  const img = document.querySelector("#file");
  
	if (isEmpty(img)) {
    alert("사진을 입력해 주세요!");
    return false;
  }
  if (isEmpty(name)) {
    alert("이름을 입력해 주세요!");
    name.focus();
    return false;
  }

  if (containKR(id) || lessThan(id, 5)) {
    alert("ID는5자 이상 입력해주세요!");
    id.focus();
    return false;
  }

  if (
    lessThan(pw, 3) ||
    notContains(pw)
  ) {
    alert("PW는 대문자 소문자 숫자 섞어주세요!");
    pw.focus();
    pw.value = "";
    pwConfirm.value = "";
    return false;
  }

  if (notEquals(pw, pwConfirm)) {
    alert("비밀번호가 다릅니다!");
    pwConfirm.focus();
    pwConfirm.value = "";
    return false;
  }
}
