<template>
    <div class="row">
        <div class="col-1">
            <button type="button" class="btn btn-light">
                <img class="point" src="@/assets/0.png" alt="" v-if="$store.state.pk.a_point == 0" width="70" height="70">
                <img class="point" src="@/assets/1.png" alt="" v-if="$store.state.pk.a_point == 1" width="70" height="70">
                <img class="point" src="@/assets/2.png" alt="" v-if="$store.state.pk.a_point == 2" width="70" height="70">
                <img class="point" src="@/assets/3.png" alt="" v-if="$store.state.pk.a_point == 3" width="70" height="70">
                <img class="point" src="@/assets/4.png" alt="" v-if="$store.state.pk.a_point == 4" width="70" height="70">
                <img class="point" src="@/assets/5.png" alt="" v-if="$store.state.pk.a_point == 5" width="70" height="70">
                <img class="point" src="@/assets/6.png" alt="" v-if="$store.state.pk.a_point == 6" width="70" height="70">
            </button>    
            
            <div class="user-photo">
                <img :src="$store.state.pk.a_photo" alt="">
            </div>
            <div class="result-board-text" v-if="$store.state.user.id == $store.state.pk.a_id">
                {{$store.state.user.username}}
            </div>
            <div class="result-board-text" v-else>
                {{$store.state.pk.opponent_username}}
            </div>
            <button v-if="$store.state.user.id == $store.state.pk.a_id && $store.state.pk.a_status == 0" @click="a_botstart" class="btn btn-success">托管</button>
            <button v-else-if="$store.state.user.id == $store.state.pk.a_id && $store.state.pk.a_status == 1" @click="a_botstart" class="btn btn-danger">取消托管</button>
            <div style="padding-top: 5vh;"> <button v-if="$store.state.user.id == $store.state.pk.a_id" @click="a_surrender" class="btn btn-danger" >认输</button></div>
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
            <div class="text-wrap">
                    <div v-if="$store.state.pk.turn === 'A' && $store.state.user.id == $store.state.pk.a_id">你的回合</div>
                </div>  
        </div>
        <div class="col-4">
            <div class="mid">   
                <button type="button" class="btn btn-warning" @click="click_match_btn" ><div size="2">投掷骰子</div></button>   
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
            <div class="text-wrap">
                    <div v-if="$store.state.pk.turn === 'B' && $store.state.user.id == $store.state.pk.b_id">你的回合</div>
                </div> 
        </div>   
        <div class="col-1">
            <button type="button" class="btn btn-light">
                <img class="point" src="@/assets/0.png" alt="" v-if="$store.state.pk.b_point == 0" width="70" height="70">
                <img class="point" src="@/assets/1.png" alt="" v-if="$store.state.pk.b_point == 1" width="70" height="70">
                <img class="point" src="@/assets/2.png" alt="" v-if="$store.state.pk.b_point == 2" width="70" height="70">
                <img class="point" src="@/assets/3.png" alt="" v-if="$store.state.pk.b_point == 3" width="70" height="70">
                <img class="point" src="@/assets/4.png" alt="" v-if="$store.state.pk.b_point == 4" width="70" height="70">
                <img class="point" src="@/assets/5.png" alt="" v-if="$store.state.pk.b_point == 5" width="70" height="70">
                <img class="point" src="@/assets/6.png" alt="" v-if="$store.state.pk.b_point == 6" width="70" height="70">
            </button> 
            <div class="user-photo">
                <img :src="$store.state.pk.b_photo" alt="">
            </div>
            <div class="result-board-text" v-if="$store.state.user.id == $store.state.pk.b_id">
                {{$store.state.user.username}}
            </div>
            <div class="result-board-text" v-else>
                {{$store.state.pk.opponent_username}}
            </div>  
            <button v-if="$store.state.user.id == $store.state.pk.b_id && $store.state.pk.b_status == 0" @click="b_botstart" class="btn btn-success">托管</button>
            <button v-else-if="$store.state.user.id == $store.state.pk.b_id && $store.state.pk.b_status == 1" @click="b_botstart" class="btn btn-danger">取消托管</button>
            <div style="padding-top: 5vh;"> <button v-if="$store.state.user.id == $store.state.pk.b_id" @click="b_surrender" class="btn btn-danger" >认输</button></div>
        </div>

    </div>
    <div class="result-board" v-if="$store.state.pk.loser !== 'none'">
        <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
            平局
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.user.id == $store.state.pk.a_id">
            {{$store.state.pk.opponent_username}}获胜
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.user.id == $store.state.pk.b_id">
            {{$store.state.user.username}}获胜
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' && $store.state.user.id == $store.state.pk.b_id">
            {{$store.state.pk.opponent_username}}获胜
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B'&& $store.state.user.id == $store.state.pk.a_id">
            {{$store.state.user.username}}获胜
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
import { onUnmounted } from 'vue'
export default{
setup() {
    const store = useStore();
    const socket = store.state.pk.socket;
    const a_botstart = () =>{
        if(store.state.pk.a_status == 0) store.state.pk.a_status = 1;
        else store.state.pk.a_status = 0;
        socket.send(JSON.stringify({
            event: "botA"
        }));
    }
    const b_botstart = () =>{
        if(store.state.pk.b_status == 0) store.state.pk.b_status = 1;
        else store.state.pk.b_status = 0;
        socket.send(JSON.stringify({
            event: "botB"
        }));
    }
    const a_surrender = () => {
        socket.send(JSON.stringify({
            event: "surrenderA"
        }));
    }
    const b_surrender = () => {
        socket.send(JSON.stringify({
            event: "surrenderB"
        }));
    }
    const click_match_btn = () =>{
        if ((store.state.user.id == store.state.pk.a_id) && (store.state.pk.turn == "A") ) {
            if (store.state.pk.a_point === 0) {
                // if(a_status.value == 1){
                //     a_status.value = Math.floor(Math.random() * 6) + 1;
                //     return;
                // }
                socket.send(JSON.stringify({
                    event: "rollA"
                }));
            }else{
                if(store.state.pk.a_status == 0)alert("请选择落子位置");
            }
        }else if ((store.state.user.id == store.state.pk.b_id) && (store.state.pk.turn == "B")) {
            if (store.state.pk.b_point === 0) {
                // if(b_status.value == 1){
                //     b_status.value = Math.floor(Math.random() * 6) + 1;
                //     return;
                // }
                socket.send(JSON.stringify({
                    event: "rollB"
                }));
            }else{
                if(store.state.pk.b_status == 0)alert("请选择落子位置");
            }
        } else {
            alert("还没到你的回合！");
        }
    }

    const fillA = (index) => {
        if(store.state.pk.a_status  == 1){
            for(let i = 0; i < 9; i ++){
                if(store.state.pk.aMap[i] != 0) continue;
                index = i ;
                break;
            }
        }
        // if (store.state.pk.a_point === 0) {
        //     alert('请先投掷骰子');
        //     return;
        // }
        let res = check_valid_A(index)
        if (res != 'success') alert(res);
        else {
            socket.send(JSON.stringify({
                event: "fillA",
                direction: index
            }));
        }
    }

    const fillB = (index) => {
        if(store.state.pk.b_status  == 1){
            for(let i = 0; i < 9; i ++){
                if(store.state.pk.bMap[i] != 0) continue;
                index = i;
                break;
            }
        }
        // if (store.state.pk.b_point === 0) {
        //     alert('请先投掷骰子');
        //     return;
        // }
        let res = check_valid_B(index)
        if (res != 'success') alert(res);
        else {
            socket.send(JSON.stringify({
                event: "fillB",
                direction: index
            }));
        }
    }

    const check_valid_A = (pos) => {
      let msg = 'success';
      if (store.state.pk.turn == "A" ) {
        if (store.state.pk.aMap[pos] != 0) msg = "不能重复落子";
      } else if (store.state.pk.turn == "B" ) msg = "不能放置于对手棋盘";

      return msg;
    }
    const check_valid_B = (pos) => {
      let msg = 'success';
      if (store.state.pk.turn == "B") {
        if (store.state.pk.bMap[pos] != 0) msg = "不能重复落子";
      } else if (store.state.pk.turn == "A") msg = "不能放置于对手棋盘";

      return msg;
    }

    const restart = () => {
        store.commit("updateModel","none");
        store.commit("clearMap");
        store.commit("updateStatus", "matching");
        store.commit("updateLoser", "none");
        store.commit("updateAStatus",0);
        store.commit("updateBStatus",0);
        store.commit("updateOpponent", {
            username: "我的对手",
            photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
        })
    }
    onUnmounted(() => {
        store.commit("updateModel","none");
        store.commit("clearMap");
        store.commit("updateStatus", "matching");
        store.commit("updateLoser", "none");
        store.commit("updateAStatus",0);
        store.commit("updateBStatus",0);
        store.commit("updateOpponent", {
            username: "我的对手",
            photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
        })
    })

    return {
        click_match_btn,
        fillA,
        fillB,
        check_valid_A,
        check_valid_B,
        restart,
        a_botstart,
        b_botstart,
        a_surrender,
        b_surrender,
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
div.text-wrap {
width: 100px;
color:rgba(57, 218, 87, 0.879);
font-size: 25px;
font-weight: 400;
font-style: italic;
padding-top: 2vh;
}
div.result-board-btn {
padding-top: 7vh;
text-align: center;
}
div.user-photo {
    text-align: center;
    padding-top: 5vh;
}
div.user-photo > img {
    border-radius: 50%;
    width: 12vh;
}
div.user-username {
    text-align: center;
    font-size: 24px;
    font-weight: 600;
    color: white;
    padding-top: 2vh;
}
</style>