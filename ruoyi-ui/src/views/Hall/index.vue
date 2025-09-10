<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true"  label-width="90px">
    <el-form-item label="电影院名称:" prop="cinemaName">
        <el-input v-model="queryParams.cinemaName"></el-input>
    </el-form-item>
     <el-form-item label="放映厅名称:" prop="hallName">
        <el-input v-model="queryParams.hallName"></el-input>
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
    <el-table v-loading="isloading" :data="hallData " style="width: 100%; margin-top: 20px;" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="hallId" label="放映厅id"></el-table-column>
        <el-table-column prop="hallName" label="放映厅名称"></el-table-column>
        <el-table-column prop="hallCol" label="放映厅座位排数"></el-table-column>
        <el-table-column prop="hallRow" label="放映厅座位列数"></el-table-column>
        <el-table-column prop="cinemaName" label="所属影院名称"></el-table-column>
         <el-table-column  label="操作">
             <template slot-scope="scope">
        <el-button
          type="text"
          size="small"
        @click="handleUpdate(scope.row.hallId)"
          >
          修改
        </el-button>
        <el-button
          type="text"
          size="small"
          @click="handleDeleteCinema(scope.row.hallId)">
          删除
          
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
        <el-form ref="hallForm" :model="form" label-width="100px" :rules="rules">
            <el-form-item label="放映厅名称:" prop="hallName">
                <el-input v-model="form.hallName"></el-input>
            </el-form-item>
            <el-form-item label="座位排数:" prop="hallCol">
                 <el-input-number v-model="form.hallCol" @change="handleChange" :min="1" style="width: 100%;" ></el-input-number>
            </el-form-item>
            <el-form-item label="座位列数:" prop="hallRow">
                <el-input-number v-model="form.hallRow" @change="handleChange" :min="1" style="width: 100%;"></el-input-number>
            </el-form-item>
           <el-form-item label="所属影院:" prop="hallRow">
                <el-select v-model="form.cinemaId"  >
                    <el-option v-for="item in cinemaData"
                            :key="item.cinemaId"
                            :label="item.cinemaName"
                            :value="item.cinemaId"
                    >
                    </el-option>
                </el-select>
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
import { getHallList,addHall,updateHall,deleteHall,selectHall,getCinemaList } from '../../api/system/hall';
import {getToken} from '@/utils/auth'

export default {
    data(){
        return {
            queryParams:{
                pageSize:20,
                pageNum:1
            },
            hallData:[],
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
             rules: {
          hallName: [
            {required:true,message:'请输入放映厅名称', trigger: 'blur' },
            
            
          ],
          hallCol: [
            {required:true,message:'请输入座位排数', trigger: 'blur' },
            
          ],
          hallRow: [
            {required:true,message:'请输入座位列数', trigger: 'blur' },
            
          ],
          cinemaId: [
            {required:true,message:'请选择所属的影院', trigger: 'blur' },
            
          ],
        }

        }
    },
    created(){
        this.getList()
        this.getCinemaOptions()
    },
    methods:{
        getList(){
            this.isloading=true
            getHallList(this.queryParams).then(Response=>{
                this.isloading=false
                    this.total=Response.total
                    this.hallData=Response.rows
                    
            })
        },
        getCinemaOptions(){
            getCinemaList().then(response=>{
                this.cinemaData=response.data
            })
        },
        reset(){
           this.resetForm("queryForm")
            this.getList()
        },
        handleAdd(){
            
            this.open=true
            this.title="添加放映厅信息"
            this.form={}
        },
        handleUpdate(hallId){
            let param={"hallId":hallId==undefined?this.id:hallId}
            selectHall(param).then(response=>{
                this.form=response.data
                this.open=true
                this.title="修改影院信息"
            })
        },
         handleDeleteCinema(hallId) {
            let postId=hallId==undefined?this.id:hallId
      let param={"hallId":postId}
      this.$modal.confirm('是否确认删除岗位编号为"' + postId + '"的数据项？').then(function() {
       deleteHall(param)
      }).then(() => {
        this.$modal.msgSuccess("删除成功")
        this.getList()
      })
    },
        
     submitForm() {
        this.$refs['hallForm'].validate((valid) => {
          if (valid) {
            if(this.form.hallId==null){
                 addHall(this.form).then(response=>{
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
                updateHall(this.form).then(response=>{
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
        this.id=selection[0].hallId
        this.issingle=false
      }

    },
  }
    }

</script>

<style>

</style>