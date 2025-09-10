<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true"  label-width="90px">
    <el-form-item label="电影院名称:" prop="cinemaName">
        <el-input v-model="queryParams.cinemaName"></el-input>
    </el-form-item>
     <el-form-item label="放映厅名称:" prop="hallName">
        <el-input v-model="queryParams.hallName"></el-input>
    </el-form-item>
    <el-form-item label="电影名称:" prop="movieName">
        <el-input v-model="queryParams.movieName"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" size="mini" icon="el-icon-search" @click="getList()">搜索</el-button>
        <el-button type="warning" size="mini" icon="el-icon-refresh" @click="reset()">重置</el-button>
    </el-form-item>
    </el-form>
    <el-row :gutter="10" >
                <el-col :span="1.5">
                <el-button type="primary"
                size="mini"
                icon="el-icon-plus"
                plain
                @click="handleAdd()"
                >新增</el-button>
               
        </el-col>
         <el-col :span="1.5">
                <el-button type="warning"
                size="mini"
                icon="el-icon-plus"
                plain
                :disabled="issingle"
                @click=" handleUpdate()"
                >修改</el-button>
               
        </el-col>
         <el-col :span="1.5">
                <el-button type="danger"
                size="mini"
                icon="el-icon-plus"
                plain
                 @click="handleDeleteCinema()"
                >删除</el-button>
               
        </el-col>
    </el-row>
    <el-table v-loading="isloading" :data="scheduleData " style="width: 100%; margin-top: 20px;" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="movieName" label="电影"></el-table-column>
        <el-table-column prop="cinemaName" label="影院名称"></el-table-column>
        <el-table-column prop="hallName" label="放映厅"></el-table-column>
        <el-table-column prop="scheduleStartTime" label="开始放映时间"></el-table-column>
        <el-table-column prop="schedulePrice" label="价格"></el-table-column>
        <el-table-column prop="scheduleState" label="状态">
            <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_schedule_state" :value="scope.row.scheduleState"/>
        </template>
        </el-table-column>
         <el-table-column  label="操作">
             <template slot-scope="scope">
        <el-button
          type="text"
          size="small"
        @click="handleUpdate(scope.row.scheduleId)"
          >
          修改
        </el-button>
        <el-button
          type="text"
          size="small"
          @click="handleUpdateState(scope.row.scheduleId,scope.row.scheduleState)">
          <span v-if="scope.row.scheduleState==0">
                上架
          </span>
          <span v-else>
                下架
          </span>
        </el-button>
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
    <!-- 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
        <el-form ref="scheduleForm" :model="form" label-width="100px" :rules="rules">
            <el-form-item label="电影:" prop="movieId">
               <el-select v-model="form.movieId" style="width: 100%;" >
                    <el-option v-for="item in movieData"
                            :key="item.movieId"
                            :label="item.movieCnName"
                            :value="item.movieId"
                    >
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="所属影院:" prop="cinemaId">
                <el-select v-model="form.cinemaId"  style="width: 100%;" @change="getHallOptions">
                    <el-option v-for="item in cinemaData"
                            :key="item.cinemaId"
                            :label="item.cinemaName"
                            :value="item.cinemaId"
                    >
                    </el-option>
                </el-select>
            </el-form-item>
             <el-form-item label="放映厅:" prop="hallId">
               <el-select v-model="form.hallId"  style="width: 100%;">
                    <el-option v-for="item in hallData"
                            :key="item.hallId"
                            :label="item.hallName"
                            :value="item.hallId"
                    >
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="放映时间:" prop="scheduleStartTime">
                   <el-date-picker
            v-model="form.scheduleStartTime"
            type="datetime"
            placeholder="选择日期时间"
            value-format="yyyy-MM-dd HH:mm:ss">
    </el-date-picker>
            </el-form-item>
            <el-form-item label="价格:" prop="schedulePrice">
                 <el-input-number v-model="form.schedulePrice" :precision="2" :step="1.0" style="width: 100%;"></el-input-number>
            </el-form-item>
           
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="open = false">取 消</el-button>
    <el-button type="primary"  @click="submitForm()">确 定</el-button>
  </span>
    </el-dialog>
</div>
</template>

<script>
import { getScheduleList,addSchedule,updateSchedule,updateScheduleState,selectSchedule,getCinemaList,getMovieList,getHallList } from '../../api/system/schedule';
import {getToken} from '@/utils/auth'

export default {
    dicts:['sys_schedule_state'],
    data(){
        return {
            queryParams:{
                pageSize:20,
                pageNum:1
            },
            scheduleData:[],
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
             rules: {
          movieId: [
            {required:true,message:'请输入电影', trigger: 'blur' },
            
            
          ],
          cinemaId: [
            {required:true,message:'请选择影院', trigger: 'blur' },
            
          ],
          hallId: [
            {required:true,message:'请选择放映厅', trigger: 'blur' },
            
          ],
          scheduleStartTime: [
            {required:true,message:'请选择放映时间', trigger: 'blur' },
            
          ],
          schedulePrice: [
            {required:true,message:'请选择价格 ', trigger: 'blur' },
            
          ],
        }

        }
    },
    created(){
        this.getList()
        this.getCinemaOptions()
        this.getMovieOptions()
    },
    methods:{
        getList(){
            this.isloading=true
            getScheduleList(this.queryParams).then(Response=>{
                this.isloading=false
                    this.total=Response.total
                    this.scheduleData=Response.rows
                    
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
         handleUpdateState(scheduleId,scheduleState) {
                let tip =scheduleState==0?'上架':'下架'
            scheduleState=scheduleState==0?1:0
            let param={"scheduleId":scheduleId,"scheduleState":scheduleState}
            this.$modal.confirm('是否确认' + tip + '该场次').then(function() {
            updateScheduleState(param)
             }).then(() => {
                this.$modal.msgSuccess("操作成功")
                this.getList()
                console.log(scheduleState);
                
                })
    },
        
     submitForm() {
        this.$refs['scheduleForm'].validate((valid) => {
          if (valid) {
            if(this.form.scheduleId==null){
                 addSchedule(this.form).then(response=>{
                if(response.code==200){
                    this.$modal.msgSuccess(response.msg)
                    this.open=false
                    this.form={};
                    this.getList()
                    
                }else{
                    this.$modal.msgError(response.msg)
                }
            })
           
          }else{
                //修改
                updateSchedule(this.form).then(response=>{
                if(response.code==200){
                    this.$modal.msgSuccess(response.msg)
                    this.open=false
                    // this.form={};
                    this.getList()
                }else{
                    this.$modal.msgError(response.msg)
                }
            })
          } 
        }else {
           alert('验证不通过!!!');
            return false;
          }
        });
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
  }
    }

</script>

<style>

</style>