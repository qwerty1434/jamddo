<template>
  <div>
    <div v-if="data.rank == -1">낙점</div>
    <div v-else-if="data.rank >= 2">등수: {{ data.rank }}등</div>
    <div v-else>등수: {{ data.rank }}등</div>
    <div v-if="isFirst == true">
      <div>
        <h4>1등입니다</h4>
        <p>와 1등!!</p>
      </div>
    </div>

    <div>상금: {{ data.myPrize | moneyFilter }}원</div>
    <div>
      내 로또번호
      <div class="outer">
        <div :class="ballColor(data.lottoDto.firstNum)" class="ball">
          {{ data.lottoDto.firstNum }}
        </div>
        <div :class="ballColor(data.lottoDto.secondNum)" class="ball">
          {{ data.lottoDto.secondNum }}
        </div>
        <div :class="ballColor(data.lottoDto.thirdNum)" class="ball">
          {{ data.lottoDto.thirdNum }}
        </div>
        <div :class="ballColor(data.lottoDto.fourthNum)" class="ball">
          {{ data.lottoDto.fourthNum }}
        </div>
        <div :class="ballColor(data.lottoDto.fifthNum)" class="ball">
          {{ data.lottoDto.fifthNum }}
        </div>
        <div :class="ballColor(data.lottoDto.sixthNum)" class="ball">
          {{ data.lottoDto.sixthNum }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "buyone-comp",
  data() {
    return {
      isFirst: false,
    };
  },
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
  watch: {
    data() {
      if (this.data.rank == 1) {
        this.isFirst = true;
        alert("1등이다!!");
      } else {
        this.isFirst = false;
      }
    },
  },
  methods: {
    ballColor(num) {
      if (num <= 10) {
        return "yellowBall";
      } else if (11 <= num && num <= 20) {
        return "blueBall";
      } else if (21 <= num && num <= 30) {
        return "redBall";
      } else if (31 <= num && num <= 40) {
        return "greyBall";
      } else if (41 <= num && num <= 45) {
        return "greenBall";
      }
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
