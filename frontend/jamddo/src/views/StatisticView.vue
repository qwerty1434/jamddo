<template>
  <div class="home">
    <h1>로또 자료</h1>

    <h2>{{ this.WinningNumData.round }}회 당첨번호</h2>
    <WinningNumComp :data="WinningNumData" />
    <br />
    <h2>{{ this.WinningNumData.round }}회 당첨정보</h2>
    <OverviewComp :data="OverviewData" />
    <br />
    <h2>이전 당첨번호 통계</h2>
    <NumStatisticDataComp :data="NumStatisticData" />
  </div>
</template>

<script>
const addr = "http://localhost:8080/info";
import axios from "axios";
import WinningNumComp from "@/components/statistic/WinningNumComp.vue";
import OverviewComp from "@/components/statistic/OverviewComp.vue";
import NumStatisticDataComp from "@/components/statistic/NumstatisticDataComp.vue";
export default {
  name: "StatisticView",
  components: {
    WinningNumComp,
    OverviewComp,
    NumStatisticDataComp,
  },
  data() {
    return {
      WinningNumData: "",
      OverviewData: "",
      NumStatisticData: "",
    };
  },
  created() {
    axios
      .get(addr + "/winningNum")
      .then((response) => {
        this.WinningNumData = response.data;
      })
      .catch((error) => {
        alert(error.response.data);
      });
    axios
      .get(addr + "/overview")
      .then((response) => {
        this.OverviewData = response.data;
      })
      .catch((error) => {
        alert(error.response.data);
      });
    axios
      .get(addr + "/statistics/number")
      .then((response) => {
        this.NumStatisticData = response.data;
      })
      .catch((error) => {
        alert(error.response.data);
      });
  },
};
</script>
