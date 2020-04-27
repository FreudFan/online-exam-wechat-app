// pages/doTest/doTest.js
const app=getApp()
var utils = require("../../utils/util.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tfList:[],
    singleList:[],
    mutiList:[],
    recordId:null,
    submitList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  
  onLoad: function (options) {
      var self = this
      console.log(options)
      let paperId=options.id
      wx.request({
        url: app.globalData.url.start,//+'?id='+paperId,
        method:'POST',
        data:{
          examId:paperId
        },
        header: {
          'Authorization': app.globalData.token
        },
        success:function(res){
          console.log(res)
          self.setData({
            tfList:res.data.topics[0],
            singleList:res.data.topics[1],
            mutiList:res.data.topics[2],
            recordId:res.data.recordId,
          })
        }
      })
  },
  radioChange:function(e){
    console.log(e)
    let topicId = e.currentTarget.dataset.topicid
    let answer =e.detail.value
    wx.request({
      url: app.globalData.url.save,
      method: "POST",
      data:{
        recordId:this.data.recordId,
        topicId:topicId,
        answer:answer
      },
      header: {
        'Authorization': app.globalData.token
      },
    })

  },
  checkboxChange:function (e) {
    console.log(e)
    let topicId = e.currentTarget.dataset.topicid
    let answerArr = e.detail.value
    let answerStr=""
    for(let i=0;i<answerArr.length;i++){
        answerStr+=answerArr[i]
    }
    wx.request({
      url: app.globalData.url.save,
      method: "POST",
      data: {
        recordId: this.data.recordId,
        topicId: topicId,
        answer: answerStr
      },
      header: {
        'Authorization': app.globalData.token
      },
    })
  },

  formSubmit:function(e){
   var self=this
    var tfList=self.data.tfList
    var sList = self.data.singleList
    var mList=self.data.mutiList
    console.log(e)
    var submitValue=e.detail.value
    var topics=[]
    tfList.forEach(tf=>{
      let key=tf.id
      let topic={
          topicId:key,
          answer:submitValue[key]
      }
      topics.push(topic)
    })
    sList.forEach(sl=> {
      let key = sl.id
      let topic = {
        topicId: key,
        answer: submitValue[key]
      }
      topics.push(topic)
    })
    mList.forEach(ml => {
      let key = ml.id
      console.log(key)
      console.log(submitValue)
      let answerArray = submitValue[key]
      
      let answerString=""
      answerArray.forEach(answer=>{
        answerString+=answer
      })
      let topic = {
        topicId: key,
        answer: answerString
      }
      topics.push(topic)
    })
    console.log(topics)
    let recordId=self.data.recordId
    wx.request({
      url: app.globalData.url.end,
      header: {
        'Authorization': app.globalData.token
      },
      method: 'POST',
      data:{
          recordId:recordId,
          topics:topics
      },
      success:function(res){
        console.log(res)
        if(res.data>=0){
          wx.redirectTo({
            url: '../../pages/showScore/showScore?score='+res.data
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