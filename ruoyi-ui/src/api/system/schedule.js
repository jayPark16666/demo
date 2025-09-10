import request from '@/utils/request'
export function getScheduleList(param){
     return request({
        url: '/schedule/list',
        method: 'get',
        params: param
      })
}
export function addSchedule(form){
     return request({
        url: '/schedule/add',
        method: 'post',
       data:form
      })
}
export function updateSchedule(form){
     return request({
        url: '/schedule/update',
        method: 'post',
        data:form
      })
}
export function updateScheduleState(param){
     return request({
        url: '/schedule/updateScheduleState',
        method: 'get',
          params: param
      })
}
export function selectSchedule(param){
     return request({
        url: '/schedule/selectById',
        method: 'post',
        params: param
      })
}
export function getCinemaList(){
     return request({
        url: '/cinema/options',
        method: 'get',
       
      })
}
export function getMovieList(){
     return request({
        url: '/movie/options',
        method: 'get',
       
      })
}
export function getHallList(param){
     return request({
        url: '/hall/options',
        method: 'get',
       params:param
      })
}
export function frontScheduleList(param){
     return request({
        url: '/schedule/frontScheduleList',
        method: 'get',
       params:param
      })
}