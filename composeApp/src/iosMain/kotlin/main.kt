import androidx.compose.ui.window.ComposeUIViewController
import org.sam.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
