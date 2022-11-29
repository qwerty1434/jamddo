<template>
  <div class="home">
    <h1>여기는 시뮬레이션</h1>
    <button v-on:click="func1">로또1장 구매</button>
    <div>{{ func1_data }}</div>
  </div>
</template>

<script>
const addr = "http://localhost:8080/simulation";
import axios from "axios";
export default {
  name: "HomeView",
  data() {
    return {
      func1_data: "",
      func2_data: "",
    };
  },
  methods: {
    func1: function () {
      axios
        .get(addr + "/buyOne")
        .then((response) => {
          console.log(response.data);
          this.func1_data = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
