// pages/test/test.js
const app=getApp()
var utils = require("../../utils/util.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    subject:null,
    testPaper:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self=this
    var courseId=options.id
    var difficulty = options.difficulty
    console.log(courseId)
    wx.request({
      url: app.globalData.url.show,
      method:'POST',
      data:{
        option:{
                subject_id:courseId,
                difficult:difficulty,
                flag:1
                },
      },
      header:{
        'Authorization': app.globalData.token
      },
      success:function(res){
        console.log(res)
        self.setData({
          testPaper: res.data.rows,
          subject:res.data.rows[0].subjectName
        })
      }
    })
  },
  doTest:function(e){
    let index = e.currentTarget.dataset.index
    console.log(index)
    let paperId=this.data.testPaper[index].id
    wx.navigateTo({
      url: "/pages/doTest/doTest?id=" + paperId,
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