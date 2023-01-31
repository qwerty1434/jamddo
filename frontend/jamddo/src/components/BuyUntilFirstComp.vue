<template>
  <div>
    <div>총 진행한 게임: {{ data.cnt | numFilter }}회</div>
    <div>1등 당첨될려고 사용한 돈: {{ data.money | numFilter }}원</div>
    <div>1등이 당첨되기까지 다른 당첨내역</div>
    <div>
      2등:
      {{ data.notFirstButPrize[0] | numFilter }}회 3등:
      {{ data.notFirstButPrize[1] | numFilter }}회 4등:
      {{ data.notFirstButPrize[2] | numFilter }}회 5등:
      {{ data.notFirstButPrize[3] | numFilter }}회
    </div>
    <div>손익: {{ data.profit | numFilter }}원</div>
  </div>
</template>

<script>
export default {
  name: "buy-until-first-comp",
  data() {
    return {};
  },
  props: {
    data: {
      type: Object,
      default: () => {
        return {
          cnt: "",
          money: "",
          notFirstButPrize: Array,
          profit: "",
          lottoDto: Object,
        };
      },
    },
  },
};
</script>
<style>
.ball {
  width: 50px;
  height: 50px;
  border-radius: 70%;
  color: white;
  margin: auto;
  line-height: 50px;
  display: inline-block;
  margin: 5px;
}
.yellowBall {
  background-color: #fbc400;
}
.blueBall {
  background-color: #69c8f2;
}
.redBall {
  background-color: #ff7272;
}
.greyBall {
  background-color: #aaaaaa;
}
.greenBall {
  background-color: #b0d840;
}
</style>
