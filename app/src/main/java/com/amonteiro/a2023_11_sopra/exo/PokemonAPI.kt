package com.amonteiro.a2023_11_sopra.exo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request

object PokemonAPI {

    val client = OkHttpClient()
    val gson = Gson()

    private const val URL_API = "https://pokebuildapi.fr/api/v1/pokemon"


    fun loadPokemonList(queryParam: String): List<PokemonResultBean> {
        //récupération des données
        val json = sendGet(queryParam)

        //Parsing
        return  gson.fromJson(json, Array<PokemonResultBean>::class.java).toList()
    }



    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    private fun sendGet(url: String): String {
        val finalurl = URL_API + url
        println("url : $finalurl")
        //Création de la requête
        val request = Request.Builder()
            .url(finalurl)
            .get()
            .addHeader("X-RapidAPI-Key", "3565dfef6cmshe140c295e08b7c6p11dee7jsn04b5325368d4")
            .addHeader("X-RapidAPI-Host", "the-mexican-food-db.p.rapidapi.com")
//            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
//            .addHeader("X-RapidAPI-Host", "the-mexican-food-db.p.rapidapi.com")
            .build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}


data class PokemonResultBean(
    var image: String,
    var name: String,
    var pokedexId: String,
    var stats: Stats,
    var apiTypes: List<TypesPokemon>
)

data class Stats (
    var hp: String,
    var attack: String,
    var defense: String,
    var special_attack: String,
    var special_defense: String,
    var speed: String
)

data class TypesPokemon(
    var name: String,
    var image: String
)
