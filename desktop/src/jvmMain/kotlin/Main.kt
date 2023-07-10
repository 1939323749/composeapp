import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.snowlie.app.common.App
import cn.snowlie.app.common.Basic
import cn.snowlie.app.common.MyApp
import cn.snowlie.app.common.WaterCounter


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        WaterCounter()
    }
}
