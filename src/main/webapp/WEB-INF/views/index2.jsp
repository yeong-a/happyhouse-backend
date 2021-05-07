<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="font/fontawesome-free-5.15.2-web/css/all.css" rel="stylesheet">
<style>

.banner-bg {
	min-height: 250px;
	padding-top: 60px;
	padding-bottom: 20px;
}

.dark-bg {
	background-color: #373737;
	color: #cdcdcd;
}

.section {
	padding: 20px 0px;
}

.form-group {
	margin-right: 10px;
}

.separator-2 {
	width: 100%;
	margin-bottom: 15px;
	position: relative;
	height: 1px;
}

footer {
	margin-top: 40px;
	padding: 30px 0px;
	background-color: #fafafa;
}

.footer-content{
	padding: 20px 0px;
}

.modal-content{
	padding: 20px;
}

</style>

<title>Happy House</title>
</head>
<body>

<div class="header-container">
	<div class="header-top">
		<div class="container">
			<div class="row">
				<div class="col-3 col-sm-6 col-lg-9">
					<div class="header-top-first clearfix"></div>
				</div>
				<div class="col-9 col-sm-6 col-lg-3">
					<div id="header-top-second" class="clearfix">
						<div class="header-top-dropdown text-right">
							<div class="header-top-logout" style="display: none;">
								<div class="btn-group">
									<a href="./signUp.html" class="btn btn-default btn-sm">Sign Up</a>
								</div>
								<div class="btn-group">
									<a class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">Login</a>
								</div>
							</div>
							<div class="header-top-login">
								<div class="btn-group">
									<a href="${root}/user/logout.do" class="btn btn-default btn-sm">Logout</a>
								</div>
								<div class="btn-group">
									<a href="${root}/user/mypage.do" class="btn btn-default btn-sm">회원정보</a>
								</div>
							</div>
						</div>
						<!--  header top dropdowns end -->
					</div>
					<!-- header-top-second end -->
				</div>
			</div>
		</div>
	</div>
	<header>
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	    <div class="container">
	      <a class="navbar-brand" href="index.html">행복한 우리 집</a>
	      
	      
	      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	      
	      <div class="collapse navbar-collapse" id="navbarResponsive">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item">
	            <a class="nav-link" href="./about.html">About</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="./notice.html">공지사항</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="./apartment.html">주변 탐방</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="./setLoc.html">관심 지역 설정</a>
	          </li>
	          <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
		        	 관심 지역 둘러보기
		      </a>
		      <div class="dropdown-menu">
		        <a class="dropdown-item" href="./lookLoc.html">주변 상권 정보</a>
		        <a class="dropdown-item" href="./locEnvInfo.html">주변 환경 정보</a>
		      </div>
		      </li>
	          <!-- <li class="nav-item">
	            <a class="nav-link" href="#"><i class="fas fa-search"></i></a>
	          </li> -->
	          <li class="input-group" style="width: auto;">
	          	<div>
				  <input type="text" id="search" class="form-control" placeholder="search.."/>
				</div>
				<button type="button" class="btn btn-secondary">
				  <i class="fas fa-search"></i>
				</button>
		  	  </li>
	        </ul>
	      </div>
	      
	      <!-- <div class="input-group">
				  <div class="form-outline">
				    <input type="search" id="form1" class="form-control" placeholder="search.."/>
				  </div>
				  <button type="button" class="btn btn-primary">
				    <i class="fas fa-search"></i>
				  </button>
		  </div> -->
	    </div>
	  </nav>
	</header>
</div>

<div class="banner banner-bg" style="background-image:url('images/mainbg1.jpg'); background-position: 50% 42%;">
  <div class="container">
    <div class="row justify-content-lg-center">
      <div class="col-lg-8 text-center pv-20">
        <h2 class="title text-white"><strong>Happy House</strong></h2>
        <p class="text-center text-white">행복한 우리 집</p>
      </div>
    </div>
  </div>
</div>

