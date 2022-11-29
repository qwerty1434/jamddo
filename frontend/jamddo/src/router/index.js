import Vue from "vue";
import VueRouter from "vue-router";
import SimulationView from "../views/SimulationView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: SimulationView,
  },
  {
    path: "/statistic",
    name: "statistic",
    component: () => import("../views/StatisticView.vue"), // 이렇게도 가능하고 위에꺼처럼도 가능한거 같음. 약간 동적로딩처럼 사용시점에 가져오는 느낌인듯.
  },
];

const router = new VueRouter({
  routes,
});

export default router;
