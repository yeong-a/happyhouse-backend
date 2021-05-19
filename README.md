# ✨ Vue관통 프로젝트7 - 1조 박상준,장영아

### Vue를 이용해 QnA 게시판을 만들어 기존의 Spring project와 연동시킨다.

## ✅ Vue

- -뷰는 MVVM 패턴을 기반으로한 개발 언어로 백엔드와 프론트엔드를 분리하여 개발함으로써 양측의 효율성을 극대화할 수 있는 언어입니다. 또한 뷰- 뷰모델 - 모델로 구조화하여 개발하는 방식과 컴포넌트 단위를 통해 기능 분리가 쉽기 때문에 유지보수에 용이합니다. 뷰의 다른 특징으로는 다양한 프레임워크들의 장점을 가져왔다는 사실입니다. Augular의 양방향 데이터 바인딩을 통해 출력값과 모델값이 동기화되며 React의 가상돔을 이용, 필요한 부분을 빠르게 렌더링할 수 있습니다. 또한 , 단방향 데이터의 흐름을 통해 모델 컴포넌트에서 UI컴포넌트로 데이터가 흘러 이해와 관리가 쉽습니다.


### QnAController.java

```java
public class QnARestController {

	private static final Logger logger = LoggerFactory.getLogger(QnARestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnABoardService qnaBoardService;

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnA>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<QnA>>(qnaBoardService.retrieveBoard(), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/{no}")
	public ResponseEntity<QnA> detailBoard(@PathVariable int no) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<QnA>(qnaBoardService.detailBoard(no), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody QnA qna) {
		logger.debug("writeBoard - 호출");
		if (qnaBoardService.writeBoard(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{no}")
	public ResponseEntity<String> updateBoard(@RequestBody QnA qna) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + qna);

		if (qnaBoardService.updateBoard(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		logger.debug("deleteBoard - 호출");
		if (qnaBoardService.deleteBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 답글 정보를 반환한다.", response = Board.class)
	@GetMapping("/answer/{no}")
	public ResponseEntity<List<Answer>> selectAnswer(@PathVariable int no) {
		return new ResponseEntity<List<Answer>>(qnaBoardService.selectAnswer(no), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 답글 정보를 입력한다.", response = Board.class)
	@PostMapping("/answer/{no}")
	public ResponseEntity<String> insertAnswer(@RequestBody Answer answer, @PathVariable int no) {
		answer.setNo(no);
		if (qnaBoardService.insertAnswer(answer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
```





### datail.vue

```java
<script>
import moment from "moment";
import http from "@/util/http-common";

export default {
  name: "detail",
  props: {
    no: { type: Number },
    writer: { type: String },
    title: { type: String },
    content: { type: String },
    regtime: { type: String },
  },
  methods: {
    moveList() {
      this.$router.push("/");
    },
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
    },
    checkHandler() {
      let err = true;
      let msg = "";
      !this.answer_detail &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.answer_detail.focus());

      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      http
        .post("/happyhouse/qnaboard/answer/"+this.$route.params.no, {
          no : this.no,
          writer: this.$session.get('user_id'),
          content: this.answer_detail,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    }
  },
  data() {
    return {
       items: [],
       answer_detail : ""
    }
  },
  created() {
    http
      .get("/happyhouse/qnaboard/answer/"+this.$route.params.no)
      .then(({ data }) => {
        this.items = data;
      })
      .catch((exp) => {
        console.log(exp);
        alert("에러가 발생했습니다.");
      });
  },
};
</script>
```

