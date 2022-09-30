<template>
        <div class="row">
            <div class="col-1">
                <button type="button" class="btn btn-light">{{pointA}}</button>    
            </div>
            <div class="col-3">
                <button v-for="(item,index) in arr" :key="index"></button>
            </div>
            <div class="col-4">
                <div class="mid">
                    <button type="button" class="btn btn-primary" @click="click_match_btn" >{{match_btn_info}}</button>        
                </div>
            </div>
            <div class="col-3">
                <button v-for="(item,index) in arr" :key="index"></button>
            </div>   
            <div class="col-1">
                <button type="button" class="btn btn-light">{{pointB}}</button>    
            </div>
        </div>
</template>

<script>
import { ref } from 'vue';
export default{
    setup() {
        const arr = ref([0,1,2,3,4,5,6,7,8]);
        let match_btn_info = ref('开始游戏');
        let pointA = ref();
        let pointB = ref();
        let turn = ref('A');
        

        const click_match_btn = () =>{
            if(match_btn_info.value === "开始游戏"){
                match_btn_info.value = "投掷骰子";
            }else{
                if(turn.value == 'A'){
                    turn.value = 'B'; 
                    pointB.value = '';
                    pointA.value = Math.floor(Math.random() * 6) + 1;
                }else{
                    pointB.value = Math.floor(Math.random() * 6) + 1;
                    pointA.value = '';
                    turn.value = 'A';
                }

            }   
        }
        
        return {
            arr,
            click_match_btn,
            match_btn_info,
            pointA,
            pointB,
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