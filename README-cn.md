中文 | [English](README.md)
# 项目简介
学习 architecture-samples 项目，运用google 最新的技术整合在项目中。此项目是android 架构的尝试性实践
## 使用技术
- [compose](https://developer.android.google.cn/develop/ui/compose/documentation?hl=zh-cn) 国外must掌握技术
- hilt  谷歌推出的依赖注入框架
- room  谷歌推出的数据库
- retrofit2 + okhttp3   网络请求框架
- firebase 谷歌数据采集平台(类似腾讯bugly)
- kotlin + [flow](https://developer.android.google.cn/kotlin/flow?hl=en)
- VariousPlayer  自己封装的播放器，底层是谷歌的exoplayer
- test  用的是junit+hilt   &emsp; [学习junit请点击](https://github.com/frankie9527/AndroidDocumentProject/tree/main/AndroidTestPractice)

## 功能介绍
- 抖音播放界面
- 单独影视播放界面
- 搜索功能
- 缓存功能（无网络下请求走缓存，播放走本地缓存）

## 运行效果
![image](https://raw.githubusercontent.com/Frankie9527/ArchitecturePractice/mvvm/img/sixthspace.gif)