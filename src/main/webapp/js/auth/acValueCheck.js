function call() {
  const name = document.querySelector("#name");
  const id = document.querySelector("#id");
  const pw = document.querySelector("#pw");
  const pwConfirm = document.querySelector("#pw-confirm");

  if (isEmpty(name)) {
    alert("이름을 입력해 주세요!");
    name.focus();
    return false;
  }

  if (containKR(id) || lessThan(id, 5)) {
    alert("ID 오류");
    id.focus();
    return false;
  }

  if (
    lessThan(pw, 3) ||
    notContains(pw)
  ) {
    alert("PW 오류");
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
