// pages/loading/loading.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '加载中',
    })

    wx.login({
      success(res) {
        console.log(res.code);
        if (res.code) {
          //发起网络请求
          wx.request({
            url: 'http://192.168.31.168:8888/rest/auth/wechat/login',
            method:'POST',
            data: {
              code: res.code
            },
            header: {
              'content-type': 'application/json'
            },
            success:function(user) {
              console.log(user.data);
              /*if(res.data==true){
                wx.reLaunch({
                  url: '/pages/exam/exam',
                })
              }
              else if(res.data){ 
                wx.reLaunch({
                  url: '/pages/register/register',
                })
              }*/
            }
          })
        } else {
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