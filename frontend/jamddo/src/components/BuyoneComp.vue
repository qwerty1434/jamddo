<template>
  <div>
    <ProfileComp />
    <div>
      <div class="outer">
        <div
          :class="[
            ballColor(data.lottoDto.firstNum),
            isWonNum(data.lottoDto.firstNum),
          ]"
          class="ball"
        >
          {{ data.lottoDto.firstNum }}
        </div>
        <div
          :class="[
            ballColor(data.lottoDto.secondNum),
            isWonNum(data.lottoDto.secondNum),
          ]"
          class="ball"
        >
          {{ data.lottoDto.secondNum }}
        </div>
        <div
          :class="[
            ballColor(data.lottoDto.thirdNum),
            isWonNum(data.lottoDto.thirdNum),
          ]"
          class="ball"
        >
          {{ data.lottoDto.thirdNum }}
        </div>
        <div
          :class="[
            ballColor(data.lottoDto.fourthNum),
            isWonNum(data.lottoDto.fourthNum),
          ]"
          class="ball"
        >
          {{ data.lottoDto.fourthNum }}
        </div>
        <div
          :class="[
            ballColor(data.lottoDto.fifthNum),
            isWonNum(data.lottoDto.fifthNum),
          ]"
          class="ball"
        >
          {{ data.lottoDto.fifthNum }}
        </div>
        <div
          :class="[
            ballColor(data.lottoDto.sixthNum),
            isWonNum(data.lottoDto.sixthNum),
          ]"
          class="ball"
        >
          {{ data.lottoDto.sixthNum }}
        </div>
      </div>
    </div>
    <div v-if="data.rank == -1">낙점</div>
    <div v-else-if="data.rank >= 2">등수: {{ data.rank }}등</div>
    <div v-else>등수: {{ data.rank }}등</div>
    <div v-if="isFirst == true">
      <div>
        <h4>1등입니다</h4>
        <p>와 1등!!</p>
      </div>
    </div>

    <div>상금: {{ data.myPrize | numFilter }}원</div>
  </div>
</template>

<script>
import ProfileComp from "@/components/ProfileComp";
export default {
  name: "buyone-comp",
  components: { ProfileComp },
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
    winningNumOnly: {
      type: Object,
    },
  },
  watch: {
    data() {
      if (this.data.rank == 1) {
        this.isFirst = true;
        alert("1등이다!!");
        // alert("1등?! 오늘 로또각?");
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

    isWonNum(num) {
      console.log(this.winningNumOnly);
      for (let i = 0; i < 6; i++) {
        if (this.winningNumOnly[i] == num) return "wonNum";
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
.wonNum {
  color: black !important;
  /* https://codepen.io/maaarj/pen/YZReoK */
}
</style>
