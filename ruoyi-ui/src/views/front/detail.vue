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
                        <li><a href="./mainPage.jsp">首页</a></li>
                        <li class="active"><a href="./movieList.jsp">电影</a></li>
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
            <div class="celeInfo-left">
                <div class="avatar-shadow">
                   <img class="avatar" :src="movieData.moviePicture" alt="">
                </div>
            </div>
            
            <div class="celeInfo-right clearfix">
                <div class="movie-brief-container">
                    <h3 class="name">{{movieData.movieCnName}}</h3>
                    <div class="ename ellipsis">Successor</div>
                    <ul>
                        <li class="ellipsis">{{movieData.movieType}}</li>
                        <li class="ellipsis">{{movieData.movieCountry}} / {{movieData.movieDuration}}</li>
                        <li class="ellipsis">{{formatDate(movieData.movieReleasedate)}}{{movieData.movieCountry}}上映</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- 占位符 -->
    <div style="margin-top: 50px;"></div>

    <!-- 主体 -->
    <div class="main">
        <div class="main-inner main-buyticket">
            <div class="cinemas-list">
                <h2 class="cinemas-list-header">影院列表</h2>   
				<div class="cinema-cell" v-for="item in cinemaList" :key="item.id">
                        <div class="cinema-info">
                          <a class="cinema-name">{{item.cinemaName}}</a>
                            <p class="cinema-address">{{item.cinemaAddress}}</p>
                        </div>
                        <div class="buy-btn">
                            <a href="" @click.prevent="scheduleInfo(item.cinemaId)">选座购票</a>
                        </div>
                        <div class="price">
                            <span class="rmb red">￥ </span>
                            <span class="price-num red"><span class="stonefont">{{ item.price }}</span></span>
                            <span style="margin-left:5px;">起</span>
                        </div>
                    </div>
            </div>
            <!-- 分页 -->
            <div class="cinema-pager">
                <ul class="list-pager">	
				  
                </ul>
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
 import {getMovie} from '@/api/system/movie.js';
import {frontCinemaList} from '@/api/system/cinema.js';
export default {
    data(){
        return{

            movieData:{},
            cinemaList:[]
        }
    },
    methods:{
        getMovieDetail(movieId){
            getMovie({"movieId":movieId}).then(response=> {
                console.log(movieId);
                
                    this.movieData=response.data
            })
        },
         formatDate(date) {
            const d = new Date(date);
            const year = d.getFullYear();
            const month = String(d.getMonth() + 1).padStart(2, '0');
            const day = String(d.getDate()).padStart(2, '0');
            // const hours = String(d.getHours()).padStart(2, '0');
            // const minutes = String(d.getMinutes()).padStart(2, '0');
            // const seconds = String(d.getSeconds()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
        getCinemaList(movieId){
            frontCinemaList({"movieId":movieId}).then(response=> {
                    this.cinemaList=response.data
                    console.log(this.cinemaList);
                    
            })
        },
        scheduleInfo(cinemaId){
             this.$router.push({path:"/front/schedule",query:{"movieId":this.movieData.movieId,"cinemaId":cinemaId}})
        }
    },
    created(){
        const param=new URLSearchParams(location.search)
        this.getMovieDetail(param.get("movieId"))
        this.getCinemaList(param.get("movieId"))
    },
     
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