import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import net.zomis.brainduck.compose.BrainduckViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: BrainduckViewModel) {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxWidth().onKeyEvent {
            if (it.type == KeyEventType.KeyUp) {
                println(it.key == Key.F9)
            }
            false
        }, horizontalAlignment = Alignment.CenterHorizontally) {
            val focus = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                focus.requestFocus()
            }
            rememberRichTextState()

            RichTextEditor(viewModel.editorState, modifier = Modifier.fillMaxWidth().height(300.dp).focusRequester(focus))

            var v by remember { mutableStateOf("") }
            Button(onClick = {
//                viewModel.editorState.addSpanStyle(SpanStyle(background = Color.Red), textRange = TextRange(10, 20))
                v = v + v.length + "\n"
            }) {
                Text("Test")
            }
            TextField(value = v, onValueChange = {}, modifier = Modifier.fillMaxWidth().height(300.dp), readOnly = true)
            TextField(value = v, onValueChange = {}, modifier = Modifier.fillMaxWidth().height(300.dp), readOnly = true)

//            Toolbar()
//            Row(modifier = Modifier.fillMaxWidth()) {
//                MemoryTape(modifier = Modifier.fillMaxWidth(0.3f))
//                Editor(modifier = Modifier.fillMaxWidth())
//            }
//            Output()
//            Input()
        }
    }
}