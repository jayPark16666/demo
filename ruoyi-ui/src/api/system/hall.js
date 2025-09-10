import request from '@/utils/request'
export function getHallList(param){
     return request({
        url: '/hall/list',
        method: 'get',
        params: param
      })
}
export function addHall(form){
     return request({
        url: '/hall/add',
        method: 'post',
       data:form
      })
}
export function updateHall(form){
     return request({
        url: '/hall/update',
        method: 'post',
        data:form
      })
}
export function getCinemaList(){
     return request({
        url: '/cinema/options',
        method: 'get',
       
      })
}
export function deleteHall(param){
     return request({
        url: '/hall/delete',
        method: 'post',
        params: param
      })
}
export function selectHall(param){
     return request({
        url: '/hall/selectById',
        method: 'post',
        params: param
      })
}