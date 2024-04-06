package com.sixth.space

import com.sixth.space.network.RetrofitService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import org.junit.Assert.*
/**
 * @author: Frankie
 * @Date: 2024/4/7
 * @Description:
 */

@HiltAndroidTest
class RetrofitTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    @Inject
    lateinit var service: RetrofitService;
    @Before
    fun init(){
        hiltRule.inject()
    }
    @Test
    fun test_tiktok_net()= runBlocking {
        val data=service.fetchTiktok("","");
        assertTrue(data.itemList.size>0)
    }
}