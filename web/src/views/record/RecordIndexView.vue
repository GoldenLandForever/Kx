<template>
  <div class="playground">
  <div class="row">
      <div class="col-1">
          <button type="button" class="btn btn-light">
              <img class="point" src="@/assets/0.png" alt="" v-if="pointA == 0" width="70" height="70">
              <img class="point" src="@/assets/1.png" alt="" v-if="pointA == 1" width="70" height="70">
              <img class="point" src="@/assets/2.png" alt="" v-if="pointA == 2" width="70" height="70">
              <img class="point" src="@/assets/3.png" alt="" v-if="pointA == 3" width="70" height="70">
              <img class="point" src="@/assets/4.png" alt="" v-if="pointA == 4" width="70" height="70">
              <img class="point" src="@/assets/5.png" alt="" v-if="pointA == 5" width="70" height="70">
              <img class="point" src="@/assets/6.png" alt="" v-if="pointA == 6" width="70" height="70">
          </button>    
          <div class="user-photo">
              <img :src="$store.state.user.photo" alt="">
          </div>
          <div class="result-board-text" >
              {{$store.state.user.username}}
          </div>
      </div>
      <div class="col-3">
          <button v-for="(item,index) in $store.state.pk.aMap" :key="index" :value="index" @click="fillA(index)">
              <img class="point" src="@/assets/0.png" alt="" v-if="item == 0" width="70" height="70">
              <img class="point" src="@/assets/1.png" alt="" v-if="item == 1" width="70" height="70">
              <img class="point" src="@/assets/2.png" alt="" v-if="item == 2" width="70" height="70">
              <img class="point" src="@/assets/3.png" alt="" v-if="item == 3" width="70" height="70">
              <img class="point" src="@/assets/4.png" alt="" v-if="item == 4" width="70" height="70">
              <img class="point" src="@/assets/5.png" alt="" v-if="item == 5" width="70" height="70">
              <img class="point" src="@/assets/6.png" alt="" v-if="item == 6" width="70" height="70">
          </button>
      </div>
      <div class="col-4">
          <div class="mid" >
              <button type="button" class="btn btn-primary" @click="click_match_btn" v-if="match_btn_info =='新手教学'">
                  <div color="white" size="9">{{match_btn_info}}</div>
              </button>     
              <button type="button" class="btn btn-warning" @click="click_match_btn" v-else><div size="5">{{match_btn_info}}</div></button>     
          </div>
      </div>
      <div class="col-3">
          <button v-for="(item,index) in $store.state.pk.bMap" :key="index" :value="index">
              <img class="point" src="@/assets/0.png" alt="" v-if="item == 0" width="70" height="70">
              <img class="point" src="@/assets/1.png" alt="" v-if="item == 1" width="70" height="70">
              <img class="point" src="@/assets/2.png" alt="" v-if="item == 2" width="70" height="70">
              <img class="point" src="@/assets/3.png" alt="" v-if="item == 3" width="70" height="70">
              <img class="point" src="@/assets/4.png" alt="" v-if="item == 4" width="70" height="70">
              <img class="point" src="@/assets/5.png" alt="" v-if="item == 5" width="70" height="70">
              <img class="point" src="@/assets/6.png" alt="" v-if="item == 6" width="70" height="70">
          </button>
      </div>   
      <div class="col-1" style="overflow: hidden; text-overflow:ellipsis; white-space: nowrap;">
          <button type="button" class="btn btn-light">
              <img class="point" src="@/assets/0.png" alt="" v-if="pointB == 0" width="70" height="70">
              <img class="point" src="@/assets/1.png" alt="" v-if="pointB == 1" width="70" height="70">
              <img class="point" src="@/assets/2.png" alt="" v-if="pointB == 2" width="70" height="70">
              <img class="point" src="@/assets/3.png" alt="" v-if="pointB == 3" width="70" height="70">
              <img class="point" src="@/assets/4.png" alt="" v-if="pointB == 4" width="70" height="70">
              <img class="point" src="@/assets/5.png" alt="" v-if="pointB == 5" width="70" height="70">
              <img class="point" src="@/assets/6.png" alt="" v-if="pointB == 6" width="70" height="70">
          </button> 
          <div class="user-photo">
              <img src="https://img-blog.csdnimg.cn/5b13c6b34fa349f79aa9abca0fea691d.jpeg" alt="">
          </div>
          <div class="result-board-text" >
              <font color="white" size="5">
                  普通僵尸
              </font>
          </div>   
      </div>
  </div>
  <div class="result-board" v-if="$store.state.pk.loser !== 'none'">
      <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
          平局
      </div>
      <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A'">
          普通僵尸获胜
      </div>
      <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' ">
          {{$store.state.user.username}}获胜
      </div>
      <div class="row">
          <div class="result-board-text" >
              {{$store.state.pk.a_score}} VS {{$store.state.pk.b_score}}
          </div>
          
      </div>
      
      <div class="result-board-btn">
          <button @click="restart" type="button" class="btn btn-primary btn">
              再来一局
          </button>
      </div>
  </div> 
