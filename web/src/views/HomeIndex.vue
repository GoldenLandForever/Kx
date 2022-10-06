<template>
    <div class="jianbian">
      <!-- <el-card>
      
      </el-card> -->
      <router-view></router-view>
      <h1>欢迎您来到我的游戏，{{$store.state.user.username}}</h1>
      <!-- 内容区域 -->

    </div>
  </template>
  
  
  <script >
  export default {
    setup(){

    },
    data() {
      return {};
    },
    created() {},
    mounted() {},
    methods: {},

  };
  </script>
  
  <style scoped>
  h1 {
    overflow: hidden;
    font-size: 50px;
    font-style: italic;
    width: 0;
    height: 150px;
    line-height: 150px;
    margin: 100px auto;
    color: rgba(20, 223, 139);
    /* background: lightcoral; */
    white-space: nowrap;
    animation: w 2.5s steps(13) forwards;
    text-align: center;
  }
  @keyframes w {
    0% {
      width: 0;
    }
    50% {
      width: 1000px;
    }
    100% {
      width: 0;
    }
  }
  
  /* .tupian-img {
    width: 1100px;
    height: 500px;
    /* margin-top:20px; 
  } */
  /* .el-card {
    width: 100%;
    height: 500px;
  } */
  .jianbian {
    height: 500px;
    width: 100%;
    /* margin: 0;
              padding: 0; */
    font-family: "montserrat";
    background-image: linear-gradient(
      45deg,
    );
    background-size: 400%;
    margin-top: -100px;
  }
  </style>