<template>
    <div>
       <el-form :model="queryParam" ref="form" label-width="100px" >
        <el-row style="margin-top: 10px;">
        <el-col :span="6" >
                <el-form-item  label="电影名称">
                 <el-input v-model="queryParam.movieCnName" placeholder="请输入电影信息"></el-input>
                </el-form-item>
        </el-col>
        <el-col :span="3" style="margin-left: 20px;">
              <el-button type="primary" @click="getList()">搜索</el-button>
              <el-button type="success" icon="el-icon-plus" @click="addmovie()">新增电影</el-button>
        </el-col>
        </el-row>
       
</el-form>
<el-row>
        <el-card v-for="(item,index) in movieList" :key="item.id" 
        style="width: 200px; float: left;margin: 20px;">
            <p style="text-align: center;">{{ index+1 }}</p>
            <img :src="item.moviePicture " style="height: 150px;width: 150px;">
            <p style="text-align: center; width: 100%; margin-top: 10px;">{{ item.movieCnName }}</p>
            <div style="text-align: center; width: 100%; margin-top: 10px;"><el-button type="text" @click="handleEdit(item.movieId)">修改</el-button>
                <el-button type="text" @click="changeMovieState(item.movieId)">{{ item.movieState==0? '上架':'下架' }}</el-button></div>
  </el-card>
</el-row>

<el-row style="text-align: center;margin-top: 300px;">
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

<el-dialog :title="title" :visible.sync="dialogFormVisible">
  <el-form :model="form" style="width: 500px;" :rules="rules"  ref="movieForm" label-width="120px">
    <el-form-item label="电影名称(中文)" prop="movieCnName">
      <el-input v-model="form.movieCnName" autocomplete="off" ></el-input>
    </el-form-item>
    <el-form-item label="演员信息:" prop="movieActor">
      <el-input v-model="form.movieActor" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="导演:" prop="movieDirector">
      <el-input v-model="form.movieDirector" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="电影时长:" prop="movieDuration">
      <el-input v-model="form.movieDuration" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="电影类型:" prop="movieType">
      <el-input v-model="form.movieType" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="上映日期:" prop="movieReleasedate">
      <el-date-picker
      v-model="form.movieReleasedate"
      type="date"
      placeholder="选择日期:">
    </el-date-picker>
    </el-form-item>
    <el-form-item label="制片地区:" prop="movieCountry" >
      <el-input v-model="form.movieCountry" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="电影详情:" prop="movieDetail">
     <el-input
        type="textarea"
        :rows="4"
        placeholder="请输入电影详情"
        v-model="form.movieDetail">
     </el-input>
    </el-form-item>


    <el-form-item label="电影海报:" prop="moviePicture">
      <el-upload
       v-model="form.moviePicture"
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
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitForm()">确 定</el-button>
  </div>
</el-dialog>
</div>

</template>

<script>
import {movieList,addmovie,getMovie,updateMovie,changeState} from '@/api/system/movie.js';
import { getToken } from "@/utils/auth"
name: 'index'
export default {
    data(){
        return{
        movieList:[],
        queryParam:{
            pageNum:1,
            pageSize:10
        },
        total:"",
        form:{},
        dialogFormVisible: false,
        title:'',
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
       rules: {
          movieCnName: [
            {required:true,message:'请输入电影名称', trigger: 'blur' },
            {min :3,max:20,message:'长度在 3 到 20 个字符',trigger:'blur'}
            
          ],
          movieActor: [
            {required:true,message:'请输入演员信息', trigger: 'blur' },
            
          ],
          movieDirector: [
            {required:true,message:'请输入导演信息', trigger: 'blur' },
            
          ],
          movieDetail: [
            {required:true,message:'请输入电影详情', trigger: 'blur' },
            
          ],
          movieCountry: [
            {required:true,message:'请输入电影制片地区', trigger: 'blur' },
            
          ],
          movieType: [
            {required:true,message:'请输入电影类型', trigger: 'blur' },
          
          ],
          moviePicture: [
            {required:true, message:'请上传电影海报', trigger: 'blur' },
         
          ],
           movieReleasedate: [
            {required:true,message:'请输入电影上映日期', trigger: 'blur' },
           
          ],
           movieDuration: [
            {required:true,message:'请输入电影时长', trigger: 'blur' },
            
          ],
        }
        }
    },
    created(){
        this.getList();
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
        addmovie(){
            this.title="新增电影"
            this.form={}
            this.fileList=[]
            this.dialogFormVisible=true
        },
        handleEdit(id){
            getMovie({"movieId":id}).then(response=>{
                this.form=response.data
                this.dialogFormVisible=true
                this.title="修改电影信息"
                this.fileList.length=0
                this.fileList.push({url:response.data.moviePicture})
            })
        },
        changeMovieState(id){
            this.$modal.confirm("是否要上架或下架该电影").then(function(){
               return  changeState({"movieId":id})
            }).then(response=>{
                if(response.code==200){
                    this.$modal.msgSuccess(response.msg)
                    this.getList()
                }else{
                    this.$modal.msgError(response.msg)
                }
            })
            
        },
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
        this.form.moviePicture=res.url
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
        this.$refs['movieForm'].validate((valid) => {
          if (valid) {
            if(this.form.movieId==null){
                 addmovie(this.form).then(response=>{
                if(response.code==200){
                    this.$modal.msgSuccess(response.msg)
                    this.dialogFormVisible=false
                    this.form={};
                    this.getList()
                }else{
                    this.$modal.msgError(response.msg)
                }
            })
           
          }else{
                //修改
                updateMovie(this.form).then(response=>{
                if(response.code==200){
                    this.$modal.msgSuccess(response.msg)
                    this.dialogFormVisible=false
                    this.form={};
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
  }
}
</script>

<style>

</style>