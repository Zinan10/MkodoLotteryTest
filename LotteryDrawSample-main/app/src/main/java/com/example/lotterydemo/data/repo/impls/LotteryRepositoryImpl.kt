package com.example.lotterydemo.data.repo.impls

import android.content.Context
import android.content.res.Resources
import androidx.annotation.RawRes
import com.example.lotterydemo.R
import com.example.lotterydemo.data.models.Draw
import com.example.lotterydemo.data.models.LotteryDrawResponse
import com.example.lotterydemo.data.repo.LotteryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class NoDrawFound(private val drawId: String) : Exception() {

    override fun getLocalizedMessage(): String {
        return "No draw found for $drawId"
    }

    override val message: String
        get() = localizedMessage
}

class LotteryRepositoryImpl(
    private val dispatcher: CoroutineDispatcher
) : LotteryRepository {

    private val drawList = mutableListOf<Draw>()

    override suspend fun getLotteryDrawList(resources: Resources): Result<List<Draw>> {
        return withContext(dispatcher) {
            try {
                val response = parseJsonFromRaw(resources)
                drawList.clear()
                drawList.addAll(response.draws ?: emptyList())
                Result.success(drawList)
            } catch (exe: Exception) {
                Result.failure(exe)
            }
        }
    }

    override suspend fun getLotteryDrawInfo(drawId: String): Result<Draw> {
        return withContext(dispatcher) {
            drawList.firstOrNull { it.id == drawId }?.let {
                Result.success(it)
            } ?: run {
                Result.failure(NoDrawFound(drawId = drawId))
            }
        }
    }

    private fun parseJsonFromRaw(resources: Resources): LotteryDrawResponse {
        val inputStream = resources.openRawResource(R.raw.lottery)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.readText()
        return Json.decodeFromString<LotteryDrawResponse>(jsonString)
    }

}