</div>
</template>

<script>
import { useStore } from 'vuex';
import { ref } from 'vue';
import { onUnmounted } from 'vue'
import $ from 'jquery'
export default{
setup() {
  
  const store = useStore();
  let match_btn_info = ref('新手教学');
  let pointA = ref();
  let is_fill = ref(true);
  let pointB = ref();
  let turn = ref('A');
  let step = ref(0);
  const click_match_btn = () =>{
      if(match_btn_info.value === "新手教学"){
          step.value ++;
          match_btn_info.value = "投掷骰子";
          if(step.value == 1){
            setTimeout(() => alert("点击下方投掷按钮即可投掷骰子"), 200);
          }
      }else{
          if(is_fill.value == true){
              is_fill.value = false;
              if(turn.value == 'A'){
                  turn.value = 'B'; 
                  pointB.value = '';
                  pointA.value = Math.floor(Math.random() * 6) + 1;
                  step.value ++;
                  if(step.value == 2){
                    setTimeout(() => alert("点击左边九宫格即可以填入数字，注意无法填在对手九宫格中哦！"), 200);
                    step.value ++;
                  }
                  console.log(step);
                  if(step.value == 5){
                    setTimeout(() => alert("玩家的某线得分 = 各点数 X 此点数在此线出现次数的平方的总和 "),200);
                    setTimeout(() => alert("各玩家得分 = 己区各线的得分的总和 "),400);
                  }
              }else{
                  alert("还没到你的回合,不过我想你看不到这条信息，没人手速那么快吧");
              }
          }else{
              alert("请选择落子位置");
          }

      }   
  }

  const check_match_end = () => {
      for (let i = 0; i < 9; i++) {
          if(store.state.pk.aMap[i] == 0){
              break;
          }
          if(i == 8){
              return true;
          }
      }
      for (let i = 0; i < 9; i++) {
          if(store.state.pk.bMap[i] == 0){
              break;
          }
          if(i == 8){
              return true;
          }
      }
      return false;
  }

  const check_delete = (index) => {
      if(turn.value == 'B'){
          if(index <= 2){
              for(let i =0; i <= 2;i ++){
                  if(store.state.pk.bMap[i] == pointA.value){
                      store.state.pk.bMap[i] = 0;
                  }
              }
          }else if(index <= 5){
              for(let i = 3; i <= 5;i ++){
                  if(store.state.pk.bMap[i] == pointA.value){
                      store.state.pk.bMap[i] = 0;
                  }
              }
          }else{
              for(let i = 6; i <= 8;i ++){
                  if(store.state.pk.bMap[i] == pointA.value){
                      store.state.pk.bMap[i] = 0;
                  }
              }      
          }
      }else{
          if(index <= 2){
              for(let i =0; i <= 2;i ++){
                  if(store.state.pk.aMap[i] == pointB.value){
                      store.state.pk.aMap[i] = 0;
                  }
              }
          }else if(index <= 5){
              for(let i = 3; i <= 5;i ++){
                  if(store.state.pk.aMap[i] == pointB.value){
                      store.state.pk.aMap[i] = 0;
                  }
              }
          }else{
              for(let i = 6; i <= 8;i ++){
                  if(store.state.pk.aMap[i] == pointB.value){
                      store.state.pk.aMap[i] = 0;
                  }
              }      
          }
      }
  }
  const fillA = (index) => {
      if(is_fill.value == false){
          if(turn.value == 'B'){
              if(store.state.pk.aMap[index] == 0){
                  store.state.pk.aMap[index] = pointA.value;
                  is_fill.value = true;
                  check_delete(index);
                  if(step.value == 3)
                    setTimeout(() => alert("做的好，你成功的填入一颗骰子"), 200);
                  console.log(step.value);
                  if(step.value == 5){
                    setTimeout(() => alert("想必你现在已经了解了学习规则，你可以选择完成这次比赛或者前往匹配"),400);
                    setTimeout(() => alert("祝你好运"),400);
                  }
                  turn.value = 'A'; 
                  pointA.value = '';
                  pointB.value = Math.floor(Math.random() * 6) + 1;
                  $.ajax({
                      url: "http://localhost:3000/pk/bot/",
                      type: "post",
                      traditional:true,
                      data:{
                          aMap: store.state.pk.aMap,
                          bMap:store.state.pk.bMap,
                          point:pointB.value,
                      },
                      success(resp) {
                          store.state.pk.bMap[resp] = pointB.value;
                          check_delete(resp);
                          if(check_match_end()){
                              game_result();
                              alert("游戏结束");
                          }
                      },
                      error(resp) {
                          console.log(resp);
                      }
                  });
                  if(step.value == 3)
                    setTimeout(() => alert("在己方某线置骰后，对方该线同点数骰将被消除，对手同理也能"), 200);
                  if(step.value == 3){
                    setTimeout(() => alert("轮到你的回合，请再投掷一颗骰子"), 1000);
                    step.value ++;
                  }
              }else{
                  alert("不能重复落子");
              }
              
          }else{
              alert("请按引导操作");
          }
      }else{
          alert("请按引导操作");
      }
      if(check_match_end()){
          game_result();
          alert("游戏结束");
      }
  }
  const game_result = () => {
      let a_score = 0;
      let b_score = 0;
      let aMap = store.state.pk.aMap;
      let bMap = store.state.pk.bMap;
      for (let i = 0; i < 3; i++) {
          let a = aMap[i * 3];
          let b = aMap[i * 3 + 1];
          let c = aMap[i * 3 + 2];

          if (a == b && b == c) a_score += a * 9;
          else if (a == b) a_score += a * 4 + c;
          else if (a == c) a_score += a * 4 + b;
          else if (b == c) a_score += b * 4 + a;
          else a_score += (a + b + c);
      }
      for (let i = 0; i < 3; i++) {
          let a = bMap[i * 3];
          let b = bMap[i * 3 + 1];
          let c = bMap[i * 3 + 2];

          if (a == b && b == c) b_score += a * 9;
          else if (a == b) b_score += a * 4 + c;
          else if (a == c) b_score += a * 4 + b;
          else if (b == c) b_score += b * 4 + a;
          else b_score += (a + b + c);
      }
      store.state.pk.a_score = a_score;
      store.state.pk.b_score = b_score;
      if(store.state.pk.a_score > store.state.pk.b_score){
          store.commit("updateLoser", "B");
      }else if(store.state.pk.a_score == store.state.pk.b_score){
          store.commit("updateLoser", "all");
      }else{
          store.commit("updateLoser", "A");
      }
      console.log(store.state.pk.loser);
  }
  const restart = () => {
      pointA.value = '';
      pointA.value = '';
      store.commit("clearMap");
      store.commit("updateStatus", "matching");
      store.commit("updateLoser", "none");
      store.commit("updateModel","none");
  }
  onUnmounted(() => {
      store.commit("updateModel","none");
      store.commit("clearMap");
  })

  return {
      click_match_btn,
      match_btn_info,
      fillA,
      pointA,
      pointB,
      is_fill,
      check_match_end,
      check_delete,
      game_result,
      restart,
      step,
  }

}
}
</script>

<style>
button {
width: 85px;
height: 85px;
border-radius: 25%;
}
div.mid{
width: 100px;
height: 50px;
margin: 2vh auto;
padding-top: 40vh;
padding-right: 60%;
}
div.result-board {
height: 50%;
width: 50%;
background-color: rgba(50, 50, 50, 0.5);
position: absolute;
top: 17.8vh;
left: 24.5vw;
}
div.result-board-text {
text-align: center;
color: white;
font-size: 50px;
font-weight: 600;
font-style: italic;
padding-top: 5vh;
}

div.result-board-btn {
padding-top: 7vh;
text-align: center;
}
div.user-photo {
  text-align: center;
  padding-top: 5vh;
}
div.result-board-btn > button{
  width: 300px;
  font-size: 35px;
  font-weight: 500;
  font-style: italic;
}
div.mid > button {
  width: 200px;
  font-size: 35px;
  font-weight: 500;
  font-style: italic;
}
div.playground {
    width: 90%;
    height: 70vh;
    margin: 40px auto;
}
</style>