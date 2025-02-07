# WebSocket 채팅 애플리케이션

Spring Boot를 활용한 실시간 채팅 애플리케이션입니다. WebSocket, STOMP, 그리고 Redis를 단계별로 적용하여 확장 가능한 채팅 서비스를 구현합니다.

## 프로젝트 개요
- Spring Boot 3.2.x
- Java 17
- Gradle
- WebSocket / STOMP
- Spring Data JPA
- H2 Database / MySQL
- Redis
- Spring Security (예정)

## 단계별 구현 사항

### 1단계: WebSocket 기본 채팅
#### 기능 요구사항
1. 사용자 연결 관리
   - 사용자는 닉네임을 입력하여 채팅에 참여
   - WebSocket handshake 시점에 닉네임 중복 검증
   - 연결 성공/실패 시 적절한 피드백 제공

2. 메시지 송수신
   - 텍스트 메시지 실시간 송수신
   - 메시지 포맷: `{timestamp, sender, content}`
   - 시스템 메시지 (입장/퇴장) 자동 발송

3. 데이터 영속성
   - H2 Database를 활용한 메시지 저장
   - 메시지 엔티티 설계
   ```sql
   Message {
     id: Long
     sender: String
     content: String
     timestamp: LocalDateTime
   }
   ```

### 2단계: STOMP 기반 채팅방
#### 기능 요구사항
1. 채팅방 관리
   - 채팅방 생성: 방 제목, 최대 인원 설정
   - 채팅방 목록 조회: 현재 인원, 최대 인원 표시
   - 채팅방 참여/퇴장
   - 참여자 목록 실시간 갱신

2. 채팅방별 메시지 관리
   - STOMP destination을 통한 방별 메시지 발송
   - 최근 메시지 50개 자동 로드
   - 채팅방별 메시지 저장 및 조회
   ```sql
   ChatRoom {
     id: Long
     name: String
     maxMembers: Integer
     createdAt: LocalDateTime
   }
   
   ChatMessage {
     id: Long
     roomId: Long
     sender: String
     content: String
     timestamp: LocalDateTime
   }
   ```

### 3단계: Redis 활용 성능 최적화
#### 기능 요구사항
1. 실시간 데이터 Redis 관리
   - 접속자 세션 관리
   - 채팅방별 참여자 목록
   - 읽지 않은 메시지 수 추적

2. 메시지 저장 및 조회
   - MySQL을 활용한 메시지 영구 저장
   - 페이지네이션 처리 (메시지 이력)
   - 채팅방 목록에서 최근 메시지 미리보기

3. 채팅방 기능 개선
   - 채팅방별 실시간 참여자 수 관리
   - 사용자별 읽지 않은 메시지 수 표시
   - 채팅방 나가기 시 읽지 않은 메시지 처리

## API 명세
### WebSocket Endpoints
- `/ws-connect` : WebSocket 연결
- `/pub/chat/message` : 메시지 발행
- `/sub/chat/room/{roomId}` : 채팅방 구독

### REST API Endpoints
- `POST /api/v1/rooms` : 채팅방 생성
- `GET /api/v1/rooms` : 채팅방 목록 조회
- `GET /api/v1/rooms/{roomId}` : 채팅방 정보 조회
- `GET /api/v1/rooms/{roomId}/messages` : 채팅방 메시지 이력 조회

## 성능 요구사항
- 동시 접속자 100명 기준 
- 메시지 전송 지연 500ms 이내
- 채팅방당 최대 인원 50명
- 초당 메시지 처리량 100건 이상

## 추가 고려사항
- 보안
  - WebSocket 인증/인가
  - CSRF 방어
  - XSS 방지
- 모니터링
  - 접속자 수 모니터링
  - 메시지 처리량 모니터링
  - 에러 로깅
- 확장성
  - 멀티 인스턴스 지원
  - 메시지 브로커 확장
  - 데이터베이스 샤딩
