import request from '@/utils/request'

export function movieList(query) {
  return request({
    url: '/movie/list',
    method: 'get',
    params: query
  })
}

export function addmovie(form) {
  return request({
    url: '/movie/add',
    method: 'post',
    data:form
  })
}
export function getMovie(param) {
  return request({
    url: '/movie/getMovie',
    method: 'post',
   params:param
  })
}
export function updateMovie(form) {
  return request({
    url: '/movie/update',
    method: 'post',
    data:form
  })
}
export function changeState(param) {
  return request({
    url: '/movie/change',
    method: 'post',
    params:param
  })
}