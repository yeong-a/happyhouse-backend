<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="./plugin.jsp" %>
    <title>회원가입</title>
  </head>
  <body>
    <%@ include file="./layoutTop.jsp" %>
    <%@ include file="./loginmodal.jsp" %>
    <!-- 회원가입 페이지 -->
    <section class="page-section bg-dark">
      <div class="container">
        <form id="signup-form">
          <div class="form-row">
            <div class="form-group col-md-6">
              <label class="text-light" for="inputPassword4">Name*</label>
              <input
                type="text"
                name="name"
                class="form-control"
                id="inputName4"
                placeholder="Username"
                required
              />
            </div>
            <div class="form-group col-md-6">
              <label class="text-light" for="inputEmail4">Email*</label>
              <input
                type="email"
                name="email"
                class="form-control"
                id="inputEmail4"
                placeholder="Email"
                required
              />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6">
              <label class="text-light" for="inputPassword4">비밀번호*</label>
              <input
                type="password"
                name="pwd"
                class="form-control"
                id="inputPassword4"
                placeholder="비밀번호"
                required
              />
            </div>
            <div class="form-group col-md-6">
              <label class="text-light" for="inputEmail4">비밀번호 확인*</label>
              <input
                type="password"
                name="pwd2"
                class="form-control"
                id="inputPasswordCheck"
                placeholder="비밀번호 확인"
                required
              />
            </div>
          </div>
          <div class="form-group">
            <label class="text-secondary" for="inputAddress">도로명 주소</label>
            <input
              type="text"
              name="address"
              class="form-control"
              id="inputAddress"
              placeholder="1234 00길"
            />
          </div>
          <div class="form-group">
            <label class="text-secondary" for="inputAddress2">상세 주소</label>
            <input
              type="text"
              name="detailAddress"
              class="form-control"
              id="inputAddress2"
              placeholder="[상세주소]건물 , 동, 호수"
            />
          </div>
          <div class="form-group">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="gridCheck" />
              <label class="text-light" class="form-check-label" for="gridCheck"
                >확인했습니다.</label
              >
            </div>
          </div>
          <button type="submit" class="btn btn-primary">Sign up</button>
        </form>
      </div>
    </section>
    <script>
      const signupForm = document.getElementById("signup-form");
      signupForm.onsubmit = function (e) {
        e.preventDefault();
        $.ajax({
          url: "${root}/user/signup",
          method: "post",
          data: $(signupForm).serialize(),
          success: function () {
            alert("회원가입 성공");
            location.href = "${root}";
          },
          statusCode: {
            500: function () {
              alert("중복된 이메일입니다.");
            },
          },
        });
      };
    </script>
  </body>
</html>
