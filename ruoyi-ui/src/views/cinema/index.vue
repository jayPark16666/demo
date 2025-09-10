<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true"  label-width="90px">
    <el-form-item label="电影院名称:" prop="cinemaName">
        <el-input v-model="queryParams.cinemaName"></el-input>
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
    <el-table v-loading="isloading" :data="cinemaData " style="width: 100%; margin-top: 20px;" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="cinemaId" label="影院id"></el-table-column>
        <el-table-column prop="cinemaName" label="影院名称"></el-table-column>
        <el-table-column prop="cinemaTel" label="影院电话"></el-table-column>
        <el-table-column prop="cinemaAddress" label="影院地址"></el-table-column>
         <el-table-column  label="操作">
             <template slot-scope="scope">
        <el-button
          type="text"
          size="small"
        @click="handleUpdate(scope.row.cinemaId)"
          >
          修改
        </el-button>
        <el-button
          type="text"
          size="small"
          @click="handleDeleteCinema(scope.row.cinemaId)">
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
        <el-form ref="cinemaForm" :model="form" label-width="90px" :rules="rules">
            <el-form-item label="影院名称:" prop="cinemaName">
                <el-input v-model="form.cinemaName"></el-input>
            </el-form-item>
            <el-form-item label="影院电话:" prop="cinemaTel">
                <el-input v-model="form.cinemaTel"></el-input>
            </el-form-item>
            <el-form-item label="影院地址:" prop="cinemaAddress">
                <el-input v-model="form.cinemaAddress"></el-input>
            </el-form-item>
            <el-form-item label="影院照片:" prop="cinemaPoster">
      <el-upload
       v-model="form.cinemaPoster"
      multiple
      :disabled="disabled"
      :action="uploadImgUrl"
      list-type="picture-card"
      :on-success="handleUploadSuccess"
      :before-upload="handleBeforeUpload"
      :data="data"
      :limit="1"
      :on-error="handleUploadError"
      :on-exceed="handleExceed"
      ref="imageUpload"
      :on-remove="handleDelete"
      :show-file-list="true"
      :headers="headers"
      :file-list="fileList"
      :on-preview="handlePictureCardPreview"
      :class="{hide: this.fileList.length >= 1}"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <!-- 上传提示 -->
    <div class="el-upload__tip" slot="tip" v-if="showTip && !disabled">
      请上传
      <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>
      <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>
      的文件
    </div>

    <el-dialog
      :visible.sync="dialogVisible"
      title="预览"
      width="800"
      append-to-body
    >
      <img
        :src="dialogImageUrl"
        style="display: block; max-width: 100%; margin: 0 auto"
      />
    </el-dialog>
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
import { getCinemaList,addCinema,updateCinema,deleteCinema,selectCinema } from '../../api/system/cinema';
import {getToken} from '@/utils/auth'

