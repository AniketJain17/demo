import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.myapplication.common.ContextProvider

actual fun getPlatformName(): String = "Desktop"

@Preview
@Composable
fun AppPreview() {
    App(ContextProvider())
}