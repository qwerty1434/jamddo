<template>
  <div id="app">
    <div class="wrapper">
      로고 넣고싶어
      <nav>
        <router-link to="/">게임하기</router-link> |
        <router-link to="/rank">랭킹보기</router-link> |
        <router-link to="/statistic">로또정보</router-link>
      </nav>
      <router-view />
    </div>
    <FooterComp />
  </div>
</template>
<script>
import FooterComp from "@/components/FooterComp";
export default {
  components: { FooterComp },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

/* footer를 바닥에 고정시키기 위한 코드 */
html,
body,
#app {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}
.wrapper {
  height: auto;
  min-height: 100%;
  padding-bottom: 15px;
}
</style>
