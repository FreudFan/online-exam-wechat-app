// pages/loading/loading.js
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
  onLoad: function (options) {
    wx.showLoading({
      title: '加载中',
    })
    utils.login()
    /*var self=this
    wx.getStorage({
      key: 'token',
      success: function(res) {
        console.log(res);
        self.setData({
          token: res.data,
        })
      },
    })*/
    wx.login({
      success(res) {
        if (res.code) {
          //console.log(self.data.token),
          wx.request({    
                  url:app.globalData.url.login,
                  data:{
                    code:res.code
                  },
                  method: 'POST',
                  header: {
                    'Authorization':app.globalData.token
                  },
                  success:function(user) {
                    console.log(user);
                    if (user.statusCode == 401){
                      wx.reLaunch({
                        url: '/pages/register/register',
                      })
                    }
                    else if (user.statusCode == 200){ 
                      wx.reLaunch({
                        url: '/pages/exam/exam',
                      })
                    }
                  }
          })
        }else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})