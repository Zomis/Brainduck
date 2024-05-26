package net.zomis.brainduck.compose

import androidx.compose.runtime.State

interface MemoryCell {
    val address: Int
    val value: State<Int>
    val labels: State<Map<String, Int>>
}
