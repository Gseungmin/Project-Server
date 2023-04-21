## 상세 정리 노션 페이지
[notion](https://jseungmin.notion.site/b59d6627bf0042fb8955e3d3a7c5607e)

## ❄ 프로젝트 목표
- 클라우드 네이티브에 대한 이해
  - [x]  도커 컴포즈 및 도커 파일 작성해 로컬에서 빌드
  - [x]  Code Deploy와 Github Actions를 통한 CI/CD 구축
- 스프링 코드 복습
  - [x]  Query Dsl을 통한 검색 쿼리
  - [x]  Spring Security 인증 및 인가 처리를 통한 JWT 토큰 발급
  - [x]  Exception Handler를 통한 예외 처리
  - [x]  지연 로딩과 패치 조인을 사용한 쿼리 성능 최적화
- 남은 목표
  - [ ] 무중단 배포 해보기

## 🌇 프로젝트를 통해 알게된 점
- Spring Security는 필터 기반이라 @RestControllerAdvice의 적용 범위를 벗어남
  - AuthenticationEntryPoint 클래스를 통해 인증 및 인가 예외 처리해야 함
- Mysql과 CI 사용할때 DB가 설치되어 있지 않아 빌드 에러가 발생함
  - CI 스크립트에서 Mysql 서버를 올려야 함
- 도커 컴포즈에서 volume을 통해 DB 스키마 생성
  ``` volumes:
  - ./db/mysql.d/create_table.sql:/docker-entrypoint-initdb.d/create_table.sql
- CodeDeploy를 통한 배포 자동화 프로세스
  - 다운타임이 발생하는 아쉬움을 통해 무중단 배포라는 새로운 공부 목표를 가짐

## 🌨 아키텍처
<img width="800" src="https://user-images.githubusercontent.com/87487149/229875743-719d2dbe-9a0c-447c-ac38-fc8e1745dcd6.jpg">

## 🌨 ERD
<img width="400" src="https://user-images.githubusercontent.com/87487149/232273356-c7629197-1311-464a-bbd2-52103efdfce8.png">

## 주요 기능에 대한 영상 정리
[정리](https://jseungmin.notion.site/b59d6627bf0042fb8955e3d3a7c5607e)

## 로컬에서 Docker 실행
#### 1. 빌드
```
./gradlew build
```
#### 2. 프로젝트 레벨에서 컴포즈 파일 빌드
```
docker-compose up --build -d
```

## Android Repository
* [안드로이드](https://github.com/Gseungmin/personal-project-android)

## 🌃 기술 스택
#### Framework
* Java 11
* Spring Boot 2.7.10
#### Dependencies
* Spring Security + JWT
* Spring Data Jpa
* QueryDsl
#### Infra
* Github Actions
* AWS EC2
* AWS S3
* AWS CodeDeploy
#### Database
* MySQL (Local DB)
* H2 (In-memory Test DB)
