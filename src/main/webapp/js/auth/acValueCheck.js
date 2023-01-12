function call() {
  const name = document.querySelector("#name");
  const id = document.querySelector("#id");
  const pw = document.querySelector("#pw");
  const pwConfirm = document.querySelector("#pw-confirm");
  const img = document.querySelector("#file");

  
	if (isEmpty(id)) {
    alert("아이디를 입력해 주세요!");
    return false;
  }
	if (isEmpty(pw)) {
    alert("비밀번호를 입력해 주세요!");
    return false;
  }
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

function winopen(){
	//새창을 열어서 페이지를 오픈 후 -> 회원아이디정보를 가지고 중복체크
	//1. 아이디가 없으면 알림창과 진행x
	if(document.fr.id.value =="" || document.fr.id.value.length < 0){
		alert("아이디를 먼저 입력해주세요")
		document.fr.id.focus();
	}else{
		//2. 회원정보아이디를 가지고 있는 지 체크하려면 DB에 접근해야한다.
		//자바스크립트로 어떻게 DB에 접근할까? => 파라미터로 id값을 가져가서 jsp페이지에서 진행하면 된다.
		window.open("joinIdCheck.jsp?userid="+document.fr.id.value,"","width=500, height=300");
	}
	
}






