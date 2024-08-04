package com.example.lotterydemo

import android.content.res.Resources
import com.example.lotterydemo.data.repo.impls.LotteryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.ByteArrayInputStream


@OptIn(ExperimentalCoroutinesApi::class)
class LotteryRepositoryImplTest {

    @Mock
    private lateinit var resources: Resources

    private lateinit var repository: LotteryRepositoryImpl

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        repository = LotteryRepositoryImpl(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetLotteryDrawList_Success() = runTest {
        // Mock the private method parseJsonFromRaw to return a successful response
        val jsonString = """
            {
  "draws": [
    {
      "id": "draw-1",
      "drawDate": "2023-05-15",
      "number1": "2",
      "number2": "16",
      "number3": "23",
      "number4": "44",
      "number5": "47",
      "number6": "52",
      "bonus-ball": "14",
      "topPrize": 4000000000
    },
    {
      "id": "draw-2",
      "drawDate": "2023-05-22",
      "number1": "5",
      "number2": "45",
      "number3": "51",
      "number4": "32",
      "number5": "24",
      "number6": "18",
      "bonus-ball": "28",
      "topPrize": 6000000000
    },
    {
      "id": "draw-3",
      "drawDate": "2023-05-29",
      "number1": "34",
      "number2": "21",
      "number3": "4",
      "number4": "58",
      "number5": "1",
      "number6": "15",
      "bonus-ball": "51",
      "topPrize": 6000000000
    }
  ]
}
        """
        val inputStream = ByteArrayInputStream(jsonString.toByteArray())
        Mockito.`when`(resources.openRawResource(R.raw.lottery)).thenReturn(inputStream)

        val result = repository.getLotteryDrawList(resources)
        assertTrue(result.isSuccess)
    }

    @Test
    fun testGetLotteryDetails_Success() = runTest {
        // Mock the private method parseJsonFromRaw to return a successful response
        val jsonString = """
            {
  "draws": [
    {
      "id": "draw-1",
      "drawDate": "2023-05-15",
      "number1": "2",
      "number2": "16",
      "number3": "23",
      "number4": "44",
      "number5": "47",
      "number6": "52",
      "bonus-ball": "14",
      "topPrize": 4000000000
    },
    {
      "id": "draw-2",
      "drawDate": "2023-05-22",
      "number1": "5",
      "number2": "45",
      "number3": "51",
      "number4": "32",
      "number5": "24",
      "number6": "18",
      "bonus-ball": "28",
      "topPrize": 6000000000
    },
    {
      "id": "draw-3",
      "drawDate": "2023-05-29",
      "number1": "34",
      "number2": "21",
      "number3": "4",
      "number4": "58",
      "number5": "1",
      "number6": "15",
      "bonus-ball": "51",
      "topPrize": 6000000000
    }
  ]
}
        """
        val inputStream = ByteArrayInputStream(jsonString.toByteArray())
        Mockito.`when`(resources.openRawResource(R.raw.lottery)).thenReturn(inputStream)

        val drawIdToCheck = "draw-1"
        repository.getLotteryDrawList(resources)
        val result = repository.getLotteryDrawInfo(drawId = drawIdToCheck)
        assertTrue(result.isSuccess)
    }

}