// pages/exam/exam.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    /*courseList: [
      {
        id: 3,
        name: '离散数学',
        showActionsheet: false
      },
      {
        id: 2,
        name: '数据结构',
        showActionsheet: false
      }
    ],*/
    courseList: [],
    groups: [
      { text: '简单', value: 0 },
      { text: '普通', value: 1 },
      { text: '困难', type: 'warn', value: 2 }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getCourseId();
  },
  getCourseId: function () {
    var self = this;
    wx.request({
      url: app.globalData.url.subShow,
      header: {
        'Authorization': app.globalData.token
      },
      method: 'POST',
      // success:function(res){
      success:res => {
        console.log(res);
        let courses = res.data
        let courseList = []
        courses.forEach(courseItem =>{
          let course={
            id: courseItem.id,
            name: courseItem.name,
            showActionsheet:false
          }
          courseList.push(course)
        })
        self.setData({
          courseList: courseList,
        })
        console.log(courseList)
      }
    })
  },
  openActionSheet:function(e){
    // debugger
    console.log(e)
    let changeActionSheetShow = 'courseList[' + e.currentTarget.dataset.index +'].showActionsheet'
    this.setData({
      [changeActionSheetShow]:true
    })
  },

  close: function (index) {
    let change='courseList['+index+'].showActionsheet'
    this.setData({
      [change]: false
    })
  },

  openCourse(e) {
    console.log("发生点击：",e.currentTarget.dataset)
    console.log("内容：", e.detail)
    let index = e.currentTarget.dataset.listid
    let value = e.detail.value
    let courseId = this.data.courseList[index].id
    console.log("课程id：",courseId)
    this.close(index)
     wx.navigateTo({
       url: "/pages/test/test?id=" + courseId + "&difficulty="+value+"",
     })
  }
})