package com.example.lotterydemo.data.repo

import android.content.Context
import android.content.res.Resources
import com.example.lotterydemo.data.models.Draw


interface LotteryRepository {

    suspend fun getLotteryDrawList(resources: Resources): Result<List<Draw>>

    suspend fun getLotteryDrawInfo(drawId: String): Result<Draw>

}
