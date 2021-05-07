<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- modal login -->
<div class="modal fade" id="loginModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">LogIn</h4>
        <button type="button" class="close" data-dismiss="modal">
          &times;
        </button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form method="post" action="${root}/user/login" class="was-validated" id="login-form">
          <div class="form-group">
            <label>이메일:</label>
            <input
              type="text"
              class="form-control"
              id="email"
              name="email"
              placeholder="이메일을 입력해주세요."
              required
            />
          </div>
          <div class="form-group">
            <label>비밀번호:</label>
            <input
              type="password"
              class="form-control"
              id="pwd"
              name="pwd"
              placeholder="비밀번호를 입력해주세요"
              required
            />
            <a href="${root}/user/searchPassword" class="text-secondary"
              >*비밀번호가 기억이 안 나요</a
            >
          </div>
          <div
            class="modal-footer d-grid gap-2 d-md-flex justify-content-md-end"
          >
            <button type="submit" class="btn btn-success" id="btn_login">
                            로그인
            </button>
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
                            닫기
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- modal logout -->
<div class="modal fade" id="logoutModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">LogOut</h4>
        <button type="button" class="close" data-dismiss="modal">
          &times;
        </button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">로그아웃 하시겠습니까?</div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <a
          class="btn btn-success"
          id="btn_logout"
          href="${root}/user/logout"
          >로그아웃</a
        >
        <button
          type="button"
          class="btn btn-secondary"
          data-dismiss="modal"
        >
                    취소
        </button>
      </div>
    </div>
  </div>
</div>
<script>
  const loginForm = document.getElementById("login-form");
  loginForm.onsubmit = function (e) {
    e.preventDefault();
    $.ajax({
      url: "${root}/user/login",
      method: "post",
      data: $(loginForm).serialize(),
      success: function () {
        alert("로그인 성공");
        location.reload();
      },
      statusCode: {
        400: function () {
          alert("아이디 또는 비밀번호가 잘못됐습니다.");
        },
      },
    });
  };
</script>
