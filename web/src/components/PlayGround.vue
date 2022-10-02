<template>
    <div class="playground">
        <LocalGameMap />
    </div>
</template>

<script>
import LocalGameMap from './LocalGameMap.vue'

export default {
    components: {
        LocalGameMap,
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
