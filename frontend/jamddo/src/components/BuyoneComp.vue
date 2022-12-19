<template>
  <div>
    <div>등수 {{ data.rank }}</div>
    <div>내 상금 {{ data.myPrize }}</div>
    <div>
      내 로또번호
      {{ data.lottoDto }}
    </div>
  </div>
</template>

<script>
export default {
  name: "buyone-comp",
  props: {
    data: {
      type: Object,
      default: () => {
        return {
          rank: "",
          winningPrize: "",
          beneficiaryNum: "",
          myPrize: "",
          lottoDto: "",
        };
      },
    },
  },
};
</script>
