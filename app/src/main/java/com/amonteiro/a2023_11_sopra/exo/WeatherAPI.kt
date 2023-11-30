package com.amonteiro.a2023_11_sopra.exo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
//    val weather = WeatherAPI.loadWeather("Nice")
//    println("Il fait ${weather.main.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} m/s")

    repeat(10) {
        var user = WeatherAPI.loadRandomUser()
        println(user)
        println("Il s'appelle ${user.name} pour le contacter\nPhone : ${user.coord?.phone ?: "-"}\nMail : ${user.coord?.mail ?: "-"}")

        println("Il s'appelle ${user.name} pour le contacter${if(!user.coord?.phone.isNullOrBlank()) ("\nPhone : " +  user.coord?.phone) else ""}\nMail : ${user
            .coord?.mail ?: "-"}")
    }
}

const val URL_API = "https://api.openweathermap.org/data/2.5"

object WeatherAPI {

    val client = OkHttpClient()
    val gson = Gson()


    fun loadWeather(cityName: String): WeatherBean {
        //récupération des données
        val json = sendGet("$URL_API/weather?q=$cityName&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        //Parsing
        val data: WeatherBean = gson.fromJson(json, WeatherBean::class.java)
        return data
    }

    fun loadRandomUser(): UserBean {
        //récupération des données
        val json :String = sendGet("https://www.amonteiro.fr/api/randomuser")

        //Parsing
        val data: UserBean = gson.fromJson(json, UserBean::class.java)
        return data
    }




    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    private fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
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
/* -------------------------------- */
// Weather
/* -------------------------------- */
data class WeatherBean(var main:TempBean, var name:String, var wind : WindBean)
data class TempBean(var temp:Double)
data class WindBean(var speed:Double)

/* -------------------------------- */
// RandomUser
/* -------------------------------- */

data class UserBean(var name : String, var age : Int, var coord : CoordBean?)
data class CoordBean(var phone : String?, var mail : String?)

