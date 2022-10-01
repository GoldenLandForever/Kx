<template>
        <div class="row">
            <div class="col-1">
                <button type="button" class="btn btn-light">{{pointA}}</button>    
            </div>
            <div class="col-3">
                <button v-for="(item,index) in $store.state.pk.aMap" :key="index" :value="index" @click="fillA(index)">
                    {{item}}
                </button>
            </div>
            <div class="col-4">
                <div class="mid">
                    <button type="button" class="btn btn-primary" @click="click_match_btn" >{{match_btn_info}}</button>        
                </div>
            </div>
            <div class="col-3">
                <button v-for="(item,index) in $store.state.pk.bMap" :key="index" :value="index" @click="fillB(index)">
                    {{item}}
                </button>
            </div>   
            <div class="col-1">
                <button type="button" class="btn btn-light">{{pointB}}</button>    
            </div>
        </div>
</template>

<script>
import { useStore } from 'vuex';
import { ref } from 'vue';
export default{
    setup() {
        const store = useStore();
        let arr = ref([0,1,2,3,4,5,6,7,8]);
        let match_btn_info = ref('开始游戏');
        let pointA = ref();
        let is_fill = ref(true);
        let pointB = ref();
        let turn = ref('A');
        

        const click_match_btn = () =>{
            if(match_btn_info.value === "开始游戏"){
                match_btn_info.value = "投掷骰子";
            }else{
                if(is_fill.value == true){
                    is_fill.value = false;
                    if(turn.value == 'A'){
                        turn.value = 'B'; 
                        pointB.value = '';
                        pointA.value = Math.floor(Math.random() * 6) + 1;
                        
                    }else{
                        pointB.value = Math.floor(Math.random() * 6) + 1;
                        pointA.value = '';
                        turn.value = 'A';
                    }
                }else{
                    alert("请选择落子位置");
                }

            }   
        }

        const check_match_end = () => {
            console.log(store.state.pk.aMap[0]);
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

        const fillA = (index) => {
            if(is_fill.value == false){
                if(turn.value == 'B'){
                    store.state.pk.aMap[index] = pointA.value;
                    is_fill.value = true;
                    if(check_match_end()){
                        alert("游戏结束");
                    }
                }else{
                    alert("不能放置于对手棋盘");
                }
            }else{
                alert("轮到对手掷筛");
            }
        }
        const fillB = (index) => {
            if(is_fill.value == false){
                if(turn.value == 'A'){
                    is_fill.value = true;
                    store.state.pk.bMap[index] = pointB.value;
                    if(check_match_end()){
                        alert("游戏结束");
                    }
                }else{
                    alert("不能放置于对手棋盘");
                }
            }else{
                alert("轮到对手掷筛");
            }

        }
        
        return {
            arr,
            click_match_btn,
            match_btn_info,
            fillA,
            fillB,
            pointA,
            pointB,
            is_fill,
            check_match_end,
        }

    }
}
</script>

<style>
button {
  width: 75px;
  height: 75px;
  border-radius: 25%;
  margin: 5px 5px 5px 5px;
}
div.mid{
    width: 100px;
  height: 50px;
  margin: 2vh auto;
  padding-top: 40vh;
}
</style>