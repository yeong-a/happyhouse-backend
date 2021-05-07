<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="./plugin.jsp" %>
    <title>아파트 실거래가 확인</title>
  </head>
  <body>
    <%@ include file="./layoutTop.jsp" %>
    <%@ include file="./loginmodal.jsp" %>
    <div class="pt-2 bg-dark">
      <form>
        <div class="container d-flex pb-2" style="width: 100%; height: 45px">
          <select
            class="form-control"
            id="sel1"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option>도 / 광역시</option>
          </select>
          <select
            class="form-control"
            id="sel2"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option>시 / 군 / 구</option>
          </select>
          <select
            class="form-control"
            id="sel3"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option>동</option>
          </select>
          <select
            class="form-control"
            id="sel4"
            style="height: 100%; font-size: 16px; font-weight: bold"
          >
            <option>아파트</option>
            <option>다세대</option>
          </select>
          <button type="submit" class="btn btn-warning">Search</button>
        </div>
      </form>
    </div>
    <div class="row">
      <div class="col-sm-2">
        <div class="m-4">
          <h4>거래 정보</h4>
          <ul class="list-group list-group-flush">
            <c:if test="${not empty houseList }">
              <c:forEach var="house" items="${houseList }">
                <li class="list-group-item apart">
                  <div class="name">아파트명 : ${house.aptName }</div>
                  <div>거래금액 : ${house.dealAmount }</div>
                  <div>면적 : ${house.area }</div>
                  <div>
                    ${house.dealYear }-${house.dealMonth }-${house.dealDay }
                  </div>
                </li>
              </c:forEach>
            </c:if>
          </ul>
        </div>
      </div>
      <div class="col-sm-10">
        <div id="map" style="width: 100%; height: 800px"></div>
        <script
          async
          src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZHFPGHr_hYOEE4zSgb5S1LOSwS21Hux8&callback=initMap"
        ></script>
        
        <script type="text/javascript">
          let map;
          function initMap() {
          	map = new google.maps.Map(document.getElementById('map'), {
          		center: {lat: ${houseList[0].lat}, lng: ${houseList[0].lng}}, zoom: 15
          	});
          	<c:forEach var="house" items="${houseList }">
          		new google.maps.Marker({
          			position: {lat : ${house.lat}, lng : ${house.lng}}, map: map
          		});
          	</c:forEach>
          }
        </script>
      </div>    
    </div>
    <script>
      $(function () {
        $(".apart").click(function () {
          let name = $(this).children(".name").text();
          location.href =
            "${root}/house/detail2?aptName=" + name.substring(7);
        });
      });
    </script>
  </body>
</html>
