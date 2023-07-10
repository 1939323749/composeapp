package cn.snowlie.app.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    WellnessScreen(modifier)
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        StatefulCounter(modifier)

        val list = remember { getWellnessTasks().toMutableStateList() }
        WellnessTasksList(list = list, onCloseTask = { task -> list.remove(task) })
    }
}
@Composable
fun WellnessTaskItem(
    taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier, checked: Boolean, onCheckedChange: Any
) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = onClose,
        modifier = modifier,
        onCloseTask = { }
    )
}
@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    onCloseTask: (WellnessTask) -> Unit
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

//设计可组合项的最佳实践是仅向它们传递所需要的参数。
@Composable
fun StatefulCounter(modifier: Modifier) {
    var waterCount by remember { mutableStateOf(0) }

    var juiceCount by remember { mutableStateOf(1) }
    Column{
        StatelessCounter(waterCount, { waterCount++ })
        StatelessCounter(juiceCount, { juiceCount*=2 })
        WellnessTasksList(list = getWellnessTasks(), onCloseTask = { })
    }

}
data class WellnessTask(val id: Int, val label: String)
private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = List(30) { i -> WellnessTask(i, "Task # $i") },
    onCloseTask: (WellnessTask) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) {
            WellnessTaskItem(
                taskName = it.label,
                checked = false,
                onCheckedChange = { },
                onClose = { onCloseTask(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}