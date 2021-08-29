## Simple Auth Application

- 간단한 회원 가입, 수정, 삭제 및 세션 기반의 폼 인증을 구현합니다.
- 회원 테이블은 오직 id, password 컬럼만을 가지며, id는 3자 이상 20자이하(공백 없는 영문 또는 숫자), password는 6자 이상 30자 이하의 제약조건을 가집니다.
- password 인코딩은 생략합니다.

<br />

## 개발 환경

- SpringBoot 2.5.4
- Java 8
- Spring Data JPA
- H2 Database
- Lombok

<br />

## 주요 기능

- 회원 가입
- 로그인
- 비밀번호 변경
- 계정 삭제

<br />

## 실행방법

### CLI

```
./gradlew bootRun
```

<br />

### 테이블 및 데이터 생성

- 기본적으로 H2 Database를 사용하며, 별도의 테이블이나 데이터 생성 없이 `http://localhost:8080/h2-console` 로 접속하면 기본 데이터가 제공 되는 것을 확인하실 수 있습니다. 그러나 만약 별도의 DB에서 데이터 생성을 해야 한다면 다음의 SQL을 실행하면 됩니다.

```sql
# Schema
DROP TABLE IF EXISTS account;

CREATE TABLE account (
    id       VARCHAR(20),
    password VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

# Data
INSERT INTO account (id, password) VALUES ('userId1', 'password1');
INSERT INTO account (id, password) VALUES ('userId2', 'password2');
INSERT INTO account (id, password) VALUES ('userId3', 'password3');
```

해당 SQL은 `resources/db/schema.sql`  `resources/db/data.sql` 에서 또한 확인하실 수 있습니다.

<br />

## 프로젝트 구조

- controller
- dto
  - request
  - response
  - login
- entity
- error
  - exception
  - response
- repository
- service

<br />

## API 요청 및 응답 상세 내용

### 1. 회원가입

`ENDPOINT`

```
POST  /api/accounts
```

`REQUEST BODY`

```
{
    "id": "userId",
    "password": "password",
    "confirmPassword": "password"
}
```

<br />

### 2. 로그인

`ENDPOINT`

```
POST /api/login
```

`REQUEST BODY`

```
{
    "id": "userID",
    "password": "password",
    "confirmPassword": "password"
}
```

<br />

### 3. 패스워드 변경

`ENDPOINT`

```
PUT /api/accounts/{accountId}
```

`REQUEST BODY`

```
{
    "id": "userID",
    "password": "changedPassword",
    "confirmPassword": "changedPassword"
}
```

<br />

### 4. 계정 삭제

`ENDPOINT`

```
DELETE /api/accounts/{accountId}
```

`REQUEST BODY`

```
NONE
```

<br />

## API 에러 응답

- 클라이언트와 서버 모두 Validation 하는 상황을 가정합니다.

<br />

### 1. 유효하지 않은 ID, 패스워드 값을 입력하여 가입 시도

```
{
    "statusCode": 400,
    "errorMessage": "요청한 ID 또는 비밀번호 값이 유효하지 않습니다. 다시 요청 해주세요.",
    "errors": [
        {
            "value": "password",
            "reaseon": "Password 값은 6자 이상 30자 이하입니다."
        }
    ]
}
```

<br />

### 2. 이미 존재하는 ID로 가입 시도

```
{
    "statusCode": 409,
    "errorMessage": "요청한 아이디의 계정이 이미 존재합니다. 다른 아이디로 다시 요청 해주세요.",
    "errors": []
}
```

<br />

### 3. 회원 가입시 입력 패스워드, 확인 패스워드가 일치하지 않는 경우

```
{
    "statusCode": 400,
    "errorMessage": "비밀번호와 확인 비밀번호가 일치하지 않습니다.",
    "errors": []
}
```

<br />

### 4. 비로그인 상태에서 비밀번호 수정 / 계정 삭제 시도

```
{
    "statusCode": 401,
    "errorMessage": "인증이 필요합니다. 로그인 이후 다시 시도 해주세요.",
    "errors": []
}
```

<br />

### 5. 로그인한 사용자와 요청한 수정 / 삭제 계정이 일치하지 않는 경우

```
{
    "statusCode": 403,
    "errorMessage": "해당 요청에 대한 권한이 없습니다. 인증 이후 다시 시도 해주세요.",
    "errors": []
}
```

