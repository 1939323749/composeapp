import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.snowlie.app.common.theme


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        theme()
    }
}
