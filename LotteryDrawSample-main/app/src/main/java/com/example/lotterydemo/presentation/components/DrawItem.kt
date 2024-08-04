package com.example.lotterydemo.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lotterydemo.data.models.Draw
import com.example.lotterydemo.presentation.screens.drawlist.DrawItemModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DrawItem(draw: DrawItemModel, onDrawItemClicked: (String) -> Unit) {
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .dashedBorder(
                color = Color.Red,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onDrawItemClicked(draw.id.orEmpty())
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //
                Text(
                    text = "Top Prize",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                //
                Text(
                    text = draw.topPrize.toString(),
                    color = Color.Gray,
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //
                Text(
                    text = "Date",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                //
                Text(
                    text = draw.drawDate?.format(format).orEmpty(),
                    color = Color.Gray,
                )
            }
        }
    }
}

fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 1.dp,
    dashWidth: Dp = 4.dp,
    gapWidth: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, this)

    val path = Path()
    path.addOutline(outline)

    val stroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashWidth.toPx(), gapWidth.toPx()),
            phase = 0f
        )
    )

    this.drawContent()

    drawPath(
        path = path,
        style = stroke,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun DrawItemPreview() {
    DrawItem(
        draw = DrawItemModel(
            id = "test",
            drawDate = LocalDate.now(),
            topPrize = 10000L
        ),
        onDrawItemClicked = {}
    )
}