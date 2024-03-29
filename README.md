# jamddo - 재미로 해보는 로또 시뮬레이션
<img src="./docs/thumb.png">


## :date: 프로젝트 진행 기간
2022.11.21 ~ 2023.01.31

## ✔ 서비스 소개
로또 시뮬레이션을 진행해볼 수 있는 웹사이트 입니다.

## ✔ 사용 기술
Frontend: Vue

Backend: SpringBoot, JPA, Querydsl

Database: MySQL

Infra: Docker

## ✔ 기능 소개
- 시뮬레이션 기능
    - 하나의 로또를 구매할 수 있습니다.
        
        <img src="./docs/home1.png">
        
    - N개의 로또를 구매할 수 있습니다. N개 번호를 모두 확인해 볼 수도 있고 N개의 번호 중 당첨된 내역의 번호만 확인하는 것도 가능합니다.

        <img src="./docs/home2.png">
        
    - 1등이 당첨될 때까지 구매를 진행해 볼 수 있습니다. 1등이 당첨되기 위해 얼마의 시도가 있었고 1등 이외의 당첨이 몇번 일어났는지 알려줍니다.
        
        <img src="./docs/home3.png">
        
- 통계정보 제공
    - 페이지에 접속하면 이번주 당첨번호, 이번주 상금 정보, 역대 당첨번호에 대한 통계값을 제공합니다.
        
        <img src="./docs/home4.png">

## ✔ 핵심 작업

- 도메인 중심 설계
    - 로또 번호를 숫자를 원시타입이 아닌 LottoNumber라는 도메인으로 정의해 1~45사이의 중복되지 않는 숫자라는 도메인 특성 부여했습니다.
    - LottoNumber객체를 매번 생성하지 않고 static필드에 값을 선언하고 재활용하는 방식으로 메모리 사용 효율을 높였습니다.
- 테스트 코드 작성
    - 도메인의 주요 기능에 대한 모든 테스트 코드 작성해 코드의 안전성을 높였습니다.

---

## ✔ 문제 상황과 해결 과정

- Querydsl의 데드락 현상
    - 문제 원인: Q타입이 순환 의존을 가질 경우, 멀티 쓰레드 환경에서 Q타입을 초기화하면 데드락이 발생할 가능성 존재했습니다.
    - 해결 방법: postConstructor시점에 ClassPathUtils의 scanPackage메서드를 사용해 멀티 쓰레드 환경 이전에 단일 쓰레드에서 클래스를 초기화 해 데드락 방지했습니다.
        - [문제 발견 및 해결 과정](https://velog.io/@qwerty1434/%EB%A9%80%ED%8B%B0%EC%93%B0%EB%A0%88%EB%93%9C-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-querydsl-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)
        - [문제 상황을 재현한 코드](https://github.com/qwerty1434/querydsl_multiThread_deadlock)
    - 현재는 리팩토링을 통해 엔티티의 순환 의존 관계를 제거해 데드락이 발생하지 않는 상태입니다.
