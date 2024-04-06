package com.sixth.space

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sixth.space.base.Constant
import com.sixth.space.data.dao.VideoDao
import com.sixth.space.data.dao.VideoDatabase
import com.sixth.space.data.dao.VideoInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * @author: Frankie
 * @Date: 2024/4/7
 * @Description:
 */
@RunWith(AndroidJUnit4::class)
class VideoDaoTest {
    private lateinit var db: VideoDatabase
    private lateinit var dao: VideoDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(
            context,
            VideoDatabase::class.java, "video"
        ).build();
        dao = db.VideoDao();
    }

    @After
    fun release(){
        db.close()
    }
    @Test
    fun deal_video() = runBlocking {
        val list: ArrayList<VideoInfo> = ArrayList()
        list.add(createVideoInfo(0,"hello"))
        list.add(createVideoInfo(1,"nice ,nice"))
        dao.insertAll(list)
        delay(200)
        val data = dao.getAll();
        assertTrue(data.size == 2)
        val videoList = dao.getVideoByKeyWord("hello");
        val info=videoList.get(0)
        assertEquals("hello",info.title)
    }

   fun createVideoInfo(id:Int,str:String):VideoInfo{
       return VideoInfo(
           videoId = id,
           videoType = Constant.recycler_adapter_type_comment,
           title = str,
           description = "",
           playUrl = "",
           blurred = "",
           category = "",
           cover = "",
           user_name = "",
           user_description = "",
           avatar = "",
           releaseTime = 0,
           duration = 0,
           consumption = VideoInfo.Consumption(0, 0, 0),
           likeCount =0,
           commentMsg = ""
       )
   }
}