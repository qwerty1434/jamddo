<template>
  <div>
    <h1>랭킹 정보</h1>
    <table class="rankTable">
      <tr>
        <td>랭킹</td>
        <td>아이디</td>
        <td>점수</td>
        <td>게임 횟수</td>
      </tr>
      <tr v-for="(item, index) in this.RankingData" v-bind:key="index">
        <td>{{ index + 1 }}등</td>
        <td>{{ item.nickname }}</td>
        <td>{{ item.point | numFilter }}p</td>
        <td>{{ item.cnt | numFilter }}회</td>
      </tr>
    </table>
  </div>
</template>
<script>
const addr = "http://localhost:8080/user";
import axios from "axios";
export default {
  name: "RankView",
  data() {
    return {
      RankingData: "",
    };
  },
  created() {
    axios
      .get(addr + "/ranking")
      .then((response) => {
        this.RankingData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
<style>
.rankTable {
  margin-left: auto;
  margin-right: auto;
}
</style>
