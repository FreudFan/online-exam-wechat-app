// pages/worrySet/worrySet.js
const app = getApp()
var utils = require("../../utils/util.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
      worrySet:[],
      courseName:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var self=this
      console.log(options)
      var courseId=options.courseId
      var courseName = options.courseName
      this.setData({
        courseName:[courseName],
      })
      wx.request({
        url: app.globalData.url.worrySetShow,
        method: 'POST',
        data: {
          option:{
              subject_id: courseId,
            }
        },
        header: {
          'Authorization': app.globalData.token
        },
        success: function (res) {
          console.log(res)
          if (res.data.length != 0) {
            self.setData({
              worrySet: res.data.rows,
            })
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