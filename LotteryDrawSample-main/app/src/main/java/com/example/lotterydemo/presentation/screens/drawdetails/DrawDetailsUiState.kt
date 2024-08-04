package com.example.lotterydemo.presentation.screens.drawdetails

import androidx.compose.runtime.Immutable
import com.example.lotterydemo.data.models.Draw
import java.time.LocalDate

@Immutable
sealed class DrawDetailsUiState

@Immutable
data object DrawDetailsInitialState : DrawDetailsUiState()

@Immutable
data class DrawDetailsSuccessState(val details: DrawDetailsState) : DrawDetailsUiState()

@Immutable
class DrawDetailsErrorState(val errorMsg: String) : DrawDetailsUiState()


@Immutable
data class DrawDetailsState(
    val id: String,
    val bonusBall: String,
    val number1: String,
    val number2: String,
    val number3: String,
    val number4: String,
    val number5: String,
    val number6: String,
    val topPrize: Long,
    val drawDate: LocalDate?,
)

fun Draw.toDrawDetailsState(): DrawDetailsState {
    return DrawDetailsState(
        id = this.id.orEmpty(),
        bonusBall = this.bonusBall.orEmpty(),
        number1 = this.number1.orEmpty(),
        number2 = this.number2.orEmpty(),
        number3 = this.number3.orEmpty(),
        number4 = this.number4.orEmpty(),
        number5 = this.number5.orEmpty(),
        number6 = this.number6.orEmpty(),
        topPrize = this.topPrize ?: 0,
        drawDate = this.drawDate
    )
}