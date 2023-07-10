package cn.snowlie.app.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ){
        animateColorComponent()
    }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ){
        MaterialTheme(
            colors = darkColors(primary = Color.DarkGray),
            shapes = Shapes(small = RoundedCornerShape(30)),
            typography = Typography(FontFamily.Default)
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ){
                var text by remember { mutableStateOf("Hello, World!") }
                val platformName = getPlatformName()

                Button(onClick = {
                    text = "Hello, ${platformName}"

                }) {
                    Text(text)
                }
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.padding(16.dp).
                    scrollable(scrollState, orientation = Orientation.Vertical)
                ) {
                    repeat(100) {
                        // Card composable is a predefined composable that is meant to represent
                        // the card surface as specified by the Material Design specification. We
                        // also configure it to have rounded corners and apply a modifier.
                        Card(
                            backgroundColor = colors.primary,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                    }
                }
                LazyColumn {
                    items(1){
                        samePaddingComponent()
                    }
                    item {
                        customPaddingComponent()
                    }
                    item {
                        animateColorComponent()
                    }
                }


            }
        }


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                var showPopup by remember{ mutableStateOf((false)) }

                Column (Modifier.clickable(onClick = {showPopup=true}), content =  {
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(8.dp).shadow(
                            elevation = 3.dp,
                            shape = RoundedCornerShape(8.dp)
                        ),
                        backgroundColor = Color.LightGray

                    ) {
                        Text(
                            text = "click to see dialog",
                            modifier = Modifier.padding(16.dp),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif
                            )
                        )
                    }

                    var onPopupDismissed = { showPopup = false }
                    if (showPopup) {
                        AlertDialog(
                            onDismissRequest = onPopupDismissed,
                            text = {
                                Text("clicked")
                            },
                            confirmButton = {
                                Button(
                                    onClick = onPopupDismissed
                                ) {
                                    Text(text = "ok")
                                }
                            },
                            modifier = Modifier
                                .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                        )
                    }
                }
                )

            }
        }

    }
@Composable
fun samePaddingComponent(){
    Surface(color = colors.primary) {
        Text(
            text = "This text has 32dp start padding, 4dp end padding, 32dp top padding & 0dp " +
                    "bottom padding padding in each direction",
            modifier = Modifier.padding(start = 20.dp, end = 4.dp, top = 32.dp, bottom = 20.dp),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    blurRadius = 4f,
                    offset = androidx.compose.ui.geometry.Offset(1f, 1f)
                ),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )
        )

    }
}
@Composable
fun customPaddingComponent(){
    Surface {
        Text(
            text = "This text has 32dp start padding, 4dp end padding, 32dp top padding & 0dp " +
                    "bottom padding padding in each direction",
            modifier = Modifier.padding(32.dp, 0.dp, 4.dp, 32.dp),
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)
        )
    }
}
@Composable
fun animateColorComponent(){
    val currentColor = remember { MutableTransitionState(Color.Red) }
    val label="animate color"
    val transition = updateTransition(currentColor, label = label)

    val color by transition.animateColor (

        transitionSpec={ tween(durationMillis = 2000) },
        label = label,
        targetValueByState = {it},
    )

    LaunchedEffect(
        key1 = currentColor.currentState,
    ){
        currentColor.targetState = when(currentColor.currentState){
            Color.Blue -> Color.Red
            else -> Color.Blue
        }
    }
    Column (
        modifier=Modifier
            .fillMaxSize()
            .background(color=color)
    ){  }
}

