<template>
  <div>
    <div>
      <div class="outer">
        <div :class="ballColor(data.firstNum)" class="ball">
          {{ data.firstNum }}
        </div>
        <div :class="ballColor(data.secondNum)" class="ball">
          {{ data.secondNum }}
        </div>
        <div :class="ballColor(data.thirdNum)" class="ball">
          {{ data.thirdNum }}
        </div>
        <div :class="ballColor(data.fourthNum)" class="ball">
          {{ data.fourthNum }}
        </div>
        <div :class="ballColor(data.fifthNum)" class="ball">
          {{ data.fifthNum }}
        </div>
        <div :class="ballColor(data.sixthNum)" class="ball">
          {{ data.sixthNum }}
        </div>
        +
        <div :class="ballColor(data.bonusNum)" class="ball">
          {{ data.bonusNum }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "winning-num-comp",
  props: {
    data: {},
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
