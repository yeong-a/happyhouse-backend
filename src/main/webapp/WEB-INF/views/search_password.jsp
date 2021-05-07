<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <%@ include file="./plugin.jsp" %>
    <title>비밀번호 찾기</title>
    <script type="text/javascript">
      $(function () {
        $("#reset_password").on("click", function () {
          alert("인증메일을 확인해주세요");
        });
      });
    </script>
  </head>
  <body>
    <%@ include file="./layoutTop.jsp" %>
    <%@ include file="./loginmodal.jsp" %>
    <div class="container">
      <div class="row prepend-top-default mt-5" style="padding: 10px 0">
        <div class="col-lg-4">
          <h4>비밀번호 찾기</h4>
          <p>회원가입 시 입력했던 정보들을 입력해주세요.</p>
        </div>
        <div class="col-lg-8">
          <form action="post">
            <div class="form-group">
              <label class="text-dark" for="inputPassword4">Name*</label>
              <input
                type="text"
                class="form-control col-lg-4"
                id="inputName4"
                placeholder="Username"
                required
              />
            </div>
            <div class="form-group">
              <label class="text-secondary" for="inputEmail4">Email*</label>
              <input
                type="email"
                class="form-control col-lg-6"
                id="inputEmail4"
                placeholder="Email"
                required
              />
            </div>
            <div class="form-group">
              <label class="text-secondary">전화번호</label>
              <input
                type="tel"
                class="form-control col-lg-6"
                id="inputphone"
                placeholder="Phone"
                required
              />
            </div>
            <button
              type="submit"
              class="btn btn-primary text-dark"
              id="reset_password"
            >
                        비밀번호 재설정
            </button>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
