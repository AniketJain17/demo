import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.myapplication.common.ContextProvider

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App(ContextProvider())
    }
}