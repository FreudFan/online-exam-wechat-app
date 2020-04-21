// pages/register/register.js
const app=getApp();
var utils = require("../../utils/util.js")
Page({

  /**
   * 页面的初始数据
   */
  
  data: {
    //token:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  verifyEmail: function(e){

  },
  verifyPhoneNumber:function(e){
    
  },
  onLoad: function (options) {
    console.log("token值")
    console.log(app.globalData.token)
    /*var self = this
    wx.getStorage({
      key: 'token',
      success: function (res) {
        console.log(res);
        self.setData({
          token: res.data,
        })
      },
    })
    console.log(self.data.token)*/
  },
  register:function(e){ 
    console.log(e.detail.value);
    var form = e.detail.value;
    if (form.password1==form.password2)
    {
            wx.request({
              url: app.globalData.url.register,
              method: 'POST',
              data: {
                username: form.userName,
                password:form.password1,
                realname:form.realName,
                gender:'0',
                email:form.email,
                telephone:form.phoneNumber
              },
              header: {
                'Authorization': app.globalData.token
              },
              success:function(res){
                console.log(res);
                if(res.data.username==form.userName)
                {
                  wx.showToast({
                  title: '注册成功',
                  icon:'success',
                  duration:2000
                  })
                  wx.reLaunch({
                    url: '/pages/exam/exam',
                  })
                }
                else if(res.statusCode==401)
                {
                  wx.showToast({
                    title: '页面已失效',
                    icon: 'none',
                    duration: 2000
                  })
                  /*utils.login()
                  wx.reLaunch({
                    url: '/pages/loading/loading',
                  })*/
                }
              }
            })
    }
    else
    {
      wx.showToast({
        title: '两次密码不同，请重新修改',
        icon: 'none',
        duration: 2000
      })
    }
  }
})