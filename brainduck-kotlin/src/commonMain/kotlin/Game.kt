@file:OptIn(ExperimentalJsExport::class)

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

@JsExport
@JsName("Game")
class Game {
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score
    @JsName("points")
    var points: Int = 0

    @JsName("elements")
    var elements: dynamic = js("[]")

    fun onScoreChanged(callback: (Int) -> Unit) {
//        scope.launch {
//            score.collect {
//                callback(it)
//            }
//        }
    }

    fun addPoint() {
        _score.value += 1
        elements.push(points)
        elements[0] += 1
        points++
    }
}

class FlowCallback<T>(
    private val flow: Flow<T>,
    private val scope: CoroutineScope,
) {
    fun subscribe(
        scope: CoroutineScope,
        onEach: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onCompletion: (() -> Unit)? = null,
    ): Job {
        return flow
            .let { flow -> onEach?.let { flow.onEach { onEach(it) } } ?: flow }
            .let { flow -> onCompletion?.let { flow.onCompletion { onCompletion() } } ?: flow }
            .let { flow -> onError?.let { flow.catch { onError(it) } } ?: flow }
            .launchIn(scope)
    }
}