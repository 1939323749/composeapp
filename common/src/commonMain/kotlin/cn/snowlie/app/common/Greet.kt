package cn.snowlie.app.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
private fun Greeting(name: String) {
    val expanded= rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    MaterialTheme {
    Surface(
        color = MaterialTheme.colors.primary,

        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp)).fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
            ) {
                Column (
                    modifier = Modifier.weight(1f),
                ){
                    Text(text = "Hello")
                    Text(text = name, style = MaterialTheme.typography.h5)
                }
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expanded.value) {
                            "Collapse"
                        } else {
                            ""
                        }
                    )
                }
            }
        }
    }
}
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = List(10) { "$it" }
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        if (shouldShowOnboarding){
            items(items=names){
                name->
                Greeting(name = name)
            }
        }
        item {
            Button(
                onClick = { shouldShowOnboarding = !shouldShowOnboarding }
            ) {
                Text(if (shouldShowOnboarding) "Show less" else "Show more")
            }
        }
    }
}