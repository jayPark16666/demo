import request from '@/utils/request'
export function getCinemaList(param){
     return request({
        url: '/cinema/list',
        method: 'get',
        params: param
      })
}
export function addCinema(form){
     return request({
        url: '/cinema/add',
        method: 'post',
       data:form
      })
}
export function updateCinema(form){
     return request({
        url: '/cinema/update',
        method: 'post',
        data:form
      })
}
export function deleteCinema(param){
     return request({
        url: '/cinema/delete',
        method: 'post',
        params: param
      })
}
export function selectCinema(param){
     return request({
        url: '/cinema/selectById',
        method: 'post',
        params: param
      })
}
export function frontCinemaList(param){
     return request({
        url: '/cinema/frontList',
        method: 'post',
        params: param
      })
}