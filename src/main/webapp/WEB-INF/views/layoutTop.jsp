<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-dark" id="topNav">
  <div class="container d-flex justify-content-end">
    <c:choose>
      <c:when test="${sessionScope.email == null}">
        <div class="row" style="">
          <div>
            <a
              class="mr-3"
              href="#"
              data-toggle="modal"
              data-target="#loginModal"
              style="font-size: 14px; color: black; font-weight: bold"
              >로그인</a
            >
          </div>
          <div>
            <a
              class="mr-3"
              href="${root}/user/signuppage"
              style="font-size: 14px; color: black; font-weight: bold"
              >회원가입</a
            >
          </div>
        </div>
      </c:when>
      <c:otherwise>
        <div class="row ml-3">
          <!--  style="display: none;"> -->
          <div>
            <a
              class="mr-3"
              href="#"
              data-toggle="modal"
              data-target="#logoutModal"
              style="font-size: 14px; color: black; font-weight: bold"
              >로그아웃</a
            >
          </div>
          <div>
            <a
              class="mr-3"
              href="${root}/user/mypage"
              style="font-size: 14px; color: black; font-weight: bold"
              >마이페이지</a
            >
          </div>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</nav>
<nav class="navbar navbar-expand-lg bg-dark" id="mainNav">
  <div class="container d-flex justify-content-between">
    <div>
      <a
        class="navbar-brand js-scroll-trigger"
        href="${root}"
        style="font-size: 25px"
        >Happy House</a
      >
    </div>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item">
          <a
            class="nav-link js-scroll-trigger"
            href="${root}/board/showArticle"
            style="font-size: 16px; font-weight: bold"
            >공지 사항</a
          >
        </li>
        <li class="nav-item">
          <a
            class="nav-link js-scroll-trigger"
            href="#"
            style="font-size: 16px; font-weight: bold"
            >오늘의 뉴스</a
          >
        </li>
        <c:choose>
          <c:when test="${sessionScope.email != null}">
            <li class="nav-item">
              <a
                class="nav-link js-scroll-trigger"
                href="${root}/user/interestsSetting"
                style="font-size: 16px; font-weight: bold"
                >관심 지역 주변 정보</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link js-scroll-trigger"
                href="${root}/user/interestsInfo"
                style="font-size: 16px; font-weight: bold"
                >관심 지역 환경 정보</a
              >
            </li>
          </c:when>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
