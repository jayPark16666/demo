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
    <div style="margin-top: 80px;"></div>

    <!-- 巨幕 -->
    <div class="banner2">
        <div class="wrapper clearfix">
            <div class="cinema-left">
                <div class="avatar-shadow">
                      <img class="avatar" :src="cinemaData.cinemaPoster">
                </div>
            </div>

            <div class="cinema-main clearfix">
                <div class="cinema-brief-container">
				   <h3 class="name text-ellipsis">{{ cinemaData.cinemaName }}</h3>
                   <div class="address text-ellipsis">{{cinemaData.cinemaAddress}}</div>
                    <div class="telphone">电话：{{cinemaData.cinemaTel}}</div>
                    <div class="features-group">
                        <div class="group-title">影院服务</div>

                        <div class="feature">
                            <span class="tag ">3D眼镜</span>
                            <p class="desc text-ellipsis" title="免押金">免押金</p>
                        </div>
                        <div class="feature">
                            <span class="tag ">可停车</span>
                            <p class="desc text-ellipsis" title="可停车">停车场可凭电影票在影城票台领取3小时内免停权益</p>
                        </div>
                    </div>
                </div>
            </div>
                
        </div>
    </div>

    <!-- 占位符 -->
    <div style="margin-top: 50px;"></div>

    <!-- 主体 -->
    <div class="main">
        <div class="main-inner main-bodyz">
            <div class="show-list active" data-index="0">
                <!-- 电影信息 -->
                <div class="movie-info">
				   <div>
                    <h3 class="movie-name">{{movieData.movieCnName}}</h3>
                 
                   <div class="movie-desc">
                     <div>
                        <span class="key">时长 : </span>
                        <span class="value">{{movieData.movieDuration}}</span>
                    </div>
                    <div>
                        <span class="key">类型 :</span>
                        <span class="value">{{movieData.movieType}}</span>
                    </div>
					 <div>
                        <span class="key">主演 :</span>
                        <span class="value">{{ movieData.movieActor }}</span>
                    </div>
                 </div>
                </div>
                <!-- 场次列表 -->
                <div class="plist-container active">
                    <table class="plist">
                        <thead>
                            <tr>
                                <th>放映时间</th>
                                <th>语言版本</th>
                                <th>放映厅</th>
                                <th>售价（元）</th>
                                <th>选座购票</th>
                            </tr>
                        </thead>
                        <tbody>
                         <tr class="" v-for="item in scheduleList" :key="item.id">
                         <td> <span class="begin-time">{{item.scheduleStartTime}}</span> <br> </td>
                         <td> <span class="lang">国语2D</span> </td>
                         <td> <span class="hall">{{item.hallName}}</span> </td>
                         <td> <span class="sell-price"><span class="stonefont">{{item.schedulePrice}}</span> </span> </td>
                         <td> <a href="javascript:void(0)" @click.prevent="buySeat(item.scheduleId)" class="buy-btn normal">选座购票</a> </td>
                         </tr>   
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
  </div>
  </div>
</template>

<script>
import '@/assets/static/css/main.css'
 import '@/assets/static/css/header.css'
 import '@/assets/static/css/main2.css'
 import '@/assets/static/css/footer.css'
 import '@/assets/static/css/buyTickets.css'
 import '@/assets/static/css/movieDetail.css'
 import '@/assets/static/css/selectSeat.css'
import {getMovie} from '@/api/system/movie.js';
import { selectCinema } from '../../api/system/cinema';
import { frontScheduleList } from '../../api/system/schedule'
export default {
    data(){
        return{
            movieData:{},
            cinemaData:{},
            scheduleList:{}
        }
    },
    created(){
 const param=new URLSearchParams(location.search)
        this.getMovieDetail(param.get("movieId"))
        this.getCinemaDetail(param.get("cinemaId"))
        this.getScheduleDetail(param.get("movieId"),param.get("cinemaId"))
    },
    methods:{
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
        getScheduleDetail(movieId,cinemaId){
             frontScheduleList({"movieId":movieId,"cinemaId":cinemaId}).then(response=> {
                    this.scheduleList=response.data
                    console.log(this.scheduleList);
                    
            })
        },
        buySeat(scheduleId){
             //跳转页面
            this.$router.push({path:"/front/seat",query:{"scheduleId":scheduleId}})
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