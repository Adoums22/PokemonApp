package com.amonteiro.a2023_11_sopra.exo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {

    val foodList = MexicanFoodAPI.loadFoodList()
    println(foodList)


//    val food = MexicanFoodAPI.loadFood("6")
//
//    println("Recette : ${food.title}\nDifficulté : ${food.difficulty}")
//
}

object MexicanFoodAPI {

    val client = OkHttpClient()
    val gson = Gson()

    private const val URL_API = "https://the-mexican-food-db.p.rapidapi.com"


    fun loadFood(id: String): MexicanFoodDetailBean {
        //récupération des données
        val json = sendGet("/$id")

        //Parsing
        return  gson.fromJson(json, MexicanFoodDetailBean::class.java)
    }

    fun loadFoodList(): List<MexicanFoodResultBean> {
        //récupération des données
        val json = sendGet("")

        //Parsing
        return  gson.fromJson(json, Array<MexicanFoodResultBean>::class.java).toList()
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
            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("X-RapidAPI-Host", "the-mexican-food-db.p.rapidapi.com")
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

data class MexicanFoodDetailBean(
    var description: String,
    var difficulty: String,
    var id: String,
    var image: String,
    var ingredients: List<String>,
    var method: List<StepsBean>,
    var portion: String,
    var time: String,
    var title: String
)

data class StepsBean(
    @SerializedName("Step 1")
    var step1: String?,
    @SerializedName("Step 2")
    var step2: String?,
    @SerializedName("Step 3")
    var step3: String?,
    @SerializedName("Step 4")
    var step4: String?,
    @SerializedName("Step 5")
    var step5: String?,
)


data class MexicanFoodResultBean(
    var difficulty: String,
    var id: String,
    var image: String,
    var title: String
)