package net.zomis.brainduck.old

interface MemoryCell {
    val address: Int
    var value: Int
    var labels: MutableMap<String, Int>
}
