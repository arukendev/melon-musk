<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>sign up</title>
  </head>
  <body>
    <form
      action="AuthC"
      id="profile_box"
      name="regform"
      method="post"
      enctype="multipart/form-data"
      onsubmit="return call()"
    >
     
      <div class="profile_content" id="file-box">
        <label for="file" class="titles" >Profile Image</label>
        <label for="file"><input type="file" name="file" id="file" accept="image/*" /></label>
      </div>
     
      <div class="profile_content" id="name-box">
        <label for="name" class="titles">Name</label>
        <input type="text" name="name" id="name" />
      </div>
      <div class="profile_content" id="id-box">
        <label for="id" class="titles">ID</label>
       <label for="id">  <input 
         type="text"
         class="check_label"
         readonly
          name="id"
          id="id"
          placeholder="영문, 숫자 5자 이상"
        /> <input class="id_check_button" type="button" value="ID중복확인" onclick="idCheck()"> </label>
 
      </div>
      <div class="profile_content" id="pw-box">
        <label for="pw" class="titles">Password</label>
        <input
          type="password"
          name="pw"
          id="pw"
          placeholder="대문자, 소문자, 숫자 포함 3자 이상"
        />
      </div>
      <div class="profile_content" id="pw-confirm-box">
        <label for="pw-confirm" class="titles">Password Confirm</label>
        <input
          type="password"
          name="pw-confirm"
          id="pw-confirm"
          placeholder="비밀번호 확인"
        />
      </div>
     
      <div class="profile_content" id="interest-box">
        <label class="titles">Interest</label>
        <div id="check-box">
          <input id="가요" type="checkbox" name="interest" value="가요" />
          <label for="가요" class="contents">가요</label>
          <input id="클래식" type="checkbox" name="interest" value="클래식" />
          <label for="클래식" class="contents">클래식</label>
          <input id="락" type="checkbox" name="interest" value="락" />
          <label for="락" class="contents">락</label>
          <input id="힙합" type="checkbox" name="interest" value="힙합" />
          <label for="힙합" class="contents">힙합</label>
        </div>
      </div>
      <div class="profile_content" id="introduce-box">
        <label for="introduce" class="titles">Introduce</label>
        <textarea id="introduce" name="introduce" rows="5"></textarea>
      </div>
      <div class="profile_buttons" id="submit-box">
        <button class="profile_button">회원가입</button>
      </div>
    </form>
    <script src="js/auth/acValueCheck.js"></script>
    <script src="js/auth/acValidCheck.js"></script>
    <script
      src="https://kit.fontawesome.com/6478f529f2.js"
      crossorigin="anonymous"
    ></script>
  </body>
</html>