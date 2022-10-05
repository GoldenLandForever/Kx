<template>
    <div class="playground">
        <LocalGameMap v-if="$store.state.pk.model === 'local'" />
        <BotGameMap v-if="$store.state.pk.model === 'bot'"/>
    </div>
</template>

<script>
import LocalGameMap from './LocalGameMap.vue'
import BotGameMap from './BotGameMap.vue'


export default {
    components: {
    LocalGameMap,
    BotGameMap
}
}
</script>

<style scoped>
div.playground {
    width: 90%;
    height: 70vh;
    margin: 40px auto;
}
</style>
