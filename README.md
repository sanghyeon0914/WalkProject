# 🗓️ WalkProject

일정을 생성하고, 댓글 및 대댓글을 통해 소통할 수 있는 일정 관리 웹 애플리케이션입니다.  

---
# 프로젝트 개요

Schedule, Comment, reply CRUD 구현
docker-compose 를 이용한 개발 환경 간 일관성 보장
JPQL + Map 변환 방식을 통해 전체 일정 목록 조회

---
## 기능 (Features)

- 일정 생성 / 조회 / 수정 / 삭제
- 댓글 및 대댓글 작성
- 댓글 수 계산 (댓글 + 대댓글 포함)

---

## 🛠 기술 스택 (Tech Stack)
```text
Java 17
Spring Boot 3.2
Spring Data JPA (Hibernate)
MySQL
Gradle
Docker
```


## 📂 프로젝트 구조

```text
└─src
    ├─main
    │  ├─java
    │  │  └─org
    │  │      └─example
    │  │          └─walkproject
    │  │              ├─schedule
    │  │              │  ├─controller
    │  │              │  ├─dto
    │  │              │  ├─entity
    │  │              │  ├─repository
    │  │              │  └─service
    │  │              ├─comment
    │  │              │  ├─controller
    │  │              │  ├─dto
    │  │              │  ├─entity
    │  │              │  ├─repository
    │  │              │  └─service
    │  │              ├─reply
    │  │              │  ├─controller
    │  │              │  ├─dto
    │  │              │  ├─entity
    │  │              │  ├─repository
    │  │              │  └─service
    │  │              ├─common

```
📊 API 명세 (API Spec)

https://teamsparta.notion.site/1e62dc3ef5148047ae2cf89c54474b07?p=1e62dc3ef51481d09fffc8a337aa07c5&pm=s
