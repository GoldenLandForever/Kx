<template>
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
            <div class="result-board-text" >
                1P
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
            <div class="mid">
                <button type="button" class="btn btn-primary" @click="click_match_btn" v-if="match_btn_info =='????????????'">
                    <div color="white" size="2">{{match_btn_info}}</div>
                </button>     
                <button type="button" class="btn btn-warning" @click="click_match_btn" v-else><div size="2">{{match_btn_info}}</div></button>     
            </div>
        </div>
        <div class="col-3">
            <button v-for="(item,index) in $store.state.pk.bMap" :key="index" :value="index" @click="fillB(index)">
                <img class="point" src="@/assets/0.png" alt="" v-if="item == 0" width="70" height="70">
                <img class="point" src="@/assets/1.png" alt="" v-if="item == 1" width="70" height="70">
                <img class="point" src="@/assets/2.png" alt="" v-if="item == 2" width="70" height="70">
                <img class="point" src="@/assets/3.png" alt="" v-if="item == 3" width="70" height="70">
                <img class="point" src="@/assets/4.png" alt="" v-if="item == 4" width="70" height="70">
                <img class="point" src="@/assets/5.png" alt="" v-if="item == 5" width="70" height="70">
                <img class="point" src="@/assets/6.png" alt="" v-if="item == 6" width="70" height="70">
            </button>
        </div>   
        <div class="col-1">
            <button type="button" class="btn btn-light">
                <img class="point" src="@/assets/0.png" alt="" v-if="pointB == 0" width="70" height="70">
                <img class="point" src="@/assets/1.png" alt="" v-if="pointB == 1" width="70" height="70">
                <img class="point" src="@/assets/2.png" alt="" v-if="pointB == 2" width="70" height="70">
                <img class="point" src="@/assets/3.png" alt="" v-if="pointB == 3" width="70" height="70">
                <img class="point" src="@/assets/4.png" alt="" v-if="pointB == 4" width="70" height="70">
                <img class="point" src="@/assets/5.png" alt="" v-if="pointB == 5" width="70" height="70">
                <img class="point" src="@/assets/6.png" alt="" v-if="pointB == 6" width="70" height="70">
            </button> 
            <div class="result-board-text" >
                2P
            </div>   
        </div>
    </div>
    <div class="result-board" v-if="$store.state.pk.loser !== 'none'">
        <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
            ??????
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A'">
            2P??????
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' ">
            1P??????
        </div>
        <div class="row">
            <div class="result-board-text" >
                {{$store.state.pk.a_score}} VS {{$store.state.pk.b_score}}
            </div>
            
        </div>
        
        <div class="result-board-btn">
            <button @click="restart" type="button" class="btn btn-primary btn">
                ????????????
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
    console.log();
    let match_btn_info = ref('????????????');
    let pointA = ref();
    let is_fill = ref(true);
    let pointB = ref();
    let turn = ref('A');
    const click_match_btn = () =>{
        if(match_btn_info.value === "????????????"){
            match_btn_info.value = "????????????";
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
                alert("?????????????????????");
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
                    alert("??????????????????");
                }
                
            }else{
                alert("???????????????????????????");
            }
        }else{
            alert("??????????????????");
        }
        if(check_match_end()){
            game_result();
            alert("????????????");
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
                    alert("??????????????????");
                }
            }else{
                alert("???????????????????????????");
            }
        }else{
            alert("??????????????????");
        }
        if(check_match_end()){
            game_result();
            alert("????????????");
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
    }
    const restart = () => {
        pointA.value = '';
        pointA.value = '';
        store.commit("clearMap");
        store.commit("updateModel","none");
        store.commit("updateStatus", "matching");
        store.commit("updateLoser", "none");
    }
    onUnmounted(() => {
        store.commit("updateModel","none");
        store.commit("clearMap");
        store.commit("updateLoser", "none");
    })

    return {
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
background-color: rgba(245,245,245);
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

</style>