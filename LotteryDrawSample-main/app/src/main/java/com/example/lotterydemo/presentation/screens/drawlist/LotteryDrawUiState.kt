package com.example.lotterydemo.presentation.screens.drawlist

import androidx.compose.runtime.Immutable
import com.example.lotterydemo.data.models.Draw
import com.example.lotterydemo.data.parser.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate


@Immutable
sealed class LotteryDrawUiState

@Immutable
data class LotteryDrawUiSuccessState(val draws: List<DrawItemModel>) : LotteryDrawUiState()

data class LotteryDrawUiErrorState(val errorMsg: String) : LotteryDrawUiState()

@Immutable
data class DrawItemModel(
    val id: String,
    val drawDate: LocalDate?,
    val topPrize: Long?
)