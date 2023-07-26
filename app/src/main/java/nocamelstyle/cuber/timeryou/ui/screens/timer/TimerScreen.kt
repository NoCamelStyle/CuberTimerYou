package nocamelstyle.cuber.timeryou.ui.screens.timer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import nocamelstyle.cuber.timeryou.R

@Composable
fun TimerScreen() {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        PickCubeTypeToolbar()
        Text(text = "algorythm")
        Row {
            //enter your scramble
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }
            // regenerate scramble
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }
        }

        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()) {
            Text(text = "0.00")
        }

        Row {
            Column {
                Text(text = "Разброс")
                Text(text = "Среднее")
                Text(text = "Лучшее")
                Text(text = "К-во")
            }
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Column {
                Text(text = "Ao5: --")
                Text(text = "Ao12: --")
                Text(text = "Ao50: --")
                Text(text = "Ao100: --")
            }
        }
    }
}

@Composable
fun PickCubeTypeToolbar() {
    Row {
        // open drawable
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        }

        Column(Modifier.weight(1f)) {
            Text(text = "Куб 4х4")
            Text(text = "normal")
        }

        // select categories
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_TimerScreen() {
    TimerScreen()
}