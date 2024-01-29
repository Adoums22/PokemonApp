package com.amonteiro.a2023_11_sopra.model

import java.util.Random


const val LONG_TEXT = """Le Lorem Ipsum est simplement
    du faux texte employé dans la composition
    et la mise en page avant impression.
    Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""
data class PictureData(val url: String, val text: String, val longText: String)

//jeu de donnée
val pictureList = arrayListOf(
    PictureData("https://picsum.photos/200", "ABCD", LONG_TEXT),
    PictureData("https://picsum.photos/201", "BCDE", LONG_TEXT),
    PictureData("https://picsum.photos/202", "CDEF", LONG_TEXT),
    PictureData("https://picsum.photos/203", "EFGH", LONG_TEXT)
)


class RandomName {
    private val list = arrayListOf("Toto", "Toto", "Titi")
    private var oldValue = ""

    fun add(name:String) = if(name.isNotBlank() && name !in list) list.add(name) else false

    fun next() = list.random()

    fun nextDiff(): String {
        var newValue = next()
        while(newValue == oldValue){
            newValue = next()
        }
        oldValue = newValue

        return newValue
    }

    fun nextDiff2() =  list.filter { it != oldValue }.random().also{
        oldValue = it
    }




    fun next2() = Pair(nextDiff(), nextDiff())

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