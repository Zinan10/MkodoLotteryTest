package com.example.lotterydemo.presentation.screens.drawlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lotterydemo.data.models.Draw
import com.example.lotterydemo.presentation.components.DrawItem
import java.time.LocalDate

@Composable
fun LotteryDrawListScreen(
    viewModel: LotteryListViewModel,
    onDrawItemClicked: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MainLayout(
        state = state,
        onDrawItemClicked = onDrawItemClicked
    )
}


@Composable
private fun MainLayout(
    state: LotteryDrawUiState,
    onDrawItemClicked: (String) -> Unit
) {

    when (state) {

        is LotteryDrawUiErrorState -> {
            //todo show error state
        }

        is LotteryDrawUiSuccessState -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        text = "Lottery Draws",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                items(state.draws) { draw ->
                    DrawItem(
                        draw = draw,
                        onDrawItemClicked = onDrawItemClicked
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MainLayoutPreview() {
    MainLayout(
        state = LotteryDrawUiSuccessState(
            draws = listOf(
                DrawItemModel(
                    id = "test",
                    drawDate = LocalDate.now(),
                    topPrize = 10000L
                ),
                DrawItemModel(
                    id = "test",
                    drawDate = LocalDate.now(),
                    topPrize = 10000L
                ),
                DrawItemModel(
                    id = "test",
                    drawDate = LocalDate.now(),
                    topPrize = 10000L
                ), DrawItemModel(
                    id = "test",
                    drawDate = LocalDate.now(),
                    topPrize = 10000L
                ), DrawItemModel(
                    id = "test",
                    drawDate = LocalDate.now(),
                    topPrize = 10000L
                ),
                DrawItemModel(
                    id = "test",
                    drawDate = LocalDate.now(),
                    topPrize = 10000L
                )
            )
        ),
        onDrawItemClicked = {}
    )
}