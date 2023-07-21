# 8️⃣ 8lowFollowMe_Project

##### SNS 사이트 만들기

****

## 개발 기간
2023.07.17. ~ 2023.07.24.

## S.A. 링크 (https://www.notion.so/8-8-be0865ec00a24d918fdd86ff7578cba8)

****

## 기술 스택

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"><img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<br>

<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white">

****
<br/>

## 기능
<details>
<summary>상세보기</summary>

### 구현해야 할 필수 기능
  <details>
  <summary>더보기</summary>

#### 사용자 인증 기능
    * 회원가입 기능  
    * 로그인 및 로그아웃 기능

#### 프로필 관리
    * 프로필 수정 기능

#### 게시물 CRUD 기능
    * 게시물 작성, 조회, 수정, 삭제 기능

#### 게시물 CRUD 기능
    * 게시물 작성, 조회, 수정, 삭제 기능 

  </details>




### 추가 구현 기능
  <details>
  <summary>더보기</summary>

#### 소셜 로그인 기능 구현
    * 카카오 로그인 기능
    * 네이버 로그인 기능(구현 미완료)

#### 백오피스 만들어보기 - 관리자 페이지 구성
    * 회원 관리 기능
    * 게시글 관리 기능

#### 프론트엔드 만들어보기
    * 백엔드에서 제공하는 API를 통해 서버와 통신하는 프론트엔드를 구현합니다.

#### 좋아요 기능
    * 게시글 및 댓글 좋아요/좋아요 취소 기능

#### 팔로우 기능 구현
    * 특정 사용자를 팔로우/언팔로우
    * 팔로우하는 사용자의 게시물을 보기

#### 이메일 가입 및 인증 기능
    * 이메일 가입 시 이메일 인증 기능을 포함하는 것이 좋습니다.

  </details>




### 명예의 전당 - 슈퍼 개발자(?)로서의 초석을 다져봅시다!
  <details>
  <summary>더보기</summary>

#### 사진 업로드 기능 구현
    * AWS S3를 이용한 사진 업로드

#### 게시물에 멀티미디어 지원 기능 구현
    * 게시물 본문에 사진이나 영상 등의 미디어 포함 지원
    * 첨부된 미디어 수정
    * AWS S3를 사용하기

#### AWS를 이용한 서비스의 배포(구현 미완료)
    * AWS EC2를 이용해서 배포하기

#### HTTP를 HTTPS로 업그레이드 하기(구현 미완료)
    * HTTPS를 적용하여 보안이 강화된 웹 페이지를 제공해보도록 합니다!

  </details>
</details>

****
<br/>

## ERD(Entity Relationship Diagram)
<img width="849" alt="8로팔로미 erd 230722" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/03a368f9-fde5-4f7b-9d10-8d6b0681367f">


****
<br/>

## 와이어프레임
<details>
<summary>상세보기</summary>

![슬라이드1](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/0dee8919-87b9-457e-afd0-45fde9be392c)
![슬라이드2](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/aae81068-a5d8-470e-bff3-bdf1fcc6ee28)
![슬라이드3](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/877cef3a-0735-4bab-9dc3-4b7aaef82d13)
![슬라이드4](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/ebb4d55b-4a55-4f00-ad50-9557841f60c9)
![슬라이드5](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/7b4fa0b2-6a39-42d9-870f-363f708069df)
![슬라이드6](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/1b038432-ae5a-41f4-a22a-3478a3499690)
![슬라이드7](https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/0d317de9-07ca-4722-8bbb-b05b31ed661a)


<br/><br/>
<br/>
<br/>

</details>

****
<br/>

## API 명세
<details>
<summary>상세보기</summary>

### 사용자 인증 기능
  <img width="631" alt="스크린샷 2023-07-22 030327" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/1599db62-66f5-40ab-86c4-2475b7ff4bac">


  <br>

### 게시글
  <img width="400" alt="스크린샷 2023-07-22 030415" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/39293623-0258-4709-bcfb-9a3472c532a0">
<img width="420" alt="스크린샷 2023-07-22 030437" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/7340eb0f-72a5-45a5-8ec1-24181e38ba3d">


  <br>

### 댓글
  <img width="392" alt="스크린샷 2023-07-22 030504" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/288cf0aa-5ac8-46a5-ad33-2c1ade1a959d">


  <br>

### 좋아요
  <img width="404" alt="스크린샷 2023-07-22 030547" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/b4be998a-2720-44f0-9abe-8774134e4135">


  <br>

### 팔로우
  <img width="413" alt="스크린샷 2023-07-22 030602" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/10c806aa-bbd1-40a8-86aa-fbf8b81e4e75">


  <br>

### 미디어
  <img width="408" alt="스크린샷 2023-07-22 030630" src="https://github.com/sepang-pang/8lowFollowMe_Project/assets/131599243/7d945a2f-4896-4370-bef6-450e1388cb43">


<br/><br/>
<br/>
<br/>

</details>

****
<br/>

## 역할 분담
- 오세창 : 사용자 인증 기능, 백오피스 회원 관리 기능, 유저 프로필 기능
- 권진혁 : 좋아요 기능, 회원가입 시 이메일 인증 기능 
- 이승현 : 게시글 CRUD 기능, 백오피스 게시글 관리 기능
- 조해나 : 댓글 CRUD 기능, 팔로우 기능, 게시물 멀티미디어 지원 기능, 소셜 로그인 기능
- 문채원 : 개인 과제 구현 후 추가 기능 구현 시도
