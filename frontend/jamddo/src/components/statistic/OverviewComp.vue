<template>
  <div>
    <div class="box">
      <table class="overviewTable">
        <tr>
          <td>등수</td>
          <td>당첨자 수</td>
          <td>금액</td>
        </tr>
        <tr>
          <td>1등</td>
          <td>{{ data.firstPrizeBeneficiaryNum }}</td>
          <td>{{ data.firstPrize | numFilter }}</td>
        </tr>
        <tr>
          <td>2등</td>
          <td>{{ data.secondPrizeBeneficiaryNum }}</td>
          <td>{{ data.secondPrize | numFilter }}</td>
        </tr>
        <tr>
          <td>3등</td>
          <td>{{ data.thirdPrizeBeneficiaryNum | numFilter }}</td>
          <td>{{ data.thirdPrize | numFilter }}</td>
        </tr>
        <tr>
          <td>4등</td>
          <td>{{ data.fourthPrizeBeneficiaryNum | numFilter }}</td>
          <td>{{ data.fourthPrize | numFilter }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "overview-comp",
  data() {
    return {};
  },
  props: {
    data: {
      round: "",
      firstPrize: "",
      secondPrize: "",
      thirdPrize: "",
      fourthPrize: "",
      firstPrizeBeneficiaryNum: "",
      secondPrizeBeneficiaryNum: "",
      thirdPrizeBeneficiaryNum: "",
      fourthPrizeBeneficiaryNum: "",
    },
  },
};
</script>
<style>
.recordTable {
  margin-left: auto;
  margin-right: auto;
}
.box {
  display: inline-block;
}
td {
  padding-right: 50px;
}
</style>
