# 로또 앱 개발 계획

## 1. 프로젝트 목표

한국의 로또 당첨 번호를 확인하고 랜덤으로 번호를 생성하는 안드로이드 애플리케이션을 개발합니다.

## 2. 핵심 기능

- **당첨 번호 확인:** 최신 로또 당첨 번호를 가져와 화면에 표시합니다.
- **랜덤 번호 생성:** 무작위 로또 번호 조합을 생성하여 제공합니다.
- **(선택) 번호 저장:** 생성되거나 수동으로 입력한 번호를 저장하는 기능을 제공합니다.
- **(선택) QR 코드 스캔:** 실제 로또 용지의 QR 코드를 스캔하여 당첨 여부를 확인합니다.

## 3. 아키텍처 및 라이브러리

- **전체 아키텍처:** Model-View-ViewModel (MVVM)
- **UI:** Jetpack Compose
- **비동기 처리:** Kotlin Coroutines
- **의존성 주입:** Hilt
- **네트워킹:** Retrofit, OkHttp (외부 API로부터 당첨 번호를 가져오기 위해 사용)
- **JSON 파싱:** Kotlinx.serialization
- **데이터 영속성:** Room (사용자의 로또 번호를 저장하기 위해 사용)

## 4. 개발 단계

### 1단계: 프로젝트 설정
- 의존성 주입을 위한 Hilt 설정.
- 네트워크 및 데이터베이스를 위한 기본 모듈 설정.

### 2단계: 데이터 계층
- **원격(Remote):**
    - 로또 당첨 번호를 위한 데이터 클래스 정의 (예: `LottoResult`).
    - 공공 로또 API에서 데이터를 가져오기 위한 Retrofit `ApiService` 생성.
- **로컬(Local):**
    - 저장된 로또 번호를 위한 Room `Entity` 정의.
    - 데이터베이스 작업을 위한 `LottoHistoryDao` 생성 (CRUD).
    - Room `AppDatabase` 설정.

### 3단계: 도메인 계층
- 데이터 소스(원격 및 로컬)를 추상화하는 `LottoRepository` 생성.
- 유스케이스(Use Case) 구현:
    - `GetLatestWinningNumbersUseCase`: 저장소에서 최신 당첨 번호를 가져옵니다.
    - `GenerateRandomNumbersUseCase`: 랜덤 로또 번호 조합을 생성합니다.
    - `SaveNumbersUseCase`: 번호 조합을 로컬 데이터베이스에 저장합니다.
    - `GetSavedNumbersUseCase`: 저장된 모든 번호를 검색합니다.

### 4단계: UI 계층 (Jetpack Compose)
- **메인 화면:**
    - "당첨 번호" 화면과 "번호 생성기" 화면으로 이동할 수 있는 메인 화면 생성.
- **당첨 번호 화면:**
    - 최신 당첨 번호와 보너스 번호를 표시합니다.
- **번호 생성기 화면:**
    - 랜덤으로 생성된 번호 조합을 보여줍니다.
    - "재생성" 버튼을 포함합니다.
    - "저장" 버튼을 포함합니다.

### 5단계: 뷰모델 (ViewModel)
- `WinningNumberViewModel`:
    - `GetLatestWinningNumbersUseCase`를 사용하여 데이터를 가져오고 당첨 번호 화면의 UI 상태를 관리합니다.
- `NumberGeneratorViewModel`:
    - `GenerateRandomNumbersUseCase` 및 `SaveNumbersUseCase`를 사용합니다.
    - 번호 생성기 화면의 상태를 관리합니다.

### 6단계: 테스트
- 뷰모델, 유스케이스, 리포지토리에 대한 단위 테스트 작성.
- UI 구성 요소 및 데이터베이스 작업을 검증하기 위한 계측 테스트 작성.