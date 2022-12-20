<template>
  <div>
    <div>등수 {{ data.rank }}</div>
    <div>내 상금 {{ data.myPrize }}</div>
    <div>
      내 로또번호
      <div>
        <div class="box">{{ data.lottoDto.firstNum }}</div>
        <div class="box">{{ data.lottoDto.secondNum }}</div>
        <div class="box">{{ data.lottoDto.thirdNum }}</div>
        <div class="box">{{ data.lottoDto.fourthNum }}</div>
        <div class="box">{{ data.lottoDto.fifthNum }}</div>
        <div class="box">{{ data.lottoDto.sixthNum }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "buyone-comp",
  props: {
    data: {
      type: Object,
      default: () => {
        return {
          rank: "",
          winningPrize: "",
          beneficiaryNum: "",
          myPrize: "",
          lottoDto: Object,
        };
      },
    },
  },
};
</script>
<style>
.box {
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
  background-color: red;
  float: left;
}
</style>
