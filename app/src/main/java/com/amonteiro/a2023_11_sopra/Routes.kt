package com.amonteiro.a2023_11_sopra

sealed class Routes(val route: String) {

    //Route 1
    object SearchScreen : Routes("homeScreen")

    //Route 2 avec paramètre
    object DetailScreen : Routes("detailScreen/{data}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètre
        fun addParam(position: Int) = "detailScreen/$position"

        //fun first() = "detailScreen/0"
    }
}