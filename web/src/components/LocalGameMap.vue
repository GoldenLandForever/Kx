<template>
    <div class="row">
        <div class="col-1">
            <button type="button" class="btn btn-light">{{pointA}}</button>    
            <div class="result-board-text" >
                1P
            </div>
        </div>
        <div class="col-3">
            <button v-for="(item,index) in $store.state.pk.aMap" :key="index" :value="index" @click="fillA(index)">
                {{item}}
            </button>
        </div>
        <div class="col-4">
            <div class="mid">
                <button type="button" class="btn btn-primary" @click="click_match_btn" v-if="match_btn_info =='开始游戏'">
                    <div color="white" size="2">{{match_btn_info}}</div>
                </button>     
                <button type="button" class="btn btn-warning" @click="click_match_btn" v-else><div size="2">{{match_btn_info}}</div></button>     
            </div>
        </div>
        <div class="col-3">
            <button v-for="(item,index) in $store.state.pk.bMap" :key="index" :value="index" @click="fillB(index)">
                {{item}}
            </button>
        </div>   
        <div class="col-1">
            <button type="button" class="btn btn-light">{{pointB}}</button> 
            <div class="result-board-text" >
                2P
            </div>   
        </div>
    </div>
    <div class="result-board" v-if="$store.state.pk.loser !== 'none'">
        <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
            平局
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A'">
            2P获胜
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' ">
            1P获胜
        </div>
        <div class="row">
            <div class="result-board-text" >
                {{$store.state.pk.a_score}} VS {{$store.state.pk.b_score}}
            </div>
            
        </div>
        
        <div class="result-board-btn">
            <button @click="restart" type="button" class="btn btn-warning btn">
                再来一局
            </button>
        </div>
    </div> 
</template>

<script>
import { useStore } from 'vuex';
import { ref } from 'vue';
import { onUnmounted } from 'vue'
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
        console.log(111);
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
                }else{
                    alert("不能重复落子");
                }
                
            }else{
                alert("不能放置于对手棋盘");
            }
        }else{
            alert("轮到对手掷筛");
        }
        if(check_match_end()){
            game_result();
            alert("游戏结束");
        }
    }
    const fillB = (index) => {
        if(is_fill.value == false){
            if(turn.value == 'A'){
                if(store.state.pk.bMap[index] == 0){
                    is_fill.value = true;
                    store.state.pk.bMap[index] = pointB.value;
                    check_delete(index);
                    
                }else{
                    alert("不能重复落子");
                }
            }else{
                alert("不能放置于对手棋盘");
            }
        }else{
            alert("轮到对手掷筛");
        }
        if(check_match_end()){
            game_result();
            alert("游戏结束");
        }
    }
    const game_result = () => {
        for(let i = 0;i < 9; i ++){
            store.state.pk.a_score += store.state.pk.aMap[i];
            store.state.pk.b_score += store.state.pk.bMap[i];
        }
        if(store.state.pk.a_score > store.state.pk.b_score){
            store.commit("updateLoser", "B");
        }else if(store.state.pk.a_score == store.state.pk.b_score){
            store.commit("updateLoser", "all");
        }else{
            store.commit("updateLoser", "A");
        }
        console.log(store.state.pk.loser);
        console.log(store.state.pk.a_score);
    }
    const restart = () => {
        pointA.value = '';
        pointA.value = '';
        store.commit("clearMap");
        store.commit("updateStatus", "matching");
        store.commit("updateLoser", "none");
    }
    onUnmounted(() => {
        store.commit("updateModel","none");
        store.commit("clearMap");
    })

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
        check_delete,
        game_result,
        restart,
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

</style>