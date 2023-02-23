import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.dschumerth.catter.ui.theme.ColorPalette

@Composable
fun CatterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorPalette,
        content = content
    )
}
