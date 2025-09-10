<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true"  label-width="150px">
    <el-form-item label="电影院名称:" prop="cinemaName">
        <el-input v-model="queryParams.cinemaName"></el-input>
    </el-form-item>
    <el-form-item label="顾客名称:" prop="userName">
        <el-input v-model="queryParams.userName"></el-input>
    </el-form-item>
    <el-form-item label="顾客电话号码:" prop="userMobile" >
        <el-input v-model="queryParams.userMobile"></el-input>
    </el-form-item>
    <el-form-item label="电影名称:" prop="movieName">
        <el-input v-model="queryParams.movieName"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" size="mini" icon="el-icon-search" @click="getList()">搜索</el-button>
        <el-button type="warning" size="mini" icon="el-icon-refresh" @click="reset()">重置</el-button>
    </el-form-item>
    </el-form>
        
    <el-table v-loading="isloading" :data="orderData " style="width: 100%; margin-top: 20px;" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55px" text-align="center" />
        <el-table-column prop="userName" label="顾客姓名"></el-table-column>
        <el-table-column prop="userMobile" label="顾客手机号码"></el-table-column>
        <el-table-column prop="movieName" label="电影"></el-table-column>
        <el-table-column prop="cinemaName" label="影院名称"></el-table-column>
        <el-table-column prop="hallName" label="放映厅"></el-table-column>
        <el-table-column prop="scheduleStartTime" label="开始放映时间"></el-table-column>
         <el-table-column prop="orderPosition" label="座位"></el-table-column>
         <el-table-column prop="orderPrice" label="订单价格"></el-table-column>
         <el-table-column prop="orderTime" label="支付时间"></el-table-column>
        <el-table-column prop="orderState" label="订单状态">
            <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_order_status" :value="scope.row.orderState"/>
        </template>
        </el-table-column>
    </el-table>
    <pagination
    v-show="total>0"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      :total="total"
      @pagination="getList()"
    />
    
</div>
</template>

<script>
import { getOrderList,getCinemaList,getHallList,getMovieList } from '../../api/system/orderinfo';
import {getToken} from '@/utils/auth'

export default {
    dicts:['sys_order_status'],
    data(){
        return {
            queryParams:{
                pageSize:20,
                pageNum:1
            },
            orderData:[],
            total:0,
            isloading:false,
            title:'',
            open:false,
            form:{},
            fileSize:1,
            showTip:false,
            issingle:false,
            id:"",
            cinemaData:[],
            movieData:[],
            hallData:[],
            

        }
    },
    created(){
        this.getList()
        this.getCinemaOptions()
        this.getMovieOptions()
        this.getHallOptions()
    },
    methods:{
        getList(){
            this.isloading=true
            getOrderList(this.queryParams).then(Response=>{
                this.isloading=false
                    this.total=Response.total
                    this.orderData=Response.rows
                    
            })
        },
        getCinemaOptions(){
            getCinemaList().then(response=>{
                this.cinemaData=response.data
            })
        },
        getMovieOptions(){
            getMovieList().then(response=>{
                this.movieData=response.data
            })
        },
        getHallOptions(){
            getHallList({"cinemaId":this.form.cinemaId}).then(response=>{
                this.hallData=response.data
            })
        },
        reset(){
           this.resetForm("queryForm")
            this.getList()
        },
        handleAdd(){
            
            this.open=true
            this.title="添加场次信息"
            this.form={}
        },
        handleUpdate(scheduleId)
        {
             
            let param={"scheduleId":scheduleId==undefined?this.id:scheduleId}
            selectSchedule(param).then(response=>{
                this.form=response.data
                this.getHallOptions()
                this.open=true
                this.title="修改场次信息"
            })
        },
         // 多选框选中数据
        handleSelectionChange(selection) {
      if(selection.length!=1){
        this.issingle=true
      }else{
        this.id=selection[0].scheduleId
        this.issingle=false
      }

    },
      }, 
    }

</script>

<style>

</style>