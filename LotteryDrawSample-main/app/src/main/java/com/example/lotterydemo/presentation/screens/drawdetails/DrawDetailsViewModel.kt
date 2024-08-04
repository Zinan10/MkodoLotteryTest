package com.example.lotterydemo.presentation.screens.drawdetails

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lotterydemo.data.repo.LotteryRepository
import com.example.lotterydemo.modules.ViewModelFactoryProvider
import com.example.lotterydemo.presentation.screens.drawlist.LotteryDrawUiState
import com.example.lotterydemo.presentation.screens.drawlist.LotteryDrawUiSuccessState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@AssistedFactory
interface DrawDetailsViewModelFactory {
    fun create(drawId: String): DrawDetailsViewModel
}

@Composable
fun personalDrawDetailsViewModel(drawId: String): DrawDetailsViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).drawDetailsViewModelFactory()

    return viewModel(
        factory = DrawDetailsViewModel.provideDrawDetailsViewModelFactory(
            factory = factory,
            drawId = drawId
        )
    )
}


class DrawDetailsViewModel @AssistedInject constructor(
    @Assisted
    private val drawId: String,
    private val lotteryRepository: LotteryRepository
) : ViewModel() {


    private val _state =
        MutableStateFlow<DrawDetailsUiState>(DrawDetailsInitialState)
    val state: StateFlow<DrawDetailsUiState> = _state.asStateFlow()

    companion object {
        fun provideDrawDetailsViewModelFactory(
            factory: DrawDetailsViewModelFactory,
            drawId: String,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(drawId) as T
                }
            }
        }
    }

    init {
        loadDrawDetails()
    }

    private fun loadDrawDetails() {
        viewModelScope.launch {
            lotteryRepository.getLotteryDrawInfo(drawId)
                .onSuccess { result ->
                    _state.update {
                        DrawDetailsSuccessState(details = result.toDrawDetailsState())
                    }
                }
                .onFailure { error ->
                    _state.update {
                        DrawDetailsErrorState(errorMsg = error.message.orEmpty())
                    }
                }
        }
    }

}