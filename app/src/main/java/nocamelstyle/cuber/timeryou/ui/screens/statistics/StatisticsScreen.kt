package nocamelstyle.cuber.timeryou.ui.screens.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import nocamelstyle.cuber.timeryou.dataset.Record
import nocamelstyle.cuber.timeryou.dataset.defaultCategoryNames
import nocamelstyle.cuber.timeryou.dataset.defaultCubeNames
import nocamelstyle.cuber.timeryou.ui.components.SelectorToolbar
import nocamelstyle.cuber.timeryou.ui.screens.timer.ListAlert

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StatisticsScreen(viewModel: StatisticsVM = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state.collectAsState()

    val openTypeDialog = remember { mutableStateOf(false) }
    val openCategoryDialog = remember { mutableStateOf(false) }
    val remove = remember { mutableStateOf<Record?>(null) }

    LaunchedEffect(Unit) {
        viewModel.handleEvent(StatisticsContract.Event.Resume)
    }

    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    painter = painterResource(R.drawable.baseline_edit_24),
//                    contentDescription = "Add"
//                )
//            }
//        }
    ) { paddings ->
        paddings
        Column {
            SelectorToolbar(
                cubeName = state.cubeName,
                cubeCategory = state.cubeCategory,
                openSettings = { /*TODO*/ },
                selectCube = { openTypeDialog.value = true },
                selectCategory = { openCategoryDialog.value = true }
            )
//            TextField(
//                value = state.searchToken,
//                onValueChange = {},
//                modifier = Modifier.fillMaxWidth()
//            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.records, key = { it.id }) { record ->
                    StatisticItemView(record) {
                        remove.value = record
                    }
                }
            }
            // поиск комментариев
            // добавить время со штрафом и без и со скрамблом и без
            // сортировка по дате и времени
            // поделиться
        }
    }

    ListAlert(
        items = defaultCubeNames.toList(),
        isShow = openTypeDialog.value,
        select = { viewModel.handleEvent(StatisticsContract.Event.SelectType(it)) },
        close = { openTypeDialog.value = false }
    )

    ListAlert(
        items = defaultCategoryNames.toList(),
        isShow = openCategoryDialog.value,
        select = { viewModel.handleEvent(StatisticsContract.Event.SelectCategory(it)) },
        close = { openCategoryDialog.value = false }
    )

    ListAlert(
        items = defaultCubeNames.toList(),
        isShow = openTypeDialog.value,
        select = { viewModel.handleEvent(StatisticsContract.Event.SelectType(it)) },
        close = { openTypeDialog.value = false }
    )

    if (remove.value != null) {
        AlertDialog(
            onDismissRequest = { remove.value = null },
            title = {
                Text(text = "Remove record?")
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.handleEvent(StatisticsContract.Event.Remove(remove.value!!))
                    remove.value = null
                }) {
                    Text("Remove")
                }
            },
            dismissButton = {
                Button(onClick = {
                    remove.value = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun LifecycleEffect(event: Lifecycle.Event, skip: Int = 1, handler: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var skipped by remember { mutableStateOf(0) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, e ->
            if (e == event) {
                if (skipped < skip) {
                    skipped += 1
                } else {
                    handler()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}