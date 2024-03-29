import Vue from "vue";
import VueRouter from "vue-router";
import SimulationView from "@/views/SimulationView.vue";
import StatisticView from "@/views/StatisticView.vue";
import RankView from "@/views/RankView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "simulation",
    component: SimulationView,
  },
  {
    path: "/statistic",
    name: "statistic",
    component: StatisticView,
  },
  {
    path: "/rank",
    name: "rank",
    component: RankView,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
