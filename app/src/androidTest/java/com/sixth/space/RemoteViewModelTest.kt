package com.sixth.space

import com.sixth.space.data.DataRepositorySource
import com.sixth.space.model.RemoteViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import org.junit.Assert.*
import kotlinx.coroutines.delay
import org.junit.After

/**
 * @author: Frankie
 * @Date: 2024/4/7
 * @Description:
 */
@HiltAndroidTest
class RemoteViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dataRepositoryRepository: DataRepositorySource
    lateinit var viewModel: RemoteViewModel;

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = RemoteViewModel(dataRepositoryRepository)
    }

    @After
    fun release() {

    }

    @Test
    fun test_hot_data_function() = runBlocking {
        viewModel.fetchHotData(0)
        delay(1000)
        val dataSize = viewModel.recipesHotData.value?.data?.size!! > 0
        assertTrue(dataSize)
    }

    @Test
    fun test_reply_comment_function() = runBlocking {
        viewModel.fetchReplyComment("322130")
        delay(1000)
        val dataSize = viewModel.recipesReplyData.value?.data?.size!! > 0
        assertTrue(dataSize)
    }

    @Test
    fun test_recommend_function() = runBlocking {
        viewModel.fetchRecommend("322130")
        delay(1000)
        val dataSize = viewModel.recipesRecommendData.value?.data?.size!! > 0
        assertTrue(dataSize)
    }

    @Test
    fun test_tiktok_function(): Unit = runBlocking {
        viewModel.fetchTiktokData(0)
        delay(1000)
        val dataSize = viewModel.recipesTiktokData.value?.data?.size!! > 0
        assertTrue(dataSize)
    }

}
