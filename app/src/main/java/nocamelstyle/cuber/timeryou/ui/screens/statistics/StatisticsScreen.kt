package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nocamelstyle.cuber.timeryou.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StatisticsScreen(viewModel: StatisticsVM = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_edit_24),
                    contentDescription = "Add"
                )
            }
        }
    ) { paddings ->
        paddings
        Column {
            TextField(value = state.searchToken, onValueChange = {})
            LazyHorizontalGrid(rows = GridCells.Adaptive(40.dp)) {
                items(state.records, key = { it.id }) { record ->
                    StatisticItemView(record)
                }
            }
            // поиск комментариев
            // добавить время со штрафом и без и со скрамблом и без
            // сортировка по дате и времени
            // поделиться
        }
    }
}