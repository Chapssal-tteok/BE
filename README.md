# PreView Backend Repository

## 👥 팀 소개

**Team 30 - 찹쌀떡**

| 항목 | 신정화 (2271035) | 차현주 (2276321) |
|-----|-----------------|-----------------|
| GitHub | [@jungh150](https://github.com/jungh150) | [@chacha091](https://github.com/chacha091) |
| 역할  | 팀장 / 백엔드 개발자 | 팀원 / 프론트엔드 개발자 |
| 담당 업무 | 백엔드 개발, 서버 배포, AI 기능 구현 | 프론트엔드 개발, UI/UX 디자인, AI 기능 구현 |

## 🔍 프로젝트 개요

**PreView**는 GPT-4o와 RAG 기반 기술을 활용하여 자기소개서 분석과 맞춤형 면접 연습을 지원하는 **AI 면접 시뮬레이션 서비스**입니다.  
취업 준비자가 보다 효율적으로 자기소개서를 개선하고, 실제 면접처럼 연습할 수 있도록 돕는 것이 핵심 목표입니다.

### 🎯 주요 목표
- 객관적이고 세밀한 자기소개서 분석 및 피드백 제공
- 자기소개서 및 기업·직무 맞춤형 면접 예상 질문 생성
- 음성 기반 인터뷰 시뮬레이션을 통한 실전 감각 향상

### 🧠 기술적 특성
- **GPT-4o** 기반 프롬프트 엔지니어링을 통한 정밀한 자소서 피드백
- **Perplexity AI + ChromaDB** 기반 RAG 시스템으로 기업 및 직무 맞춤형 질문 생성
- **Google TTS/STT** 기술을 활용한 음성 기반 면접 연습 기능 제공
- **Spring Boot + FastAPI** 구조로 백엔드 서버와 AI 서버 분리 구성

### 🛠 제공 기능
1. **자기소개서 피드백**  
   → 질문 충실도, 논리 흐름, 구체성 등 6가지 항목 분석 + 개선 예문 제시  
2. **맞춤형 면접 질문 생성**  
   → 자기소개서 핵심 키워드 기반, RAG로 도출된 예상 질문 제공  
3. **음성 면접 시뮬레이션**  
   → TTS/STT로 실제 면접처럼 연습, 답변 분석 및 후속 질문 피드백 제공

PreView는 누구나 **혼자서도 실전 면접을 준비할 수 있는 AI 면접 파트너**입니다.

## 🛠 기술 스택 및 주요 라이브러리

### 🧩 사용 기술

| 항목 | 내용 |
|-----|------|
| Language | Java 17 |
| Framework  | Spring Boot 3.2.5 |
| Build Tool | Gradle |
| Database | MySQL (Amazon RDS) |
| 비동기 통신 | Spring WebFlux (비동기 외부 API 호출에 사용) |
| 배포 방식 | WAR 패키징, AWS EC2 기반 수동 배포 |
| 클라우드 연동 | AWS S3, Google Cloud TTS/STT |
| 문서화 도구   | Swagger UI (SpringDoc OpenAPI 2.5.0) |

### 📦 주요 라이브러리

| 범주 | 라이브러리명 / 기능 설명 |
|-----|-----------------------|
| **Spring Boot** | `spring-boot-starter-web` – REST API 기본 구성<br>`spring-boot-starter-data-jpa` – JPA ORM<br>`spring-boot-starter-validation` – 입력값 검증 |
| **보안 / 인증** | `spring-boot-starter-security` – 보안 설정<br>`jjwt` – JWT 기반 인증/인가 구현 |
| **문서화** | `springdoc-openapi-starter-webmvc-ui:2.5.0` – Swagger UI 생성 |
| **비동기 통신** | `spring-boot-starter-webflux` – WebClient 기반 외부 API 비동기 호출 |
| **Google API** | `google-cloud-texttospeech`, `google-cloud-speech` – Google TTS/STT 기능 연동 |
| **AWS SDK** | `software.amazon.awssdk:s3` – AWS S3 파일 업로드/관리 |
| **DB 드라이버**| `mysql-connector-j` – MySQL DB 연결 |
| **개발 보조**  | `lombok`, `annotationProcessor` – 코드 간결화 |

이 백엔드 서버는 사용자/자기소개서/면접 관련 데이터를 관리하며,  
**TTS/STT 음성 처리**, **AI 서버 연동을 위한 WebClient**, **AWS S3 업로드**,  
**JWT 인증**, **Swagger 문서화**, **비동기 API 호출** 기능들을 포함하고 있습니다.

## 📁 디렉토리 구조

```
preview-backend/
├── build.gradle
├── settings.gradle
├── README.md
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.chapssal_tteok.preview
│ │ │ ├── PreviewApplication.java # 메인 실행 클래스
│ │ │ ├── domain # 주요 도메인 엔티티 및 비즈니스 로직
│ │ │ │ ├── interview # 면접 세션 관련 도메인
│ │ │ │ ├── interviewqa # 면접 질문/답변 관련 도메인
│ │ │ │ ├── resume # 자기소개서 관련 도메인
│ │ │ │ ├── resumeqa # 자기소개서에 대한 질문/분석 도메인
│ │ │ │ ├── user # 사용자 정보 관련 도메인
│ │ │ │ └── voice # TTS/STT 음성처리 도메인
│ │ │ ├── global # 공통 전역 유틸리티 / 설정
│ │ │ │ ├── apiPayload # API 응답/요청 공통 포맷
│ │ │ │ ├── client # 외부 API 연동 (AI 서버 등)
│ │ │ │ ├── common # 공통 상수/헬퍼 등
│ │ │ │ ├── config # Spring 설정 클래스
│ │ │ │ └── validation # 커스텀 유효성 검사
│ │ │ ├── infra # 외부 연동 인프라 (ex. AWS)
│ │ │ └── security # JWT 인증 및 보안 설정
│ └── resources
│ ├── application.yml # 환경 설정 파일
│ └── google-key.json # Google TTS/STT 서비스 키
├── test
│ └── ... # 테스트 코드
```

## 🔧 사전 준비사항

백엔드 서버를 실행하기 위해 아래의 환경 및 자원들이 필요합니다:

### ✅ 필수 환경

| 항목 | 내용 |
|-----|------|
| Java | JDK 17 이상 |
| Gradle | Wrapper 포함 (별도 설치 불필요) |
| DB | MySQL (로컬 or AWS RDS) |
| Google Cloud | TTS / STT 사용을 위한 서비스 계정 키 |
| AWS | S3 버킷 및 IAM 권한 |

## 🚀 시작하기

### 1️⃣ 레포지토리 클론

```bash
git clone https://github.com/Chapssal-tteok/preview-backend.git
cd preview-backend
```

### 2️⃣ 환경 변수 및 설정 파일 구성

#### 🔹 `application.yml` 작성

`src/main/resources/application.yml` 파일을 다음과 같이 작성합니다:

```yaml
spring:
  profiles:
    active: dev
    include: google
  datasource:
    url: jdbc:mysql://<DB_HOST>:3306/<DB_NAME>
    username: <DB_USERNAME>
    password: <DB_PASSWORD>
    driver-class-name: com.mysql.cj.jdbc.Driver
  sql:
    init:
      mode: never
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        show_sql: false
        format_sql: true
        use_sql_comments: true
        hbm2ddl:
          auto: update
        default_batch_fetch_size: 1000

jwt:
  secret-key: <JWT_SECRET>
  expiration:
    access: 3600000 # Access Token 만료 시간 (1시간: 밀리초 단위)
    refresh: 604800000 # Refresh Token 만료 시간 (7일: 밀리초 단위)

google:
  credentials:
    path: classpath:google-key.json

cloud:
  aws:
    s3:
      bucket: preview-s3-bucket
    region:
      static: ap-northeast-2
    credentials:
      access-key: ${AWS_ACCESS_KEY}
      secret-key: ${AWS_SECRET_KEY}

ai:
  server-url: http://<AI_SERVER_HOST>:<PORT>  # 예: http://localhost:8000

cors:
  allowed-origins:
    - https://<YOUR_FRONTEND_DOMAIN>  # 예: https://your-frontend.vercel.app
```

#### 🔹 Google Cloud 서비스 키 등록

TTS/STT 기능을 사용하기 위해 Google Cloud에서 발급받은 **서비스 계정 키(JSON)** 파일을 `src/main/resources/` 경로에 추가합니다.

파일명은 반드시 `google-key.json` 으로 유지해야 하며, 프로젝트 내 설정(`application.yml`)과 일치해야 합니다.

서비스 키는 [Google Cloud Console](https://console.cloud.google.com/)에서 생성할 수 있습니다.

### 3️⃣ 필수 도구 설치

#### 🔧 JDK 설치

- JDK 17 이상 필요
- [Adoptium OpenJDK](https://adoptium.net/) 또는 IntelliJ 내장 JDK 설정 사용 가능

#### 🔧 ffmpeg 설치 (음성 처리 기능에 필요)
| OS | 설치 명령어 또는 경로 |
|----|-------|
| macOS	| `brew install ffmpeg` |
| Ubuntu | `sudo apt install ffmpeg` |
| Windows | [ffmpeg 공식 홈페이지](https://ffmpeg.org/download.html)에서 ZIP 파일 다운로드 후 `bin/` 디렉토리를 시스템 환경 변수 `PATH`에 추가 |

### 4️⃣ 서버 실행

#### ▶️ 방법 1: 개발 환경에서 실행

개발 시에는 Gradle이 제공하는 `bootRun` 명령어를 통해 실행할 수 있습니다.

```bash
./gradlew bootRun
```

#### ▶️ 방법 2: 배포 환경 또는 수동 실행용 .war 빌드 & 실행

운영 서버 또는 배포 테스트 환경에서는 .war 파일을 빌드한 뒤 직접 실행하는 방식을 사용합니다.

```bash
# 빌드
./gradlew build

# 실행
java -jar build/libs/preview-backend-0.0.1-SNAPSHOT.war
```

빌드 결과물인 .war 파일은 build/libs/ 디렉토리에 생성됩니다.

- 기본 실행 주소: http://localhost:8080
- Swagger 문서: http://localhost:8080/swagger-ui/index.html
