import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.snowlie.app.common.App
import cn.snowlie.app.common.MyApp


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MyApp()
    }
}
