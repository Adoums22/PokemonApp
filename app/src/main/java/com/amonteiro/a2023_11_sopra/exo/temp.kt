package com.amonteiro.a2023_11_sopra.exo


fun main() {

    var list = arrayListOf(
        StudentBean("toto", "De la bassecours", 15),
        StudentBean("toto2", "Bobbys"),
        StudentBean("toto3")
    )



}

class StudentBean(var name: String = "", var surname: String = "", var old: Int = 0)