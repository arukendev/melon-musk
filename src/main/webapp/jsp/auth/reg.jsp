<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>sign up</title>
  </head>
  <body>
  	<div id="regWrapper">
    <form
      action="AuthC"
      id="reg-form"
      name="regform"
      method="post"
      enctype="multipart/form-data"
      onsubmit="return call()"
    >
     
      <div class="items" id="file-box">
        <label for="file" class="titles">Profile Image</label>
        <label for="file"><input type="file" name="file" id="file" accept="image/*" /></label>
      </div>
     
      <div class="items" id="name-box">
        <label for="name" class="titles">Name</label>
        <input type="text" name="name" id="name" />
      </div>
      <div class="items" id="id-box">
        <label for="id" class="titles">ID</label>
       <label for="id">  <input 
         type="text"
         readonly
          name="id"
          id="id"
          placeholder="영문, 숫자 5자 이상"
        /> <input type="button" value="ID중복확인" onclick="idCheck()"> </label>
 
      </div>
      <div class="items" id="pw-box">
        <label for="pw" class="titles">Password</label>
        <input
          type="password"
          name="pw"
          id="pw"
          placeholder="대문자, 소문자, 숫자 포함 3자 이상"
        />
      </div>
      <div class="items" id="pw-confirm-box">
        <label for="pw-confirm" class="titles">Password Confirm</label>
        <input
          type="password"
          name="pw-confirm"
          id="pw-confirm"
          placeholder="비밀번호 확인"
        />
      </div>
     
      <div class="items" id="interest-box">
        <label class="titles">Interest</label>
        <div id="check-box">
          <input id="k-pop" type="checkbox" name="interest" value="k-pop" />
          <label for="k-pop" class="contents">k-pop</label>
          <input id="j-pop" type="checkbox" name="interest" value="j-pop" />
          <label for="j-pop" class="contents">j-pop</label>
          <input id="메탈" type="checkbox" name="interest" value="메탈" />
          <label for="메탈" class="contents">메탈</label>
          <input id="힙합" type="checkbox" name="interest" value="힙합" />
          <label for="힙합" class="contents">힙합</label>
        </div>
      </div>
      <div class="items" id="introduce-box">
        <label for="introduce" class="titles">Introduce</label>
        <textarea id="introduce" name="introduce" rows="5"></textarea>
      </div>
      <div class="items" id="submit-box">
        <input type="submit" value="Sign Up" />
      </div>
    </form>
  	</div>
    <script src="js/auth/acValueCheck.js"></script>
    <script src="js/auth/acValidCheck.js"></script>
    <script
      src="https://kit.fontawesome.com/6478f529f2.js"
      crossorigin="anonymous"
    ></script>
  </body>
</html>