<template>
  <div>
     <!-- 导航栏 -->
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
            <div class="step">
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

        <div class="main">
            <!-- 主页 -->
            <div class="hall">
                <div class="seat-example">
                    <div class="selectable-example example">
                        <span>可选座位</span>
                    </div>
                    <div class="sold-example example">
                        <span>已售座位</span>
                    </div>
                    <div class="selected-example example">
                        <span>已选座位</span>
                    </div>
                    <div class="couple-example example">
                        <span>情侣座位</span>
                    </div>
                </div>
                <div class="seats-block">
                    <div class="row-id-container">
                    </div>
                    <div class="seats-container">
                        <div class="screen-container">
                            <div class="screen">银幕中央</div>
                            <div class="c-screen-line"></div>
                        </div>
                        <div class="seats-wrapper">
                            <div class="row" v-for="n in row" :key="n.id">
                                <div style="display: inline-block;" v-for="i in col" :key="i.id" @click="selectSeat(n,i)">
                                    <span v-if="checkSoldSeat(n,i)!=-1" class="seat sold"> </span>
                                    <span v-else-if="checkSeat(n,i)!=-1" class="seat selected"></span>
                                    <span v-else class="seat selectable"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 侧页 -->
            <div class="side">
                <!-- 电影 -->
                <div class="movie-info clearfix">
				   <div class="poster">
                            <img :src="movieData.moviePicture">
                     </div>
                        <div class="content">
                            <p class="name text-ellipsis"></p>
                            <div class="info-item">
                             <span>类型：</span>
                             <span class="value">{{movieData.movieType}} </span>
                            </div>
                            <div class="info-item">
                                <span>时长：</span>
                                <span class="value">{{movieData.movieDuration}}</span>
                            </div>
                        </div>
                </div>
                <!-- 场次 -->
                <div class="show-info">
                     <div class="info-item">
                            <span>影院：</span>
                            <span class="value">{{cinemaData.cinemaName}}</span>
                        </div>
                        <div class="info-item">
                            <span>影厅：</span>
                            <span class="value">{{hallData.hallName}}</span>
                        </div>
                        <div class="info-item">
                            <span>版本：</span>
                            <span class="value">国语2D</span>
                        </div>
                        <div class="info-item">
                            <span>场次：</span>
                            <span class="value">{{formatDate(scheduleData.scheduleStartTime)}}</span>
                        </div>
                        <div class="info-item">
                            <span>票价：</span>
                            <span class="value">￥{{scheduleData.schedulePrice}}/张</span>
                        </div>"
                </div>
                <div class="ticket-info">
                    <div class="no-ticket" v-if="selected.length==0" >
                        <p class="buy-limit">座位：一次最多选4个座位</p>
                    </div>
                    <div class="has-ticket"  v-if="selected.length>0" >
                        <span class="text">座位：</span>
                        <div class="ticket-container" v-for="item in selected" :key="item.id">
                            <div  class="ticket" data-index="1-5">{{item.x}}排{{item.y}}列</div>
                        </div>
                    </div>
                    <div class="total-price" >
                        <span>总价：</span>
                        <span class="price">{{ selected.length*scheduleData.schedulePrice }}元</span>
                    </div>
                </div>
                <div class="confirm-order">
                    <div class="confirm-btn disable" v-if="selected.length==0">确认选座</div>
                    <div class="confirm-btn " v-if="selected.length>0" @click="buy()">确认选座</div>
                </div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import '@/assets/static/css/main.css'
 import '@/assets/static/css/header.css'
 import '@/assets/static/css/footer.css'
 import '@/assets/static/css/buyTickets.css'
 import '@/assets/static/css/movieDetail.css'
 import '@/assets/static/css/selectSeat.css'
  import '@/assets/static/css/buySeat.css'
