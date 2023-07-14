import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.snowlie.app.common.InputBox


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        InputBox()
    }
}
