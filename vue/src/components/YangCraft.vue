<template>
  <div>
    <Top/>
    <Middle/>
  </div>
</template>

<script>
import Top from "../views/YangCraft/home/top";
import Middle from "../views/YangCraft/home/middle";

export default {
  name: "Yangcraft",
  methods: {
  

  },
  components: {Top,Middle},
  data () {
    return{
      Top,
      Middle
    }
  }
}
</script>

<style scoped>

</style>
