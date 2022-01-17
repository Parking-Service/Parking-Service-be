[![Build Status](https://img.shields.io/badge/status-developing-orange)](https://github.com/Pgmjun/Parking-Service)
![License](https://img.shields.io/apm/l/vim-mode?color=yellowgreen)
[![Framework](https://img.shields.io/badge/framework-spring-brightgreen)](https://spring.io/)

<br>

<br>

<p align="center">
  <img width="250" height="250" src="https://user-images.githubusercontent.com/84304802/145155841-90c5d0d0-c84d-4ba3-87c7-2c39370bbc78.jpg">
</p>



# '어따세워'

<br>

우린 운전을 하면서 종종 **"주차할 곳이 없는"** 문제에 직면한다.

<br>

하지만 전국의 **주차장 주소 / 요금 / 운영시간** 등 다양한 정보를 제공하는 서비스 앱이 있다면 어떨까?

<br>

우린 전국의 주차 관련 문제를 겪고 있는 사람들에게 해결책을 제시하려한다.

<br>

## ✨핵심 기능 및 기대 효과

+ ### 핵심 기능

  <strong>[구현 완료]</strong> <br>
  
    💡 로그인/회원가입 기능(카카오톡, 페이스북, 구글 지원)

    💡 메인 화면에서 내 주변 주차장 탐색 후 시각화

    💡 카카오맵을 통해 현재 내 위치 이동 및 기기가 가리키는 방향 감지 기능

    💡 주소, 주차장명 검색을 통해 주차장 데이터 검색 <strong>*음성 인식 기능 포함</strong>

    💡 주차장 아이템 클릭시 해당 주차장 정보(주차장 사진, 가격, 운영일시, 결제방법, 주소 etc...)를 시각화해서 사용자에게 제공
    
    💡 24시간 마다 주차장 정보 최신화

    <br>

  <strong>[미구현 · 구현예정]</strong>

    💡 희망 주차장을 내비게이션 앱 또는 타 지도 앱 연동

    💡 즐겨찾기, 가봤어요 기능 등 희망 주차장 개인 리스트화 기능 제공

    💡 방문한 주차장에 대한 사용자 리뷰 및 평점을 통해 유저간의 주차장 참고점 제공

    <br>

- ### 기대효과 

  ✔️ GPS기반으로 내 주변 주차장을 빠르고 손쉽게 찾을 수 있다.

  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;👉 초보, 초행길 운전자와 같이 적절한 주차공간을 찾기 어려운 사람들에게 동선 낭비를 줄여줄 수 있다.

  ✔️ 주차장의 요금, 운영시간 등 구체적인 정보를 알 수 있어 원하는 주차장을 찾기 수월하다.

  ✔️ 지도앱에서 찾는 주차장 데이터와 다르게 장애인 할인, 경차 할인 등 다양한 혜택까지 알 수 있어 경제적이다.

  ✔️ 본인이 주차한 공간을 사진으로 기록할 수 있기 때문에 위치를 기억할 필요가 없다.

<br>
<br>

## 📋 컨벤션

### # 브랜치 관리 전략

**⚙️ git-flow**

<p align="center">
  <img src="https://user-images.githubusercontent.com/84304802/148559145-64a8029e-d220-4b80-b02f-eb45a0e07c05.png" alt="git-flow">
</p>

<br>

| 브랜치 종류  | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| Master(main) | 테스트 서버에서 테스트가 끝나고 운영서버로 배포 할 수 있는 브랜치 |
| develop      | 개발을 위한 브랜치                                           |
| feature      | 하나의 기능을 개발하기 위한 브랜치                           |
| hotfix       | 운영중인 버전에서 발생한 버그를 수정 하는 브랜치             |
| release      |	다음 버전을 출시전 테스트 및 버그 픽스를 위한 브랜치         |

- `feature` 브랜치는 하나의 기능을 개발하기 위한 브랜치입니다. 부모는 develop이며, 개발이 완료되면 develop에 merge합니다. 브랜치 이름은 보통 feature/*이 됩니다.
- `develop` 브랜치는 개발을 위한 브랜치입니다. 여러 feature들이 merge되는 장소이며, 아직 release되지 않은 기능들이 모여 있게 됩니다.
- `release` 브랜치는 배포를 위한 브랜치입니다. release는 develop에서부터 merge되며, 이후 QA테스트 등을 거쳐 발견된 버그 등은 release 브랜치에 커밋합니다. 버그 픽스를 release에서만 하게 되면 - develop과의 차이가 발생하므로, 해당 부분은 나중에 develop에 적용시켜 줄 필요가 있습니다.
- `master` 브랜치는 실제 운영 중인 서비스의 브랜치입니다. release브랜치에서 더 이상 문제가 없다고 판단되면 master에 merge해 서비스에 적용시킵니다. merge 전략에 따라 master브랜치에 merge 커밋이 남지 않을 수도 있으므로, Tag를 붙여 릴리스 버전을 관리합니다.
- `hotfix` 브랜치는 서비스에 문제가 발생했을 때 핫픽스에 해당하는 브랜치입니다. 기능 개발(feature) 등과 달리 빠르게 대처해야 할 필요가 있기 때문에, master 브랜치에 직접 merge하는 전략을 취합니다. release와 마찬가지로 develop과의 차이가 발생하기 때문에, 나중에 차이를 merge할 필요가 있습니다.


<br>

<br>

### # 브랜치 네이밍

**⚙️ 네이밍 패턴**

```
브랜치 종류/이슈번호-간단한 설명	
```

**Ex)** 이슈번호가 67인 '로그인 기능' 이슈를 구현하는 브랜치를 생성하는 경우, 브랜치 이름을<br> 	`feature/67-login` 로 작성한다.

<br>

<br>

### # 커밋 메시지

**⚙️ 메시지 구조**

```
Type : 제목 #이슈번호

본문
```

**Ex)** 이슈번호가 67인 이슈의 기능을 구현한 뒤 커밋을 하는 상황이라면 커밋 메시지의 제목을<br>	`feat : A기능 구현 #67` 으로 작성한다.

<br>

**⚙️ Type**

| 타입 종류 | 설명                                 |
| --------- | ------------------------------------ |
| feat      | 새로운 기능에 대한 커밋              |
| fix       | 수정에 대한 커밋                     |
| bug       | 버그에 대한 커밋                     |
| build     | 빌드 관련 파일 수정에 대한 커밋      |
| ci/cd     | 배포 커밋                            |
| docs      | 문서 수정에 대한 커밋                |
| style     | 코드 스타일 혹은 포맷 등에 관한 커밋 |
| refactor  | 코드 리팩토링에 대한 커밋            |
| test      | 테스트 코드 수정에 대한 커밋         |

<br>
<br>

## ⚒️기술 스택

<br>

### ⚙️Language

JAVA 11

### ⚙️FrameWork

Spring Boot 2.6.1 | Swagger 2.9.2

### ⚙️Main Library

Spring Data JPA | JUnit 5 | Lombok | OpenCsv

### ⚙️DB

MySQL 8.0

### ⚙️Storage

AWS S3


<br>

## 📱InApp 스크린샷

<p align="center" display="inline-block">
  <img width="300" height="500" src="https://user-images.githubusercontent.com/84304802/146116777-571a8920-760e-43e4-b78b-ea0368569100.jpg">
    <img width="300" height="500" src="https://user-images.githubusercontent.com/84304802/146116792-fcbba351-d8d4-4c77-8044-e4c30d6fb5d1.jpg">
    <img width="300" height="500" src="https://user-images.githubusercontent.com/84304802/146116796-05297791-8321-4fe6-add0-48c640db032b.jpg">
    <br>
    <img width="300" height="500" src="https://user-images.githubusercontent.com/84304802/146116797-ed6507cd-519f-452a-b1e2-c71f1adbfe01.jpg">
    <img width="300" height="500" src="https://user-images.githubusercontent.com/84304802/146116803-54f421f6-a391-4e22-a4b0-131f7028bf16.jpg">
    <img width="300" height="500" src="https://user-images.githubusercontent.com/84304802/146116807-f2f763f0-c174-4216-bfaf-c67a3ababed4.jpg">
</p>

<br>

## 🎥 InApp GIF

<br>

<p align="center" display="inline">
  <img src="https://user-images.githubusercontent.com/56534241/146184033-8ff4ad24-b05c-4f8a-929d-54b4f491c542.gif" width="310" height="530" />
  <img src="https://user-images.githubusercontent.com/56534241/146184052-cdc67d08-7e3d-4b2f-add3-a44cde80566d.gif" width="310" height="530" />
  <img src="https://user-images.githubusercontent.com/56534241/146184058-132baf77-428e-4f55-b56a-31760f551fc9.gif" width="310" height="530" />
  <br><br><br>
  <strong> * 현재 개발 중인 단계를 기록한 화면입니다. *<br>
    프로젝트가 진행됨에 따라 실행 영상은 지속 업데이트될 예정입니다! </strong>
</p>

<br>

## 👥파트 및 개발계획
- <strong>[개발 인원] - 2인 개발 (안드로이드, 백엔드) / 서비스 프로젝트
  <br>
- [개발 기간] - 2021/12 ~ 개발 진행 중
  <br><br>
- Backend - 1명 **(본인)**
  <br>
- Android - 1명
 <br>

📑**개발 일지**: https://pgmjun.tistory.com/2?category=1021427

🙎‍♂️**팀원 Github**: https://github.com/tmdgh1592/Parking-Service

<br>
