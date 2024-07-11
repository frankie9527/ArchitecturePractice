package com.sixth.space.data.dao

/**
 * @author: Frankie
 * @Date: 2024/3/9
 * @Description:
 */
class VideoRepositoryImpl constructor(private val dao:VideoDao) :VideoRepository{
    override fun getAll(): List<VideoInfo> {
         return dao.getAll()
    }

    override fun insertAll(list: List<VideoInfo>) {
        dao.insertAll(list)
    }

    override fun getVideoByKeyWord(str: String) :List<VideoInfo>{
      return   dao.getVideoByKeyWord(str)
    }
}