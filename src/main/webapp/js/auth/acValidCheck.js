// js 라이브러리 완성

// 일반적으로 유효성 검사하는 것들
// 함수형태로 정리해서
// 나중에 필요할때 쓸라고(쓰기 편하게)
// .jar 쓰던것 처럼 (lib)

// 문제가 있으면 true, 없으면 false

// <input> 을 넣으면..
// 거기에 글자가 없으면 true, 있으면 false
function isEmpty(input) {
  return !input.value;
}

// <input>과, 글자 수를 넣으면
// 그 글자수보다 적으면 true, 아니면 false
function lessThan(input, length) {
  return input.value.length < length;
}

// <input> 을 넣어서
// 값이 숫자가 아니면 true, 숫자면 false

// input : 123
// input : asd

function isNotNumber(input) {
  return isNaN(input.value);
}

// test :

// input x 2 넣으면
// 내용이 다르면 true, 아니면 false

function notEquals(input1, input2) {
  return input1.value !== input2.value;
}

// <input> 을 넣으면
// 한글/특수문자 들어있으면 true, 아니면 false
// ex) id

function containKR(input) {
  let ok = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
  for (let i = 0; i < input.value.length; i++) {
    if (ok.indexOf(input.value[i]) === -1) {
      return true;
    }
  }
}

// <input>, 문자열 세트
// 그 문자열 세트가 포함 안되어있으면 true
// 들어있으면 false
// 비밀번호 조합 대,소,숫
function notContains(input) {
  // input : 1qwerASD
  // input : ASD

  // set : 123456789
  // set : QWERTYUIOPASDFGHKLZXCVBNM
  const reg = /(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)/;
  if (reg.test(input.value) === false) {
    return true;
  }
  return false;
}

// <input>, 확장자를 넣으면
// 그 파일이 아니면 true, 맞으면 false

// jpg, jpeg, png, gif

// a.jpg

function isNotType(input, type) {
  type = "." + type; // .gif
  return input.value, indexOf(type) == -1;
}
