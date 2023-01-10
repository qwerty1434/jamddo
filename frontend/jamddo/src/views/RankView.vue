<template>
  <div>
    랭킹페이지
    {{ RankingData }}
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
