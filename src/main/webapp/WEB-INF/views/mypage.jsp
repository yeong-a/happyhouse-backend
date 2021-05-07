<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <%@ include file="./plugin.jsp" %>
    <title>내 정보 수정</title>
  </head>
  <body>
    <%@ include file="./layoutTop.jsp" %>
    <%@ include file="./loginmodal.jsp" %>
    <!-- 마이페이지 정보 수정 -->
    <div class="container">
      <div class="content-wrapper">
        <div class="container mt-10" style="padding: 10px 0">
          <div class="row prepend-top-default" style="padding-top: 15px">
            <div class="col-lg-4">
              <h4>회원 정보 수정</h4>
            </div>
            <div class="col-lg-8">
              <form id="update-info-form">
                <div class="form-group">
                  <label class="text-secondary" for="inputPassword">이름</label>
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="name"
                    placeholder="닉네임"
                  />
                </div>
                <div class="form-group">
                  <label class="text-secondary" for="inputEmail">이메일</label>
                  <input
                    type="email"
                    class="form-control"
                    name="email"
                    id="inputEmail4"
                    value="${sessionScope.email}"
                    readonly
                  />
                </div>
                <div class="form-group">
                  <label class="text-secondary" for="inputAddress"
                    >도로명 주소</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="address"
                    id="address"
                    placeholder="1234 00길"
                  />
                </div>
                <div class="form-group">
                  <label class="text-secondary" for="inputAddress2"
                    >상세 주소</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="detailAddress"
                    id="detailAddress"
                    placeholder="[상세주소]건물 , 동, 호수"
                  />
                </div>
                <div class="form-group">
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="gridCheck"
                    />
                    <label
                      class="text-secondary"
                      class="form-check-label"
                      for="gridCheck"
                    >
                                        확인했습니다.
                    </label>
                  </div>
                </div>
                <button type="submit" class="btn btn-primary">
                                내 정보 수정
                </button>
              </form>
            </div>
          </div>
          <hr />

          <!-- 비밀번호 변경하기 -->
          <div class="row prepend-top-default">
            <div class="col-lg-4">
              <h4 class="prepend-top">비밀번호 변경</h4>
            </div>
            <div class="col-lg-8">
              <form id="change-password-form" class="update_password">
                <div class="form-group">
                  <label class="label-bold">현재 비밀번호</label>
                  <input
                    class="form-control"
                    type="password"
                    name="current_password"
                    id="user[current_password]"
                    required
                  />
                  <p class="text-secondary">
                                     비밀번호를 변경하기 위해 현재 비밀번호를 입력해주세요.
                  </p>
                </div>
                <div class="form-group">
                  <label class="label-bold">새 비밀번호</label>
                  <input
                    class="form-control"
                    type="password"
                    name="password"
                    id="userpassword]"
                    required
                  />
                </div>
                <div class="form-group">
                  <label class="label-bold">비밀번호 확인</label>
                  <input
                    class="form-control"
                    type="password"
                    name="password_confirm"
                    id="user[confirm_password]"
                    required
                  />
                </div>
                <div class="prepend-top-default">
                  <input
                    type="submit"
                    class="btn btn-success"
                    value="비밀번호 바꾸기"
                  />
                  <a href="${root}/user/searchPassword" class="text-primary">비밀번호를 잊어버렸어요</a>
                </div>
              </form>
            </div>
          </div>
          <hr />

          <!-- 계정 삭제하기 -->
          <div class="row prepend-top-default">
            <div class="col-lg-4">
              <h4 class="text-danger">계정 삭제</h4>
            </div>
            <div class="col-lg-8">
              <p>
                            탈퇴하시면 그동안 이용했던 정보들이 모두 삭제되며 다시 복구할 수
                            없습니다.
              </p>
              <button class="btn btn-danger" id="delete-user-btn">
                             탈퇴하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script>
      const updateInfoForm = document.getElementById("update-info-form");
      updateInfoForm.onsubmit = function (e) {
        e.preventDefault();
        $.ajax({
          url: "${root}/user/updateInfo",
          method: "post",
          data: $(updateInfoForm).serialize(),
          success: function () {
            alert("내 정보가 수정되었습니다.");
            location.reload();
          },
          statusCode: {
            400: function () {
              alert("에러");
            },
          },
        });
      };

      const changePasswordForm = document.getElementById(
        "change-password-form"
      );
      changePasswordForm.onsubmit = function (e) {
        e.preventDefault();
        $.ajax({
          url: "${root}/user/changepassword",
          method: "post",
          data: $(changePasswordForm).serialize(),
          success: function () {
            alert("비밀번호가 변경되었습니다.");
            location.reload();
          },
          statusCode: {
            400: function () {
              alert("현재 비밀번호가 맞지 않습니다.");
            },
          },
        });
      };

      const deleteUserButton = document.getElementById("delete-user-btn");
      deleteUserButton.onclick = function () {
        $.ajax({
          url: "${root}/user/delete",
          method: "post",
          success: function () {
            alert("계정 삭제가 완료되었습니다.");
            location.href = "${root}";
          },
          statusCode: {
            400: function () {
              alert("계정 삭제에 실패했습니다.");
            },
          },
        });
      };
    </script>
  </body>
</html>