import { selectHall } from '../../api/system/hall';
import {getMovie} from '@/api/system/movie.js';
import { selectCinema } from '../../api/system/cinema.js';
import { selectSchedule } from '../../api/system/schedule.js';
import { findSoldSeat } from '../../api/system/orderinfo.js';
export default {
    data(){
        return{
            row:0,
            col:0,
            movieData:{},
            cinemaData:{},
            hallData:{},
            scheduleData:{},
            selected:[],
           orderData:{},
           sold:[]
        }
    },
    created(){
        const param=new URLSearchParams(location.search)
        this.getScheduleInfo(param.get("scheduleId"))
        this.initSoldSeat(param.get("scheduleId"))
    },
    methods:{
        //回显座位
        initSoldSeat(scheduleId){
            findSoldSeat({"scheduleId":scheduleId}).then(response=>{
                let data=response.data
                for (let index = 0; index < data.length; index++) {
                    let seat=data[index]
                    let row =seat.substring(0,seat.indexOf("排"))
                    let col=seat.substring(seat.indexOf("排")+1,seat.indexOf("座"))
                    this.sold.push({'x':row,'y':col})
                }
                
            })
        },
        getHallDetail(hallId){
          selectHall({"hallId":hallId}).then(response=> {
                    this.row=response.data.hallRow
                    this.col=response.data.hallCol
                    this.hallData=response.data
                    this.getCinemaDetail(this.hallData.cinemaId)
            })
        },
         getMovieDetail(movieId){
            getMovie({"movieId":movieId}).then(response=> {
                    this.movieData=response.data
            })
        },
        getCinemaDetail(cinemaId){
            selectCinema({"cinemaId":cinemaId}).then(response=> {
                    this.cinemaData=response.data
            })
        },
        getScheduleInfo(scheduleId){
            selectSchedule({"scheduleId":scheduleId}).then(response=>{
                    this.scheduleData=response.data
                    this.getHallDetail(this.scheduleData.hallId)
                    this.getMovieDetail(this.scheduleData.movieId)
            })
        },
         formatDate(date) {
            const d = new Date(date);
            // const year = d.getFullYear();
            const month = String(d.getMonth() + 1);
            const day = String(d.getDate()).padStart(2, '0');
            const hours = String(d.getHours()).padStart(2, '0');
            const minutes = String(d.getMinutes()).padStart(2, '0');
            // const seconds = String(d.getSeconds()).padStart(2, '0');
            return `${month}月${day}日  ${hours}:${minutes}`;
        },
        selectSeat(n,i){
           
            if(this.checkSoldSeat(n,i)!=-1){
                alert('该座位已经被抢了')
                return
            }
            let b= this.checkSeat(n,i)
            if(b==-1){
                 if(this.selected.length==4){
                alert(`一次最多选择4个座位`)
                return 
            }
                 this.selected.push({'x':n,'y':i})
            }else{
                this.selected.splice(b,1) 
                 
            }
           
        },
        checkSeat(n,i){
            let  a=-1
            for (let index = 0; index < this.selected.length; index++) {
                    if(this.selected[index].x==n && this.selected[index].y==i){
                        a=index
                    }
            }
                return a
        },
         checkSoldSeat(n,i){
            let  a=-1
            for (let index = 0; index < this.sold.length; index++) {
                    if(this.sold[index].x==n && this.sold[index].y==i){
                        a=index
                    }
            }
                return a
        },
        buy(){
            var seat=""
            for (let index = 0; index < this.selected.length; index++) {
                    seat+=this.selected[index].x+"排"+this.selected[index].y+"座"+" "
            }
            const orderData={"scheduleId":this.scheduleData.scheduleId,"movieCnName":this.movieData.movieCnName,"time":this.formatDate(this.scheduleData.scheduleStartTime),
             "hallName":this.hallData.hallName,"cinemaName":this.cinemaData.cinemaName,"seat":seat,"price":this.selected.length*this.scheduleData.schedulePrice
            }
            //传数据给下一个页面
            console.log(orderData);
            
            localStorage.setItem("order",JSON.stringify(orderData))
            this.$router.push({path:"/front/pay"})
            localStorage.removeItem("minute")
            localStorage.removeItem("second")
        }
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