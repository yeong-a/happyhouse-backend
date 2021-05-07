# FRAMEWORK 1조 박상준,장영아

## Servlet과 JSP로 구성되어 있는 기존 프로젝트를 Spring/Mybatis  기반의 프로젝트로 재구성한다.

## Spring Framework?

- EJB(Enterprise Java Bean)라는 무겁고 복잡한 플랫폼에서 벗어나 POJO(Plain Old Java Object)를 기반으로 하는 경량의 환경을 제공한다.
- 스프링 프레임워크가 처음 등장했을 떄는 단순히 애플리케이션 운용에 필요한 객체들을 생성하고, 객체들 사이에서 의존성을 주입해주는 단순한 컨테이너로서의 기능만 제공했지만 발전을 거듭한 현재의 스프링은 다양한 엔터프라이즈 시스템 개발에 필요한 모든 분야를 지원하는 하나의 플랫폼으로 자리잡았다.

## Spring Boot의 장점

**1) 라이브러리 관리 자동화**

- 기존 스프링 자바 프로젝트에서는 메이븐이나 그래들을 이용해서 라이브러리 의존성을 관리해왔다. 하지만 스프링 부트에서는 스타터(Starter)라는 것을 이용해 특정 기능에 필요한 라이브러리 의존성을 더욱 간단히 처리 할 수 있다.

**2) 설정의 자동화**

- 스프링 부트에서는 프로젝트에 추가된 라이브러리를 기반으로 실행에 필요한 환경을 자동으로 설정해준다.
- 개발에 필요한 라이브러리들을 추가하면 스픵 부트가 이 라이브러리들을 인지해서 관련된 스프링 설정을 자동으로 처리해주기 때문에 개발자들은 복잡한 설정을 하지 않고도 개발이 가능하다.

**3) 라이브러리 버전 자동 관리**

- 개발시 가장 신경쓰이는 부분이 라이브러리와 버젼 관리이다. 기존의 스프링은 스프링 라이브러리만 사용하여 개발할 수 없으며, 의존관계에 있는 서드파티 라이브러들도 사용한다. 스프링 부트를 사용하면 스프링 부트 버전에 해당하는 스프링 라이브러리뿐만 아니라 서드파티 라이브러리들도 호환되는 버전으로 다운로드해준다.
- 라이브러리 버전이 달라 정상적으로 동작하지 않는 상황을 겪을 필요가 없고, XML설정을 이용해서 라이브러리를 매번 설정하는 과정을 줄이고 개발에만 집중할 수 있는 환경을 제공한다.

**4) 테스트 환경과 내장 Tomcat**

- JUnit을 비롯한 테스트 관련 라이브러리들이 기본적으로 포함되어 있기 때문에 컨트롤러를 비롯한 다양한 계층의 클래스들에 대해서 테스트 케이스를 쉽게 작성할 수 있다.
- Tomcat 서버를 내장하고 있기 때문에 단지 main() 메소드를 가진 클래스를 실행하는 방식으로 서버를 구동하기 때문에 실행결과를 빠르게 확인할 수 있다.

**5) 독립적으로 실행 가능한 JAR**

- 애플리케이션을 개발하고 테스트까지 마쳤으면 애플리케이션을 실제 운영 서버에 배포하기 위해서 패키징을 해야하는데, 프로젝트가 일반 자바 프로젝트라면 간단하게 JAR파일로 패키징하면 되지만 웹 프로젝트라면 WAR 파일로 패키징 해야한다.
- 스프링 부트는 독립적으로 실행 가능한 애플리케이션을 빠르게 개발하는 것을 목표로 하기 때문에 웹 애플리케이션도 WAR가 아닌 JAR파일로 패키징 하여 사용할 수 있다.
- 

BoardController.java

```java

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("addArticle")
	private String addArticle(@RequestParam String title,@RequestParam String content)
			throws SQLException{
		boardService.register(title, content);

		return "/notice";
	}
	
	@GetMapping("deleteArticle")
	private String deleteArticle(@RequestParam String bno)
			throws SQLException, ServletException, IOException {
		boardService.delete(bno);
		return "/notice";
	}

	
	@GetMapping("updateArticle")
	private String updateArticle(@RequestParam String bno,@RequestParam String title,@RequestParam String content) throws SQLException {
	
		boardService.update(bno, title, content);
		return "/notice";
	}

	@RequestMapping("showArticle")
	private String showArticle(HttpServletRequest request) throws SQLException
		{
		request.setAttribute("boardList", boardService.getBoardList());
		
		return "/notice";
	}	
}
```

UserRestController 

