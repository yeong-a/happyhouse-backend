<%@page import="java.util.ArrayList"%>
<%@page import="com.ssafy.happyhouse.model.dto.Board"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String root = request.getContextPath();
  List<Board> list = (ArrayList<Board>) request.getAttribute("boardList");
  if(list == null || list.size() == 0)
      response.sendRedirect(root + "/board/showArticle");
%>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="./plugin.jsp" %>
    <title>공지사항</title>
  </head>
  <body id="page-top">
    <%@ include file="./layoutTop.jsp" %>
    <%@ include file="./loginmodal.jsp" %>
    <div class="container mt-5">
      <h2 class="text-center mb-3">공지 사항</h2>
      <div class="d-flex justify-content-between align-items-center">
        <button
          type="button"
          class="btn btn-dark mb-2"
          data-toggle="modal"
          data-target="#myModal2"
        >
                    글쓰기
        </button>
        <form class="form-inline" action="#">
          <input
            class="form-control mr-sm-2"
            type="text"
            placeholder="Search"
          />
          <button class="btn btn-dark" type="submit">Search</button>
        </form>
      </div>
      <table class="table table-hover mb-3">
        <thead>
          <tr class="text-center">
            <th style="width: 15%">번호</th>
            <th style="width: 55%">제목</th>
            <th style="width: 15%">조회수</th>
            <th style="width: 15%">조회</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="board" items="${boardList }">
            <tr class="text-center">
              <td>${board.bno }</td>
              <td class="bd-bno" style="display: none">${board.bno }</td>
              <td class="text-left pl-4 bd-title">${board.title }</td>
              <td class="bd-content" style="display: none">
                ${board.content}
              </td>
              <td>${board.hit }</td>
              <td>
                <button
                  type="button"
                  class="btn btn-warning btn-sm detailBtn"
                  data-toggle="modal"
                  data-target="#myModal"
                >
                                    조회
                </button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <script>
      $(function () {
        $(".detailBtn").on("click", function () {
          let bno = $(this).parent().parent().children(".bd-bno").text();
          let title = $(this)
            .parent()
            .parent()
            .children(".bd-title")
            .text();
          let content = $(this)
            .parent()
            .parent()
            .children(".bd-content")
            .text();
          $(".hidden").val(bno);
          $(".up-title").val(title);
          $(".up-content").val(content);
        });
      });
    </script>

    <!-- 글 조회 modal -->
    <div class="modal fade" id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">공지 사항</h4>
            <button type="button" class="close" data-dismiss="modal">
              ×
            </button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <form method="get">
              <div class="form-group">
                <label for="title">제목</label>
                <input
                  type="text"
                  class="form-control up-title"
                  id="title"
                  name="title"
                />
              </div>
              <div class="form-group">
                <label for="content">내용</label>
                <input
                  type="text"
                  class="form-control up-content"
                  id="content"
                  name="content"
                />
              </div>
              <input type="hidden" class="hidden" name="bno" />
              <button
                type="submit"
                class="btn btn-dark"
                formaction="<%=root%>/board/updateArticle"
              >
                                수정
              </button>
              <button
                type="submit"
                class="btn btn-dark"
                formaction="<%=root%>/board/deleteArticle"
              >
                                삭제
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- 글쓰기 modal -->
    <div class="modal fade" id="myModal2">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">글쓰기</h4>
            <button type="button" class="close" data-dismiss="modal">
              ×
            </button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <form action="<%=root %>/board/addArticle" method="get">
              <div class="form-group">
                <label for="title">제목</label>
                <input
                  type="text"
                  class="form-control"
                  id="title"
                  name="title"
                />
              </div>
              <div class="form-group">
                <label for="content">내용</label>
                <input
                  type="text"
                  class="form-control"
                  id="content"
                  name="content"
                />
              </div>
              <button type="submit" class="btn btn-dark">작성</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
