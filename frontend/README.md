# React Todo 화면

이 프론트는 기존 Spring Boot Todo API를 사용합니다.

## 파일 역할

- `src/App.jsx`: React 화면, 입력값·목록·필터 상태, 버튼 이벤트
- `src/App.css`: 화면 디자인과 모바일 레이아웃
- `src/api.js`: 서버 요청과 오류 응답 처리
- `src/main.jsx`: React 앱 시작 및 CSS 불러오기
- `vite.config.js`: 개발 서버 프록시와 Spring 정적 파일 출력 설정

## 이 PC에서 실행

설정된 VS Code 작업 공간에서 F5를 누르면 React를 먼저 빌드하고 Java 서버를 시작합니다.
브라우저에서 http://127.0.0.1:8080 에 접속하세요. 종료는 Shift+F5입니다.
JSX/CSS 수정 후에는 서버를 종료하고 다시 F5를 누르면 변경 내용이 빌드됩니다.

## 일반적인 개발 환경

Node.js 24와 npm이 설치된 환경에서 이 폴더를 열고:

```sh
npm ci
npm run dev
```

Java 서버도 실행해야 합니다. Vite가 표시하는 주소에서 개발하면 수정 사항이 즉시 반영됩니다.
`/todos` API 요청은 Vite가 `http://127.0.0.1:8080`으로 전달합니다.

```sh
npm run build
```

빌드 결과는 `../src/main/resources/static`에 생성됩니다. 이 폴더의 생성 파일 대신
`frontend/src`의 원본을 수정하세요. Maven으로 JAR를 패키징할 때는 먼저 프론트를 빌드하세요.

## React 핵심 개념

`useState`는 할 일 목록과 입력값을 기억합니다. `useEffect`는 화면을 처음 열 때 목록을
조회합니다. `map`은 목록을 JSX로 바꾸고, 상태가 바뀌면 React가 화면을 갱신합니다.
완료 체크는 서버의 PATCH 요청이 성공한 후 화면에 반영합니다.
