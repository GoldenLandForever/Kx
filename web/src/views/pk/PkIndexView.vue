<template>
    <div v-if="$store.state.pk.model === 'none'">
        <div class="row" style="width:1200px">
            <button @click="local">
                <font color="white" size="9">
                    本地对战
                </font>
            </button>
        </div>
        <div class="row" style="width:1200px">
            <button @click="match">
                <font color="white" size="9">
                    匹配对战
                </font>
            </button>
        </div>
        <div class="row" style="width:1200px">
            <button @click="bot">
                <font color="white" size="9">人机对战</font>
            </button>
        </div>
    </div>
    <PlayGround v-if="$store.state.pk.model === 'local' || $store.state.pk.model === 'bot'"/>
    <MatchGround v-if="$store.state.pk.model === 'match' && $store.state.pk.status === 'matching'" />
    <MatchPlayGround v-if="$store.state.pk.model === 'match' && $store.state.pk.status === 'playing'"></MatchPlayGround>
</template>

<script>
import PlayGround from '../../components/PlayGround.vue'
import MatchGround from '@/components/MatchGround.vue';
import { onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'
import MatchPlayGround from '../../components/MatchPlayGround.vue'

export default {
    components: {
    PlayGround,
    MatchGround,
    MatchPlayGround,
},
    setup(){
        const store = useStore();
        const socketUrl = `ws://localhost:3000/websocket/${store.state.user.token}`

        let socket = null;

        onMounted(() => {
            store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            socket = new WebSocket(socketUrl);

            socket.onopen = () => {
                console.log('connected!');
                store.commit("updateSocket", socket)
            }

            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                if (data.event === "match_success") {
                    store.commit("updateOpponent", {
                        username: data.opponent_username,
                        photo: data.opponent_photo
                    }),

                    setTimeout(() => {
                        store.commit("updateStatus", "playing")
                    }, 1000);

                    store.commit("updateGame", data.game);

                    console.log('match found!')
                } else if (data.event === 'result') store.commit("updateRes", data);
                else if (data.event === 'rollA') store.commit("updateApoint", data);
                else if (data.event === 'rollB') store.commit("updateBpoint", data);
                else if (data.event === 'curMap') store.commit("updateCurMap", data);

                socket.onclose = () => {
                    console.log("disconnected!")
                }
            }
        });

        const local = () =>{
            store.state.pk.model ="local";
        }
        const match = () =>{
            store.state.pk.model = "match";
        }
        const bot = () =>{
            store.state.pk.model = "bot";
        }
        onUnmounted(() => {
            store.commit("updateModel", "none");
        })
        return{
            local,
            match,
            bot,
        }
        
    }
}
</script>

<style scoped>
button {
  width: 500px;
  height: 100px;
  margin: 50px 500px 50px 500px;
  background-color: rgba(254, 254, 253, 0.5);
  font-size: large;
  font-weight: 600;
  font-style: italic;
}
</style>
