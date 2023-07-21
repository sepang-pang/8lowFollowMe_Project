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
![img_14.png](img_14.png)

****
<br/>

## 와이어프레임
<details>
<summary>상세보기</summary>

![img_7.png](img_7.png)
![img_8.png](img_8.png)
![img_9.png](img_9.png)
![img_10.png](img_10.png)
![img_11.png](img_11.png)
![img_12.png](img_12.png)
![img_13.png](img_13.png)

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
  ![img.png](img.png)

  <br>

### 게시글
  ![img_1.png](img_1.png)
  ![img_2.png](img_2.png)

  <br>

### 댓글
  ![img_3.png](img_3.png)

  <br>

### 좋아요
  ![img_4.png](img_4.png)

  <br>

### 팔로우
  ![img_5.png](img_5.png)

  <br>

### 팔로우
  ![img_6.png](img_6.png)

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