```java
package com.ssafy.happyhouse.controller.rest;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	private UserService userService = new UserServiceImpl();

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	@PostMapping("/login")  
	private void login(@RequestParam String email,@RequestParam String pwd, HttpSession session,HttpServletResponse response) throws SQLException {
		System.out.println(email);
		System.out.println(pwd);
		String name = userService.login(email,pwd);
		
		if (name == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		session.setAttribute("email",email);
		session.setAttribute("name", name);
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
	
	@PostMapping("/updateInfo")  
	private boolean updateInfo(@ModelAttribute User user, HttpSession session,HttpServletResponse response) throws SQLException {
		
		String email = user.getEmail();

		String name = user.getName();
		String address = user.getAddress();
		String detailAddress = user.getDetailAddress();

		if (userService.update(email, name, address, detailAddress)){
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
	@PostMapping("/changepassword")  
	private boolean changePassword(@RequestParam(value="current_password") String currentPassword,@RequestParam String password,HttpSession session,HttpServletResponse response) throws SQLException {
		
		String email = (String)session.getAttribute("email");

		User user = userService.getUser(email);
		if (!user.getPwd().equals(currentPassword)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return true;
		}

		if (userService.changePassword(email, password)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
	@PostMapping("/signup")  
	private boolean signup(@ModelAttribute User user, HttpSession session,HttpServletResponse response) throws SQLException {

		if (userService.signup(user)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
	@PostMapping("/delete") 
	private boolean delete(HttpSession session,HttpServletResponse response) throws SQLException  {
		
		String email = (String) session.getAttribute("email");

		if (userService.delete(email)) {
			session.invalidate();
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
}
```

# MyBatis?

MyBatis 는 개발자가 지정한 SQL, 저장프로시저 그리고 몇가지 고급 매핑을 지원하는 퍼시스턴스 프레임워크이다. MyBatis 는 JDBC 코드와 수동으로 셋팅하는 파라미터와 결과 매핑을 제거한다. MyBatis 는 데이터베이스 레코드에 원시타입과 Map 인터페이스 그리고 자바 POJO 를 설정하고 매핑하기 위해 XML 과 애노테이션을 사용할 수 있다.

## **[Mybatis의 장점](https://tkjeon.tistory.com/entry/Mybatis%EC%9D%98-%EC%9E%A5%EC%A0%90)**

Mybatis는 객체지향 어플리케이션에서 관계형 데이터베이스를 쉽게 사용할 수 있도록 도와주는 데이터 맵핑 프레임워크이다. 이러한 Mybatis의 장점은 다음과 같다.

- SQL 및 프로시져구문의 독립
- 복잡한 JDBC코드를 걷어내며 깔끔한 소스코드를 유지할 수 있다.
- 수동적인 파라미터 설정과 쿼리 결과에 대한 맵핑 구문을 제거할 수 있다.

Mybatis는 별도의 XML 문서에 맵핑된 프로시져와 SQL 구문을 연동하여 데이터베이스와 연동할 수 있도록 도와주어 데이터베이스 개발에 집중할 수 있도록 돕는다. 결과적으로 복잡한 JDBC 연동 코드나 트랜잭션 코드를 간소화시킬 수 있도록 도와주며 이는 결과적으로 소스코드의 유지보수를 용이하게 돕는다. 같은 맥락이지만 ResultSet과 같이 결과값을 맵핑하는 객체 또한 자동화시켜주어 많은 라인의 소스코드를 줄일 수 있다.

그밖에도 데이터베이스 개발이 Java와 분리된다는 것은 프로젝트 협업시에도 많은 이점을 제공해 줄 수 있다

UserDAO.java

```java
package com.ssafy.happyhouse.model.dao;

@Mapper
@Repository
public interface UserDAO {
	// 회원정보 insert
	int insert(User user) throws SQLException;

	// 회원정보 select
	User select(String email) throws SQLException;

	// 회원정보 update
	int update(User user) throws SQLException;
	
	// 회원정보 delete
	int delete(User user) throws SQLException;
}
```

UserDAO.xml

```sql
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
<mapper namespace="com.ssafy.happyhouse.model.dao.UserDAO">

	<insert id="insert" parameterType="User">
		insert into 
		member(email, name, pwd, address, detailAddress) 
		values(#{email}, #{name}, #{pwd}, #{address}, #{detailAddress})
	</insert>

	<resultMap type="User" id="UserResultMap">
		<id column="email" property="email"/>
		<result column="name" property="name"/>
		<result column="pwd" property="pwd"/>
		<result column="address" property="address"/>
		<result column="detailAddress" property="detailAddress"/>
	</resultMap>
	
	<select id="select" parameterType="String" resultMap="UserResultMap">
		select * 
		from member 
		where email = #{email}
	</select>
	
	<update id="update" parameterType="User">
		update member 
		set name = #{name}, 
		address = #{address}, 
		detailAddress = #{detailAddress}, 
		pwd = #{pwd} 
		where email = #{email}
	</update>
	
	<delete id="delete" parameterType="User">
		delete from member 
		where email = #{email}
	</delete>
	
</mapper>
```