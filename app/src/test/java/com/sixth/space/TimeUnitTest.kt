package com.sixth.space

import org.junit.Test
import com.sixth.space.uitls.durationToStr
import com.sixth.space.uitls.getTime2String
import org.junit.Assert.*


/**
 * @author: Frankie
 * @Date: 2024/4/7
 * @Description:
 */

class TimeUnitTest {
    @Test
    fun test_time_str(){
        val time=1681840376000L;
        time.getTime2String();
        assertEquals("04-19 01:52",time.getTime2String())
    }
    @Test
    fun test_duration_str(){
        val time=132;
        assertEquals("02:12",time.durationToStr())
    }
}