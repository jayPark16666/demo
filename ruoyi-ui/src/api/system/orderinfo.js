import request from '@/utils/request'

// 查询部门列表
export function getOrderList(param) {
  return request({
    url: '/orderinfo/list',
    method: 'get',
     params: param
  })
}
export function createOrder(form) {
  return request({
    url: '/orderinfo/create',
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
export function findSoldSeat(param) {
  return request({
    url: '/orderinfo/find',
    method: 'get',
     params: param
  })
}