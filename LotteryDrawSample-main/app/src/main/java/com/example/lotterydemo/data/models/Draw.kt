package com.example.lotterydemo.data.models


import com.example.lotterydemo.data.parser.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Draw(
    @SerialName("bonus-ball")
    val bonusBall: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("drawDate")
    val drawDate: LocalDate? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("number1")
    val number1: String? = null,
    @SerialName("number2")
    val number2: String? = null,
    @SerialName("number3")
    val number3: String? = null,
    @SerialName("number4")
    val number4: String? = null,
    @SerialName("number5")
    val number5: String? = null,
    @SerialName("number6")
    val number6: String? = null,
    @SerialName("topPrize")
    val topPrize: Long? = null
)