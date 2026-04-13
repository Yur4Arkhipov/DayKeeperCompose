package com.example.daykeepercompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.daykeepercompose.ui.model.TaskUi
import kotlin.math.roundToInt

@Composable
fun TimelineTable(
    modifier: Modifier = Modifier,
    tasks: List<TaskUi>,
    pixelsPerMinute: Float = 3.0f,
    onTaskClick: (TaskUi) -> Unit = {}
) {
    val density = LocalDensity.current
    val totalMinutes = 24 * 60
    val scrollHeightPx = totalMinutes * pixelsPerMinute
    val scrollHeightDp = with(density) { scrollHeightPx.toDp() }

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .height(scrollHeightDp)
            .verticalScroll(scrollState)
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
        Column {
            repeat(24) { hour ->
                val hourHeightPx = 60f * pixelsPerMinute
                val hourHeightDp = with(density) { hourHeightPx.toDp() }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(hourHeightDp)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.align(Alignment.TopCenter),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    Text(
                        text = "%02d:00".format(hour),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(60.dp)
                            .padding(end = 8.dp),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }

        tasks.forEach { task ->
            val topOffsetPx = task.startMinute * pixelsPerMinute
            val heightPx = task.durationMinutes * pixelsPerMinute
            val minHeightToShowText = 40.dp

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = topOffsetPx.roundToInt()
                        )
                    }
                    .padding(start = 64.dp, end = 8.dp)
                    .fillMaxWidth()
                    .height(with(density) { heightPx.toDp() })
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Blue)
                    .clickable { onTaskClick(task) }
            ) {
                if (with(density) { heightPx.toDp() } >= minHeightToShowText) {
                    Text(
                        text = task.name,
                        modifier = Modifier.padding(8.dp),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}