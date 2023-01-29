<template>
  <div class="outer">
    <div class="group">
      <div
        class="set"
        v-for="(item, index) in this.data.statisticResult.slice(1, 11)"
        v-bind:key="index"
      >
        <div :class="ballColor(index)" class="ball">{{ index + 1 }}번</div>
        <div>{{ item }} 회</div>
      </div>
    </div>

    <div class="group">
      <div
        class="set"
        v-for="(item, index) in this.data.statisticResult.slice(11, 21)"
        v-bind:key="index + 11"
      >
        <div :class="ballColor(index + 11)" class="ball">
          {{ index + 11 }}번
        </div>
        <div>{{ item }} 회</div>
      </div>
    </div>

    <div class="group">
      <div
        class="set"
        v-for="(item, index) in this.data.statisticResult.slice(21, 31)"
        v-bind:key="index + 21"
      >
        <div :class="ballColor(index + 21)" class="ball">
          {{ index + 21 }}번
        </div>
        <div>{{ item }} 회</div>
      </div>
    </div>
    <div class="group">
      <div
        class="set"
        v-for="(item, index) in this.data.statisticResult.slice(31, 41)"
        v-bind:key="index + 31"
      >
        <div :class="ballColor(index + 31)" class="ball">
          {{ index + 31 }}번
        </div>
        <div>{{ item }} 회</div>
      </div>
    </div>
    <div class="group">
      <div
        class="set"
        v-for="(item, index) in this.data.statisticResult.slice(41, 46)"
        v-bind:key="index + 41"
      >
        <div :class="ballColor(index + 41)" class="ball">
          {{ index + 41 }}번
        </div>
        <div>{{ item }} 회</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "num-statistic-data-comp",
  data() {
    return {};
  },
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
  margin: 15px;
}
</style>
