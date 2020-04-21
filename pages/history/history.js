// pages/history/history.js
const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      courseId:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.getCourseId();
  },
  getCourseId:function(){
    var self = this;
    wx.request({
      url:app.globalData.url.showSubject,
      data:{
        userId:'1'
      },
      header: {
        'Authorization': app.globalData.token
      },
      method:'POST',
      success:function(res){
        console.log(res);
        self.setData({
            courseId:res.data,
        })
      }
    })
  },
})