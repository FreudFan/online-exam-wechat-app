const app = getApp();
const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

module.exports = {
  formatTime: formatTime,
  login: login
}
function login(){
  wx.login({
    success(res) {
      if (res.code) {
        wx.request({
          url: app.globalData.url.open,
          method: 'GET',
          header: {
            'Authorization':res.code
          },
          success: function (res) {
            //console.log(res)
            wx.setStorage({
              key: 'token',
              data: res.data,
            })
          }
        })
      } else {
        console.log('登录失败！' + res.errMsg)
      }
    }
  })
}
