<template>
    <div v-if="$store.state.pk.model === 'none'">
        <div class="row">
            <button @click="local">
                <font color="white" size="9">
                    本地对战
                </font>
            </button>
        </div>
        <div class="row">
            <button @click="match">
                <font color="white" size="9">
                    匹配对战
                </font>
            </button>
        </div>
        <div class="row">
            <button @click="bot">
                <font color="white" size="9">人机对战</font>
            </button>
        </div>
    </div>
    <PlayGround v-if="$store.state.pk.status === 'playing' || $store.state.pk.model === 'local'"/>
    <MatchGround v-if="$store.state.pk.status === 'matching' && $store.state.pk.model === 'match'" />
    <ResultBoard v-if="$store.state.pk.loser != 'none'" />
</template>

<script>
import PlayGround from '../../components/PlayGround.vue'
import { useStore } from 'vuex'

export default {
    components: {
        PlayGround
    },
    setup(){
        const store = useStore();

        const local = () =>{
            store.state.pk.model ="local";
        }
        const match = () =>{
            store.state.pk.model = "match";
        }
        const bot = () =>{
            store.state.pk.model = "bot";
        }
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
