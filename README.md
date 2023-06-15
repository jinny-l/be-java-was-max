![](https://img.shields.io/badge/VERSION-5.0-green)
![](https://img.shields.io/badge/LAST_UPDATE-2023--06--15-blue)

# 🌎 자바 웹서버 미션
- 2023 코드스쿼드 마스터즈 BE max에서 진행한 자바로 웹서버를 구현하는 미션
- 미션 진행 기간(max 10주차 ~ 15주차): 2023-05-08 ~ 2023-06-15

## 🔖 버전 기록
| 버전  | 기능                                        |
|:----|:------------------------------------------|
| 5.0 | 로그인 기능                                    |
| 4.0 | POST 방식으로 회원 가입하는 기능, HTTP redirection 기능 |
| 3.0 | 다양한 컨텐츠 타입 지원, HTTP Response 응답 기능        |
| 2.0 | GET 방식으로 회원 가입하는 기능                       |
| 1.0 | 정적 파일 응답 및 logger를 통한 HTTP Request 출력 기능  |

## ✔️ 기능 요구사항
- (1.0) 정적 파일 응답 기능
- (1.0) HTTP Request 내용 출력 기능
- (2.0) GET 방식으로 회원 가입하는 기능
- (3.0) 다양한 컨텐츠 타입을 지원하는 기능(css, js, ico, png, jpg 등)
- (4.0) POST 방식으로 회원 가입하는 기능
- (4.0) HTTP redirection 기능
- (5.0) 로그인 기능

## ⌨️ 프로그래밍 요구사항
- 단순히 요구사항 구현이 목표가 아니라 프로젝트의 동작 원리에 대해 파악한다.
- 주어진 소스코드를 기반으로 기능요구사항을 만족하는 코드를 작성한다. 
- 유지보수에 좋은 구조에 대해 고민하고 코드를 개선해 본다. 
- 웹 리소스 파일은 제공된 파일을 수정해서 사용한다. (직접 작성해도 무방하다.)
- Junit을 활용한 단위 테스트를 적용해 본다.

---

## ✨ 기능 목록
- [X] (1.0) 정적 파일 응답 및 logger를 통한 HTTP Request 출력 기능
- [X] (2.0) GET 방식으로 회원 가입하는 기능
  - [X] 홈페이지에서 “회원가입” 메뉴 클릭 시, http://localhost:8080/user/form.html 으로 이동하는 기능
  - [X] URL에서 파라미터를 파싱하는 기능
  - [X] 회원 가입하는 기능
- [X] (3.0) 다양한 컨텐츠 타입을 지원하는 기능
  - [X] HttpResponse, HttpResponseHeader, StatusLine, ContentType 클래스 구현
- [X] (4.0) POST 방식으로 회원 가입하는 기능
  - [X] POST method 지원을 위한 RequestBody 구현
  - [X] URL 분기 처리를 위한 RequestMapping 어노테이션 기능
  - [X] URL에 따라 매핑되는 서블릿을 불러오는 기능
  - [X] 가입 완료 후 특정 페이지로 리다이렉트하는 기능
- [X] (5.0) 로그인 기능
  - [X] 로그인이 성공할 경우, HTTP 헤더의 쿠키 값을 SID=세션 ID 로 응답하는 기능
  - [X] 로그인이 성공하면 index.html로 이동하고, 실패하면 /user/login_failed.html로 이동하는 기능
