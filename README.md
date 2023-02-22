#闲憩
闲憩是一款休闲阅读应用，界面风格主要按照Material Design设计。应用主要有新闻，视频和个人模块
##预览
![image](https://raw.githubusercontent.com/ZengChong500373/Rest/master/img/rest.gif)
###应用技术
####http
* retrofit，rxjava 连用对网络数据进行请求，对返回数据进行转换成对应的javabean

####图片
* 使用glide 对图片进行处理。图片处理同时。对glide进行封装，利于以后更换第三方图片框架

####视频
* 视频使用的是ijkplayer的第三方集成。giraffeplayer。效果还算不错

####数据缓存
* 新闻缓存用okhttp的Interceptor。判断网络是否可用。如果不可用就读取以前的请求数据
* 图片缓存 使用glide 自动缓存
* 收藏或者搜索缓存  吧http获取到的javabean转成收藏的Collectionbean 存储在数据库中！

####数据库设计
* docId 唯一性表示，新闻的是docId，视频使用的MP4地址
* isCollection Boolean变量。如果为false表示这个对象没有收藏。
* imgList  如果是多张图片的新闻布局，就把多张图片的地址转换成String存放[{"img":"1"},{"img":"1"},{"img":"1"}]
