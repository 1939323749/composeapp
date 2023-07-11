package cn.snowlie.app.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Red700 = Color(0xffdd0d3c)
val Red800 = Color(0xffd00036)
val Red900 = Color(0xffc20029)
val Red200 = Color(0xfff297a2)
val Red300 = Color(0xffea6d7e)

private val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800,
)
private val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)

@Composable
fun theme() {
    MaterialTheme(
        colors = LightColors,
    ) {
        Surface(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            color = if (isSystemInDarkTheme()) DarkColors.background.copy(0.6f) else LightColors.background.copy(0.6f),
        ) {
            Column(
                modifier = androidx.compose.ui.Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Hello World",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSystemInDarkTheme()) DarkColors.onBackground else LightColors.onBackground,
                    )
                )
            }
        }
    }
}
