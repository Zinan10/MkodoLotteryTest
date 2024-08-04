package com.example.lotterydemo.presentation.screens.drawlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotterydemo.R
import com.example.lotterydemo.data.repo.LotteryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LotteryListViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val lotteryRepository: LotteryRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<LotteryDrawUiState>(LotteryDrawUiSuccessState(draws = emptyList()))
    val state: StateFlow<LotteryDrawUiState> = _state.asStateFlow()

    init {
        loadLotteryDraws()
    }

    private fun loadLotteryDraws() {
        viewModelScope.launch {
            lotteryRepository.getLotteryDrawList(context.resources).onSuccess { result ->
                _state.update {
                    LotteryDrawUiSuccessState(result.map {
                        DrawItemModel(
                            id = it.id.orEmpty(),
                            drawDate = it.drawDate,
                            topPrize = it.topPrize
                        )
                    })
                }
            }.onFailure { error ->
                _state.update {
                    LotteryDrawUiErrorState(errorMsg = error.message ?: "something went wrong")
                }
            }
        }
    }

}