package com.amonteiro.a2023_11_sopra.exo


fun main() {

    println(scanText("Un prénom"))
    println(scanNumber("Un age"))
    println(scanNumber("Un age(2)"))

}

fun scanText(question: String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question: String) = scanText(question).toIntOrNull() ?: 0


fun boulangerie(nbCroi: Int = 0, nbBaguette: Int = 0, nbSand: Int = 0) = PRICE_BAG * nbBaguette + PRICE_CROI * nbCroi + PRICE_SAND * nbSand


fun myPrint(text: String) = println("#$text#")

fun pair(c: Int) = c % 2 == 0

fun min(a: Int, b: Int, c: Int) = if (a <= b && a <= c) a else if (b <= a && b <= c) b else c


