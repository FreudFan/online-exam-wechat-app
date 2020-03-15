// pages/history/history.js
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
      url: 'http://192.168.31.168:8888/rest/exam/record/showSubject',
      data:{
        userId:'1'
      },
      header: {
        'content-type': 'application/json'
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