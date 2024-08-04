package com.example.lotterydemo.presentation.screens.drawdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lotterydemo.presentation.components.dashedBorder
import java.time.format.DateTimeFormatter

@Composable
fun DrawDetailsScreen(viewModel: DrawDetailsViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainLayout(state = state)
}

@Composable
fun MainLayout(state: DrawDetailsUiState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {

        when (state) {
            is DrawDetailsErrorState -> {
                // handle error state
            }

            DrawDetailsInitialState -> {
                //handle loading state
            }

            is DrawDetailsSuccessState -> {
                DrawDetailState(state = state.details)
            }
        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DrawDetailState(state: DrawDetailsState) {
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .dashedBorder(
                color = Color.Red,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//
            Text(
                text = "Top Prize",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            //
            Text(
                text = state.topPrize.toString(),
                color = Color.Gray,
                fontSize = 18.sp
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "Bonus Ball",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                DrawBall(
                    size = 50.dp,
                    number = state.bonusBall
                )
            }

            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = state.drawDate?.format(format).orEmpty(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Text(
            text = "Numbers",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        FlowRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                state.number1,
                state.number2,
                state.number3,
                state.number4,
                state.number5,
                state.number6
            ).filter { it.isNotEmpty() }.forEach {
                DrawBall(
                    size = 50.dp,
                    number = it
                )
            }
        }
    }
}

@Composable
private fun DrawBall(size: Dp, number: String) {
    Text(
        modifier = Modifier
            .then(
                Modifier.size(size)
            )
            .drawBehind {
                drawCircle(
                    color = Color.Black
                )
            }
            .padding(10.dp),
        text = number,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}
