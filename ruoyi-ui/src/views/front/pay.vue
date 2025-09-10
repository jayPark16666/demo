<template>
  <div>
    <!-- ------------------------------------------------------------------- -->
   <div class="header navbar navbar-fixed-top">
        <div class="header-top">
            <div class="header-inner">
                <h1>
                    <a href="javascript:void(0)" class="logo"></a>
                </h1>
                <div class="nav">
                    <ul>
                        <li><a href="./mainPage.jsp">首页</a></li>
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
            <div class="step last">
                <span class="step-num">4</span>
                <div class="bar"></div>
                <span class="step-next">影院取票观影</span>
            </div>
        </div>

        <div class="count-down-wrapper">
            <div class="count-down">
                <p class="time-left">
                    请在<span class="minute">{{ minute }}</span>分钟<span class="second">{{ second }}</span>秒内完成支付
                </p>
                <p class="tip">超时订单会自动取消，如遇支付问题，请联系管理员</p>
            </div>
        </div>

        <p class="warning">
            请仔细核对场次信息，出票后将<span class="attention">无法退票和改签</span>
        </p>

        <table class="order-table">
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>影片</th>
                    <th>时间</th>
                    <th>影院</th>
                    <th>座位</th>
                </tr>
            </thead>
            <tbody>
    
                        <tr>
                           <td ><el-input style="width: 46%;" placeholder="请输入您的姓名" v-model="form.userName"></el-input></td>
                           <td ><el-input style="width: 80%;" placeholder="请输入您的手机号码" v-model="form.userMobile"></el-input></td>
                           <td class="movie-name">{{orderData.movieCnName}}</td>
                           <td class="showtime">今天 {{orderData.time}}</td>
                           <td class="cinema-name">{{orderData.cinemaName}}</td>
                           <td>
                                <span class="hall">{{orderData.hallName}}</span>
                                <div class="seats">
                                    <div class="choiceseat" style="display: block;">
                                        {{orderData.seat}}</div>
                                </div>
                            </td>
                        </tr>
            </tbody>
        </table>

        <div class="right">
            <div class="price-wrapper">
                <span>实际支付：</span>
                <span class="price">{{orderData.price}}</span>
            </div>
            <div>
                <div class="pay-btn" @click="payorder">确认支付</div>
            </div>
        </div>
    </div>

  </div>
</template>

<script>
 import '@/assets/static/css/header.css'
 import '@/assets/static/css/footer.css'
 import '@/assets/static/css/pay.css'
import {createOrder} from '@/api/system/orderinfo.js';

export default {
    created(){
        this.orderData=JSON.parse(localStorage.getItem("order"))
        
    },
    data(){
        return{
        minute:null,
        second:null,
        orderData:{},
        form:{}
        }
       
    },
    methods:{
        payorder(){
            if(this.form.userName==null){
                alert(`请输入姓名`)
                return 
            }
            if(this.form.userMobile==null){
                alert(`请输入手机号码`)
                return
            }
            this.form.scheduleId=this.orderData.scheduleId
            this.form.orderPosition=this.orderData.seat
            
            createOrder(this.form).then(Response=>{
            //跳转页面
            this.$router.push({path:"/front/success"})
            })
        }
    },
    
    mounted(){
         setInterval(()=> {
                if(this.second==0 && this.minute==0){
                    window.alert("支付时间已过，订单失效！");
                    localStorage.clear();
                }
                if((localStorage.second == "NaN") || (localStorage.second == 0 && localStorage.minute == 0))
                {
                    localStorage.minute = 14;
                    localStorage.second = 59;
                }
                this.second = localStorage.second;
                this.minute = localStorage.minute; 
                if(this.second==0){
                    this.minute--;
                    this.second = 60;
                }
                this.second--;
                localStorage.second = this.second;
                localStorage.minute = this.minute;
            },1000);
    }
}
</script>

<style scoped>
 li{
    list-style: none;
   }
.container{
      width: 1170px;
      margin: 0 auto;
}
</style>