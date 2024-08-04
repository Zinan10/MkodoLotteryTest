package com.example.lotterydemo

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        assertEquals(4, 2 + 2)
    }

    @Test
    fun testParseJsonFromRaw() {
        runBlocking {
            //val mockContext = mock(Context::class.java)
            //val mockDispatcher = mock(CoroutineDispatcher::class.java)
            //val mockLotteryRepository = mock(LotteryRepositoryImpl::class.java)

            /*mockLotteryRepository.getLotteryDrawList().onSuccess {
                println()
            }.onFailure {
                println()
            }*/
        }
    }
}