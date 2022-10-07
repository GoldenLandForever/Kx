<template>
  <div class="playground">
    <div class="jianbian">
      <!-- <el-card>
      
      </el-card> -->
      <router-view></router-view>
      <h1>欢迎您来到我的游戏，{{$store.state.user.username}}</h1>
      <!-- 内容区域 -->

    </div>
    <div class="inner-container" v-if="flag == 1">
      <p class="text" v-for="(text, index) in arr" :key="index">{{text}}</p>
    </div>
  </div>
  </template>
  
  
  <script >
  export default {
    setup(){
      let flag = 0;
      return{
        flag,
      }
    },
    data() {
  return {
    arr: [
    '这里是故事背景',
    '从前有位公主被恶龙抓走了',
    '众所周知，柯逍老师教出来的学生个个身手不凡',
    '于是你被推荐去讨伐恶龙',
    '但是你根本就打不过恶龙',
    '好在恶龙乐于下逍遥骰',
    '只要你能够在逍遥骰中赢过恶龙',
    '公主就会被恶龙释放',
    '但是恶龙只愿意与强者下逍遥骰', 
    '而且国王会赠予救出公主的人福州大学的保研资格和一块美味的草莓味蛋糕', 
    '因此为了美味的草莓蛋糕而去与恶龙下棋的人络绎不绝',
    '现在你需要战胜其他人，登上排行榜前列，获得与恶龙对战的机会',
    '祝你好运',
    ],
  }
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
  .inner-container {
  animation: myMove 50s forwards;
  animation-fill-mode: forwards;
  width: 90%;
    height: 70vh;
    margin: 40px auto;
}
  .text{
    color: rgb(255 -176, 255 -177, 255 -171);
    background-color: rgba(239, 241, 238, 0.5);
    font-size: 20px;
    font-weight: 600;
    font-style: italic;
    text-align: center;
  }
  /*文字无缝滚动*/
@keyframes myMove {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-2000px);
  }
  
}
div.playground {
    width: 90%;
    height: 70vh;
    margin: 40px auto;
}
  </style>