package com.example.lotterydemo.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LotteryDrawResponse(
    @SerialName("draws")
    val draws: List<Draw>?
)