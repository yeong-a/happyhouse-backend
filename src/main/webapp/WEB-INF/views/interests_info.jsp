<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="./plugin.jsp" %>
    <title>관심지역 대기 정보</title>
  </head>
  <body>
    <%@ include file="./layoutTop.jsp" %>    
    <%@ include file="./loginmodal.jsp" %>
    <div
      class="pt-2 bg-dark d-flex justify-content-center"
      style="padding: 10px 0"
    >
      <form class="form-inline">
        <div class="form-group mr-2">
          <select
            class="form-control btn-secondary"
            id="sel1"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option>서울시</option>
            <option>경기도</option>
            <option>인천광역시</option>
          </select>
        </div>
        <div class="form-group mr-2">
          <select
            class="form-control btn-secondary"
            id="sel2"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option selected>구로구</option>
            <option>종로구</option>
            <option>중구</option>
            <option>성북구</option>
            <option>마포구</option>
          </select>
        </div>
        <div class="form-group mr-2">
          <select
            class="form-control btn-secondary"
            id="sel3"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option>궁동</option>
            <option>북창동</option>
            <option>창성동</option>
          </select>
        </div>
        <button type="submit" class="btn btn-secondary">추가</button>
      </form>
    </div>
    <div class="row">
      <div class="col-sm-2">
        <div class="m-4">
          <hr />
          <h4>관심 지역 목록</h4>
          <hr />
          <ul class="list-group list-group-flush mt-md-2">
            <li class="list-group-item">
              <div class="row">
                <h5>궁동</h5>
                /
                <h5>구로구</h5>
              </div>
              <div class="row">
                <a class="mr-3 text-dark" href="#" id="choose_main"
                  >💡메인으로 변경</a
                >
                <a class="text-dark" href="#" id="delete_interest"
                  >&times;삭제</a
                >
              </div>
            </li>
            <li class="list-group-item mt-2 md-2">
              <div class="row">
                <h5>북창동</h5>
                /
                <h5>중구</h5>
              </div>
              <div class="row">
                <a class="mr-3 text-dark" href="#" id="choose_main"
                  >💡메인으로 변경</a
                >
                <a class="text-dark" href="#" id="delete_interest"
                  >&times;삭제</a
                >
              </div>
            </li>
            <li class="list-group-item mt-2 md-2">
              <div class="row">
                <h5>창성동</h5>
                /
                <h5>종로구</h5>
              </div>
              <div class="row">
                <a class="mr-3 text-dark" href="#" id="choose_main"
                  >💡메인으로 변경</a
                >
                <a class="text-dark" href="#" id="delete_interest"
                  >&times;삭제</a
                >
              </div>
            </li>
            <li class="list-group-item mt-2 md-2">
              <div class="row">
                <h5>청운동</h5>
                /
                <h5>종로구</h5>
              </div>
              <div class="row">
                <a class="mr-3 text-dark" href="#" id="choose_main"
                  >💡메인으로 변경</a
                >
                <a class="text-dark" href="#" id="delete_interest"
                  >&times;삭제</a
                >
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="col-sm-10">
        <img
          src="${root}/assets/img/search/air.jpg"
          alt="구로구 대기질 정보"
          style="width: 100%; height: 850px"
        />
      </div>
    </div>
  </body>
</html>
