<template>
  <div>
    <section>
      <input type="radio" v-model="viewAll" value="true" />모두 보기
      <input type="radio" v-model="viewAll" value="false" />당첨 내용만 보기
    </section>

    <div v-for="(item, index) in this.data" v-bind:key="index">
      <div v-if="index == 0 && item.rank == -1 && viewAll == 'false'">
        당첨내용 없음
      </div>
      <div v-else-if="item.rank == -1 && viewAll == 'false'"></div>
      <div v-else>
        <BuybundleDataComp :data="item" />
      </div>
    </div>
  </div>
</template>

<script>
import BuybundleDataComp from "@/components/BuybundleDataComp";

export default {
  name: "buybundle-comp",
  components: { BuybundleDataComp },
  data() {
    return {
      viewAll: "true",
    };
  },
  props: {
    data: {
      type: Array,
    },
  },
  methods: {
    changeviewAll() {
      this.viewAll = !this.viewAll;
    },
  },
};
</script>
