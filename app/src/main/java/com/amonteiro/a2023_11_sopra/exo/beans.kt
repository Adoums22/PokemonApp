package com.amonteiro.a2023_11_sopra.exo

import java.util.Random

fun main() {
}

object User {
    var name: String = ""
}

class ThermometerBean(var min:Int, var max:Int, value : Int){
    var value : Int = value.coerceIn(min..max)
        set(value) {
            field = value.coerceIn(min..max)
        }


}

class PrintRandomIntBean(val max: Int) {
    private val random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println(random.nextInt(max))
    }

}

class HouseBean(var color: String, width: Int, length: Int) {
    var surface = width * length

    fun print() = "La maison $color fait ${surface}m²"
}

data class CarBean(var marque: String = "", var model: String? = null) {
    var color = ""
}
