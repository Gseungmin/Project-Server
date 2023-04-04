## ❄ 프로젝트 목표
* 클라우드 네이티브에 대한 이해
  * [X] 도커 컴포즈 및 도커 파일 작성
  * [X] Code Deploy와 Github Actions를 통한 CI/CD 구축
* 스프링 코드 복습
  * [X] Query Dsl을 통한 검색 쿼리
  * [X] Spring Security 인증 및 인가 처리를 통한 JWT 토큰 발급
  * [X] Exception Handler를 통한 예외 처리
  * [X] 지연 로딩과 패치 조인을 사용한 쿼리 성능 최적화
* 남은 목표
  * [ ] Node JS 서버를 추가해 MSA 아키텍처 구축
  * [ ] Planet Scale DB 사용해보기
  * [ ] 쿠버네티스를 통한 컨테이너 관리

## 🌇 프로젝트를 통해 알게된 점
* Spring Security 인증 및 인가 예외 처리
  * AuthenticationEntryPoint 클래스를 통해 인증 및 인가 예외 처리
* CodeDeploy를 통한 배포 자동화 프로세스
  * [프로세스 정리 페이지](https://jseungmin.notion.site/CodeDeploy-b8bf7114b60f475bafb2883f9223361d)

## 🌨 아키텍처
<img width="800" src="https://user-images.githubusercontent.com/87487149/229875743-719d2dbe-9a0c-447c-ac38-fc8e1745dcd6.jpg">

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
