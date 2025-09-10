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
    <div style="margin-top: 30px;"></div>

    <!-- 主体 -->
    <div class="main">
        <div class="main-inner main-page" >
        <el-carousel trigger="click" height="440px">
            <el-carousel-item>
                <img src="@/assets/static/images/pic1.jpg">
            </el-carousel-item>
            <el-carousel-item>
                <img src="@/assets/static/images/pic2.jpg">
            </el-carousel-item>
            <el-carousel-item>
                <img src="@/assets/static/images/pic5.jpg">
            </el-carousel-item>
            <el-carousel-item>
                 <img src="@/assets/static/images/pic6.jpg">
            </el-carousel-item>
            <el-carousel-item>
                 <img src="@/assets/static/images/pic7.jpg">
            </el-carousel-item>
           <el-carousel-item>
                 <img src="@/assets/static/images/pic9.jpg">
            </el-carousel-item>
            
        </el-carousel>
        
           
            <div class="movie-grid">
                <div class="panel-header">
                    <span class="panel-more">
                        <a href="./movieList.jsp" class="textcolor_red" data-act="all-playingMovie-click">
                            <span>全部</span>
                        </a>
                        <span class="panel-arrow panel-arrow-red"></span>
                    </span>
                    <span class="panel-title hot-title">
					    <span class="textcolor_red">正在热映10部</span>
                    </span>
                </div>
                <div class="panel-content">
                    <ul class="movie-list movie-hot">
					<li v-for="item in movieList" :key="item.id">
                       <div class="movie-item"> 
                            <a href="购票.html" target="_blank" data-act="playingMovie-click" data-val="">
                              <div class="movie-poster" style="cursor:default;">
								  <img id="moive_picture" :src="item.moviePicture">
							
								  <div class="movie-overlay movie-overlay-bg">
										<div class="movie-info">
										  <div class="movie-score"><i style="font-size:16px">9.5</i></div>
										  <div class="movie-title movie-title-padding" :title="item.movieCnName">{{item.movieCnName}}</div>
										</div>
								  </div>
                               </div>
                             </a>
                             <div class="movie-detail movie-detail-strong movie-sale">
                                    <a href="javascript:void(0)"  @click="moviedetail(item.movieId)" class="active"  data-act="salePlayingMovie-click" data-val="{movieid:42964}">购 票</a>
                              </div>
                            </div>
                        </li>
					
                    </ul>
                </div>
                <div class="panel">
                    <span class="panel-title on-title">
                    </span>
                </div>
                <div class="panel-content">
                    <ul class="movie-list movie-on">
                    </ul>
                </div>
            </div>    
        </div>
        <el-row style="text-align: center; margin-bottom: 30px; position: relative;">
             <el-pagination
             background
             layout="prev, pager, next"
             :total="total"
             :page-size="queryParam.pageSize"
            :current-page="queryParam.pageNum"
            @current-change="changepage"
            > 
        </el-pagination>
    </el-row>
    </div>
    </div>
   
</template>
<script>
 import '@/assets/static/css/main.css'
 import '@/assets/static/css/header.css'
 import '@/assets/static/css/main2.css'
 import '@/assets/static/css/footer.css'
 import {movieList} from '@/api/system/movie'
export default {
    created(){
        this.getList()
    },
    data(){
        return{
            movieList:[],
            total:0,
            queryParam:{
                pageNum:1,
                pageSize:20
            }
        }
        
    },
    methods:{
         getList(){
            movieList(this.queryParam).then(response=>{
                this.movieList=response.rows
                this.total=response.total
            })
        },
        changepage(val){
            this.queryParam.pageNum=val
            this.getList()
        },
        moviedetail(movieId){
            //跳转页面
            this.$router.push({path:"/front/detail",query:{"movieId":movieId}})
        }
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