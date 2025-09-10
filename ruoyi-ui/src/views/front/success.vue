<template>
  <div>
    <div class="header navbar navbar-fixed-top">
        <div class="header-top">
            <div class="header-inner">
                <h1>
                    <a href="javascript:void(0)" class="logo"></a>
                </h1>
                <div class="nav">
                    <ul>
                        <li><a href="首页.html">首页</a></li>
                    </ul>
                </div>
                <div class="app-download">
                </div>
                <div class="user-info">
                <div class="user-avatar J-login">
                    <ul class="layui-nav" style="background-color: #fff;">
                        <li class="layui-nav-item header-li" style="width:40px;" lay-unselect="" >
                        </li>
                    </ul>
                </div>
                </div>
                <form action="">
                    <input name="searchMovie" class="search" type="search" maxlength="32" placeholder="找影视剧、影人、影院" autocomplete="off">
                <input class="submit" type="submit" value="">
                </form>
            </div>
        </div>
    </div>
    <!-- 占位符 -->
    <div style="margin-top: 90px;"></div>

    <!-- 主体 -->
    <div class="container">
        <div class="order-progress-bar">
            <div class="step first done">
                <span class="step-num">1</span>
                <div class="bar"></div>
                <span class="step-next">选择影片场次</span>
            </div>
            <div class="step done">
                <span class="step-num">2</span>
                <div class="bar"></div>
                <span class="step-next">选择座位</span>
            </div>
            <div class="step done">
                <span class="step-num">3</span>
                <div class="bar"></div>
                <span class="step-next">付款</span>
            </div>
            <div class="step last done">
                <span class="step-num">4</span>
                <div class="bar"></div>
                <span class="step-next">影院取票观影</span>
            </div>
        </div>
        <div class="main-paystatus">
            <div class="img"></div>
            <p class="statustext">已完成支付</p>
        </div>
        <div class="right">
            <p class="warning">
                该页面关闭后可以在<span class="attention">个人订单</span>页面查看订单信息
            </p>
            <div>
                <div class="pay-btn" @click="returnCenter()">返回个人订单</div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import '@/assets/static/css/main.css'
 import '@/assets/static/css/header.css'
 import '@/assets/static/css/footer.css'
  import '@/assets/static/css/paystatus.css'
export default {
    data(){
        return{

        }
    },
    methods:{
        // returnCenter(){
        //       //跳转页面
        //     this.$router.push({path:"/data/orderinfo"})
        // }
    }
}
</script>

<style scoped>
.container{
      width: 1170px;
      margin: 0 auto;
}
 li{
    list-style: none;
   }
</style>