<div class="dark-bg section">
	<div class="container-fluid">
		<div class="sorting-filters text-center mb-20 d-flex justify-content-center">
			<form class="form-inline">
				<div class="form-group">
					<select class="form-control" name="city" id="city">
                	  <option value="all">도/광역시</option>
	                  <option value="서울특별시">서울시</option>
	                  <option value="경기도">경기도</option>
	                  <option value="인천광역시">인천광역시</option>
	                  <option value="부산광역시">부산광역시</option>
	                  <option value="대전광역시">대전광역시</option>
	                  <option value="대구광역시">대구광역시</option>
	                  <option value="울산광역시">울산광역시</option>
	                  <option value="세종특별자치시">세종시</option>
	                  <option value="광주광역시">광주광역시</option>
	                  <option value="강원도">강원도</option>
	                  <option value="충청북도">충청북도</option>
	                  <option value="경상북도">경상북도</option>
	                  <option value="경상남도">경상남도</option>
	                  <option value="전라북도">전라북도</option>
	                  <option value="전라남도">전라남도</option>
	                  <option value="제주특별자치도">제주도</option>
                	</select>
				</div>
				<div class="form-group">
					<select class="form-control" name="gu" id="gu">
                	  <option value="all">시/구/군</option>
                	</select>
				</div>
				<div class="form-group">
					<select class="form-control" name="dong" id="dong">
                	  <option value="all">동</option>
                	</select>
				</div>
			</form>
		</div>
	</div>
</div>

<section class="main-content mt-3">
	<div class="container">
		<div class="row">
			<div class="main col-12">
				<div class="row grid-space-10">
					<div class="col-12 justify-content-center" id="map" style="width: 800px; height: 400px; position: relative; overflow: hidden;">
						<img src="https://via.placeholder.com/800x400" style="width:100%; height:100%;">
					</div>
				</div>
				
				<div class="separator-2"></div>
				
				<div class="row gird-space-10">
					<div class="col-md-4">
						<!-- 광고 -->
						<img alt="광고" src="${root}/images/mainbg1.jpg" style="width: 100%; height: 100%;">
					</div>
					<div class="col-md-4">
						<h4>주택 관련 기사</h4>
						<hr>
						<div class="separator-2"></div>
						<ul class="nav flex-column list-style-icons">
							<li class="nav-item">
								<a class="nav-link" href="#">기사 1</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">기사 2</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">기사 3</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">기사 4</a>
							</li>
						</ul>
						<div class="separator-2"></div>
					</div>
					<div class="col-md-4">
						<h4>오늘의 뉴스</h4>
						<hr>
						<div class="separator-2"></div>
						<ul class="nav flex-column list-style-icons">
							<li class="nav-item">
								<a class="nav-link" href="#">뉴스 1</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">뉴스 2</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">뉴스 3</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">뉴스 4</a>
							</li>
						</ul>
						<div class="separator-2"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<footer>
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div class="footer-content">
					<h3>Find Us</h3>
					<hr>
					<div class="separaotr-2"></div>
					<ul class="list-icons">
                      <li><i class="fa fa-map-marker pr-2 text-default"></i>(SSAFY) 5기 서울 6반 9조</li>
                      <li><i class="fa fa-phone pr-2 text-default"></i> 777-7777</li>
                      <li><i class="fa fa-envelope-o pr-2"></i>ssafy5_6@ssafy.com</li>
                    </ul>
				</div>
			</div>
		</div>
	</div>
</footer>

<!-- The Modal -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog">
    	<div class="modal-content">
        	<!-- Modal Header -->
       		<form action="/action_page.php">
  				<div class="form-group">
    				<label for="test">ID:</label>
   	 				<input type="text" class="form-control" placeholder="User ID" id="userId">
  				</div>
  				<div class="form-group">
    				<label for="pass">Password:</label>
    				<input type="password" class="form-control" placeholder="Enter password" id="password">
  				</div>
  				<div class="form-group form-check">
      				<input class="form-check-input" type="checkbox">
      				<label class="form-check-label">Remember me</label>
  				</div>
  				<button type="submit" class="btn btn-primary">로그인</button>
  				<button onclick="location.href='./findPassword.html'" type="button" class="btn btn-primary">비밀번호찾기</button>
			</form>
      	</div>
    </div>
</div>

</body>
</html>