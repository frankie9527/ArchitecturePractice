package com.sixth.space.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert


/**
 * @author: Frankie
 * @Date: 2024/3/9
 * @Description:
 */
@Dao
interface VideoDao {
    @Query("SELECT * FROM videoinfo")
    fun getAll(): List<VideoInfo>

    @Upsert
    fun insertAll(list: List<VideoInfo>);

    @Query("SELECT * FROM videoinfo WHERE title Like '%' ||:str|| '%'")
    fun getVideoByKeyWord(str:String):List<VideoInfo>

}