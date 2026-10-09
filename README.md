# Java와 Spring Boot 연습

- `Main.java`: Java 기초와 객체지향 연습
- `spring-practice/`: Spring Boot 할 일 API 연습

## 노트북에서 시작하기

1. Git, JDK 21, VS Code를 설치합니다.
2. VS Code에서 Extension Pack for Java를 설치합니다.
3. 이 저장소를 `git clone 저장소주소`로 내려받습니다.
4. VS Code에서 `spring-practice` 폴더를 엽니다.
5. 터미널에서 `java -version`이 21인지 확인합니다. JAVA_HOME도 JDK 21 설치 폴더로 설정합니다.
6. 해당 폴더의 PowerShell에서 실행합니다.

```powershell
.\mvnw.cmd spring-boot:run
```

macOS/Linux에서는 `sh ./mvnw spring-boot:run`으로 실행할 수 있습니다.
첫 실행에는 Maven과 라이브러리를 다운로드하므로 인터넷 연결이 필요합니다.

브라우저에서 http://127.0.0.1:8080/todos/sample 로 확인합니다.
서버 종료는 실행한 터미널에서 Ctrl+C입니다.

## 다른 컴퓨터에서 이어서 작업하기

작업 시작 전에 저장소 폴더에서 `git pull`을 실행합니다.
작업이 끝나면 아래 명령으로 저장하고 GitHub로 올립니다.

```powershell
git add .
git commit -m "연습 코드 수정"
git push
```

한 컴퓨터의 작업을 커밋하고 푸시한 뒤 다른 컴퓨터에서 풀하면 이어서 작업하기 쉽습니다.

## 데이터와 설정

GitHub에는 소스 코드와 Maven 설정을 저장합니다.
JDK 설치 파일, 빌드 결과, 이 PC 전용 VS Code 설정, H2 DB 파일은 제외합니다.
현재 TodoRepository는 메모리에 저장하므로 서버를 다시 시작하면 할 일 데이터가 사라집니다.
H2의 기존 데이터도 컴퓨터 간에 자동으로 옮겨지지 않습니다.