export default {
    data(){
        return {
            queryParams:{
                pageSize:20,
                pageNum:1
            },
            cinemaData:[],
            total:0,
            isloading:false,
            title:'',
            open:false,
            form:{},
            //图片
             number: 0,
            uploadList: [],
              dialogImageUrl: "",
            dialogVisible: false,
            hideUpload: false,
            baseUrl: process.env.VUE_APP_BASE_API,
            uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload",// 上传的图片服务器地址
            headers: {
            Authorization: "Bearer " + getToken(),
            },
            fileList: [],
            fileSize:1,
            fileType:["png", "jpg", "jpeg","webp"],
            showTip:false,
            issingle:false,
            id:"",
             rules: {
          cinemaName: [
            {required:true,message:'请输入影院名称', trigger: 'blur' },
            
            
          ],
          cinemaAddress: [
            {required:true,message:'请输入影院地址', trigger: 'blur' },
            
          ],
          cinemaTel: [
            {required:true,message:'请输入影院电话', trigger: 'blur' },
            
          ],
          cinemaPoster: [
            {required:true,message:'请上传影院照片', trigger: 'blur' },
            
          ],
        }

        }
    },
    created(){
        this.getList()
    },
    methods:{
        getList(){
            this.isloading=true
            getCinemaList(this.queryParams).then(Response=>{
                this.isloading=false
                    this.total=Response.total
                    this.cinemaData=Response.rows
                    
            })
        },
        reset(){
           this.resetForm("queryForm")
            this.getList()
        },
        handleAdd(){
            this.open=true
            this.title="电影院信息"
            this.fileList=[]
            this.form={}
        },
        handleUpdate(cinemaId){
            let param={"cinemaId":cinemaId==undefined?this.id:cinemaId}
            selectCinema(param).then(response=>{
                this.form=response.data
                this.open=true
                this.title="修改影院信息"
                this.fileList=[]
                this.fileList.push({"url":response.data.cinemaPoster})
            })
        },
         handleDeleteCinema(cinemaId) {
            let postId=cinemaId==undefined?this.id:cinemaId
      let param={"cinemaId":postId}
      this.$modal.confirm('是否确认删除岗位编号为"' + postId + '"的数据项？').then(function() {
       deleteCinema(param)
      }).then(() => {
        this.$modal.msgSuccess("删除成功")
        this.getList()
      })
    },
        // 图片方法
         // 上传前loading加载
    handleBeforeUpload(file) {
      let isImg = false
      console.log(this.fileType);
      
      if (this.fileType.length) {
        let fileExtension = ""
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1)
        }
        isImg = this.fileType.some(type => {
          if (file.type.indexOf(type) > -1) return true
          if (fileExtension && fileExtension.indexOf(type) > -1) return true
          return false
        })
      } else {
        isImg = file.type.indexOf("image") > -1
      }

      if (!isImg) {
        this.$modal.msgError(`文件格式不正确，请上传${this.fileType.join("/")}图片格式文件!`)
        return false
      }
      if (file.name.includes(',')) {
        this.$modal.msgError('文件名不正确，不能包含英文逗号!')
        return false
      }
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize
        if (!isLt) {
          this.$modal.msgError(`上传头像图片大小不能超过 ${this.fileSize} MB!`)
          return false
        }
      }
      this.$modal.loading("正在上传图片，请稍候...")
      this.number++
    },
    // 文件个数超出
    handleExceed() {
      this.$modal.msgError(`上传文件数量不能超过 1 个!`)
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      if (res.code === 200) {
        this.uploadList.push({ name: res.fileName, url: res.url })
        this.form.cinemaPoster=res.url
        this.uploadedSuccessfully()
       
        
      } else {
        this.number--
        this.$modal.closeLoading()
        this.$modal.msgError(res.msg)
        this.$refs.imageUpload.handleRemove(file)
        this.uploadedSuccessfully()
       
        
      }
    },
    // 删除图片
    handleDelete(file) {
      const findex = this.fileList.map(f => f.name).indexOf(file.name)
      if (findex > -1) {
        this.fileList.splice(findex, 1)
        this.$emit("input", this.listToString(this.fileList))
      }
    },
    // 上传失败
    handleUploadError() {
      this.$modal.msgError("上传图片失败，请重试")
      this.$modal.closeLoading()
    },
    // 上传结束处理
    uploadedSuccessfully() {
      if (this.number > 0 && this.uploadList.length === this.number) {
        this.fileList = this.fileList.concat(this.uploadList)
        this.uploadList = []
        this.number = 0
        this.$emit("input", this.listToString(this.fileList))
        this.$modal.closeLoading()
      }
    },
    // 预览
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    // 对象转成指定字符串分隔
    listToString(list, separator) {
      let strs = ""
      separator = separator || ","
      for (let i in list) {
        if (list[i].url) {
          strs += list[i].url.replace(this.baseUrl, "") + separator
        }
      }
      return strs != '' ? strs.substr(0, strs.length - 1) : ''
    },
     submitForm() {
        this.$refs['cinemaForm'].validate((valid) => {
          if (valid) {
            if(this.form.cinemaId==null){
                 addCinema(this.form).then(response=>{
                if(response.code==200){
                    this.$modal.msgSuccess(response.msg)
                    this.open=false
                    this.form={};
                    this.getList()
                    this.form.cinemaPoster=""
                }else{
                    this.$modal.msgError(response.msg)
                }
            })
           
          }else{
                //修改
                updateCinema(this.form).then(response=>{
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
        this.id=selection[0].cinemaId
        this.issingle=false
      }

    },
  }
    }

</script>

<style>

</style>