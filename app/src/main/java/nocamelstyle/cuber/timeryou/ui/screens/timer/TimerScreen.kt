package nocamelstyle.cuber.timeryou.ui.screens.timer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nocamelstyle.cuber.timeryou.R
import nocamelstyle.cuber.timeryou.extensions.toFormattedTime

@Composable
fun TimerScreen() {
    val state = TimerContract.State.getStub()
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        PickCubeTypeToolbar(state)

        Text(
            text = state.scrumble,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            //enter your scramble
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_edit_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            // regenerate scramble
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_refresh_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = state.time.toFormattedTime() ?: "0.00",
                fontSize = 36.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(
                        R.string.timer_screen_dispersion,
                        state.dispersion?.toFormattedTime() ?: "--"
                    )
                )
                Text(
                    text = stringResource(
                        R.string.timer_screen_average_time,
                        state.averageTime?.toFormattedTime() ?: "--"
                    )
                )
                Text(
                    text = stringResource(
                        R.string.timer_screen_best_time,
                        state.bestTime?.toFormattedTime() ?: "--"
                    )
                )
                Text(
                    text = stringResource(
                        R.string.timer_screen_try_count,
                        state.tryCount?.toFormattedTime() ?: "--"
                    )
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "Ao5: ${state.ao5?.toFormattedTime() ?: "--"}")
                Text(text = "Ao12: ${state.ao12?.toFormattedTime() ?: "--"}")
                Text(text = "Ao50: ${state.ao50?.toFormattedTime() ?: "--"}")
                Text(text = "Ao100: ${state.ao100?.toFormattedTime() ?: "--"}")
            }
        }
    }
}

@Composable
fun PickCubeTypeToolbar(state: TimerContract.State) {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // open drawable
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = state.cubeName)
            Text(text = state.cubeCategory)
        }

        // select categories
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.baseline_category_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_TimerScreen() {
    TimerScreen()
}