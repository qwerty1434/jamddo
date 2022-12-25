<template>
  <div>
    <div
      class="set"
      v-for="(item, index) in this.data.statisticResult"
      v-bind:key="index"
    >
      <div :class="ballColor(index)" class="ball">
        {{ index }}
      </div>
      <div>{{ item }} 회</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "num-statistic-data-comp",
  data() {},
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
.outer {
  display: inline-block;
  text-align: center;
  margin: 5px;
}
.set {
  display: inline-block;
}
</style>
