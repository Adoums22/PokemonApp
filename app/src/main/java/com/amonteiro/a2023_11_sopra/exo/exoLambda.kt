package com.amonteiro.a2023_11_sopra.exo

fun main() {
    exo1()
}

fun exo1() {
    var lower: (String) -> Unit = { it: String -> println(it.lowercase()) }
    var lower2 = { it: String -> println(it.lowercase()) }
    var lower3: (String) -> Unit = { it -> println(it.lowercase()) }
    var lower4: (String) -> Unit = { println(it.lowercase()) }

    lower("Coucou")

    var hour: (Int) -> Int = { it / 60 }
    var res: Int = hour(125)
    println(res)

    var max = { a: Int, b: Int -> Math.max(a, b) }
    println("max=${max(5, 6)}")

    var reverse: (String) -> String = { it.reversed() }
    println(reverse("Toto"))

    var minToMinHour : ((Int?) -> Pair<Int, Int>?)? = { if(it != null ) Pair(it/60, it%60) else null}
    println(minToMinHour?.invoke(126))
}
