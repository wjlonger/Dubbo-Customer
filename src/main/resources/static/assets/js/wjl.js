;NProgress.configure({ ease: 'ease', speed: 500,showSpinner: true,trickleRate: 0.2, trickleSpeed: 100 });
Vue.prototype.showNotification = function (data) {
    $.notify({
        icon: 'mdi mdi-signal-variant',
        message: data.msg
    },{
        type: (data.code == 1 ? 'success' : 'danger'),
        timer: 50,
        placement: {
            from: 'top',
            align: 'center'
        }
    });
};
Vue.prototype.transformRequest = function(json,separator){
    if(typeof separator === 'undefined') separator = "&";
    var arr = new Array();
    for(var i in json){
        switch (typeof json[i]){
            case "string":
            case "number":
            case "boolean":
                arr.push(i + '=' + json[i]);
                break;
            case "undefined":
            case "object":
            case "function":break;
            default:break;
        }
    }
    return arr.join(separator);
}
$(function () {
    NProgress.start();
});
$(window).load(function(){
    NProgress.done();
});
$(window).ajaxStart(function () {
    NProgress.start();
});

$(window).ajaxStop(function () {
    NProgress.done();
});
axios.interceptors.request.use(function(config){
    NProgress.start();
    return config;
},function(err){
    NProgress.done();
    return Promise.reject(error);
});
//添加一个响应拦截器
axios.interceptors.response.use(function(res){
    NProgress.done();
    new Vue().showNotification(res.data)
    return res;
},function(err){
    NProgress.done();
    return Promise.reject(error);
});

