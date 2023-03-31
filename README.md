# 기능 요구 사항
***

* 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
* 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
* 자동차 이름은 쉼표(,)를 기준으로 구분한다.
* 전진하는 조건은 0에서 9 사이에서 random 값을 구한 후 random 값이 4이상일 경우이다.
* 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한명 이상일 수 있다.

<br>

# 추가 프로그래밍 요구 사항
*** 

* 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
    * 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
    * UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
* 모든 원시 값과 문자열을 포장한다.
* 일급 컬렉션을 쓴다.

<br>

# 클래스 설계 및 기능 목록
***
Car : 
  * 필드 : String name, Integer movement;
  * 메서드 : 
    * 랜덤 수 생성하여 전진이 가능하면 movement를 +1 해줌
   
**Cars** :
  * 필드 : List<Car>
  * 메서드 :
    * 자동차의 이름의 길이 대한 검증(name<6)
    * 중복된 이름이 없는 지에 대한 검증
    * 사용자의 입력에 대해 문자열로 받아 Car를 생성하여 저장
    * 전체 Car들에 대해 랜덤 수를 다시 생성하게 함.
    * 전진 가능한 Car들의 이름을 List로 반환
    * movement가 가장 큰 Car의 name을 찾아 List로 반환

**RacingCarGame** :
  * 필드 : Cars
  * 메서드 : 
    * init() : Controller를 통해 Car들의 name을 받아 Cars에게 생성을 요청
    * gameStart : Cars를 통해 전진하게 하고 Cars를 반환;
    * findWinners : Cars를 통해 우승자를 찾게 하고 결과를 반환

**RandomGenerator** :
  * 메서드 :
    * 전달 받은 범위 내의 랜덤수 생성

**Controller**

**InputView** 

**OutputView**

<br>

# 테스트 목록
***

**Car**

- [x] 0~3 범위의 랜덤 수가 들어오면 전진하지 않는다. 
- [x] 4~9 범위의 랜덤 수가 들어오면 전진한다.

**Cars**

- [x] 1개 이상의 올바른 이름이 입력될 시 Car들이 생성되어 List로 저장이 된다.
- [x] 이름의 길이가 1~5가 아닐 시 예외 발생한다.
- [x] 중복된 이름이 있을 시 예외 발생한다.
- [x] null이 입력될 시 예외 발생한다.
- [x] 모든 Car들에게 4~9 범위의 랜덤 수를 생성하게 했을 때 모든 Car들이 전진한다.
- [x] 모든 Car들에게 0~3 범위의 랜덤 수를 생성하게 했을 때 모든 Car들이 전진하지 않는다.
- [x] 모든 Car들 중에서 movement가 가장 높은 Car의 name을 반환한다.
- [x] 모든 Car들 중 movement가 가장 높은 Car가 다수일 경우에는 모두 반환한다.

**RandomGenerator**

* 랜덤값 자체에 대한 테스트는 라이브러리를 믿고 쓰는 것을 전제로 한다.
- [x] 범위를 지정했을 때, 주어진 범위 내의 수를 생성한다.

**RacingCarGame**

- [x] init(String carNames)를 호출했을 때, carNames대로 Car들이 생성이 된다.
- [x] result()를 호출했을 때, Car의 name이 담긴 List<String>이 반환된다.
- [ ] gameStart()를 호출했을 때, Cars를 통해 Car를 전진시키는 메서드 호출 -->테스트작성 실패
<br>

 __현재 상황__ :
  gameStart에서 Cars클래스의 tryMove를 호출 함. 이때 RacingCarGame클래스 내부에 있는 상수
  RANDOM_NUMBER_MAX_BOUND와 RANDOM_NUMBER_MIN_BOUND를 인자로 전달 함.
```
@ParameterizedTest
       @DisplayName("Cars들을 전진 할 수 있으면 전진시킨다.")
       public void gameStartTest() {
       RacingCarGame racingCarGame = new RacingCarGame();
       racingCarGame.init("car1,car2,car3");
       racingCarGame.gameStart();

       Cars resultCars = racingCarGame.getRacingCars();
       List<Car> cars = resultCars.getCars();

       assertThat(cars.get(0).getMovement()).isEqualTo();
       assertThat(cars.get(1).getMovement()).isEqualTo();
       assertThat(cars.get(2).getMovement()).isEqualTo();
       }

```
  __문제 상황__ :
  Cars들의 전진을 확인 하는 테스트 gameStartTest()에서 racingCaarGame.gameStart();를 호출할 때 내부의 tryMove메서드를 위한
  경계값을 주지 않으므로 랜덤 값에 대해 이를 확인하는 테스트를 짜기가 어려움.

  __생각해본 해결책__ :
    1. gameStart()에 경계값을 인자로 추가하여 gameStart(int min, int max)로 호출 함. min = 4, max = 9를 전달하면 확실히 전진함.
       --> 이렇게 하면 Controller에서 gameStart를 호출할 때도 게임에 대한 정보인 경계값을 넘겨주어야 함. Controller는 사용자의 요청만
       처리하고, 게임에 대한 정보는 모르게 하고 싶음.
    2. while(racingCarGame.getRacingCars().getCars().get(0).getMovement == 1)으로 여러 번 gameStart를 호출함.
       --> 종료조건이...
       


