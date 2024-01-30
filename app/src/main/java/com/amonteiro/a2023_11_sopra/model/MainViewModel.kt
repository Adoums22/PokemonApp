package com.amonteiro.a2023_11_sopra.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amonteiro.a2023_11_sopra.exo.MexicanFoodAPI
import com.amonteiro.a2023_11_sopra.exo.WeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //Mutablestatof pour que le composant graphqiue puisse venir observer les changements de valeurs
    val searchText = mutableStateOf("")
    val errorMessage = mutableStateOf("")

    //by permet d'eviter d'écrire les .value
    var runInProgress by mutableStateOf(false)
    val myList = mutableStateListOf<PictureData>()

    var selectedPictureData: PictureData? = null


    fun uploadSearchText(newText: String) {
        searchText.value = newText
    }

    //force : Permet de forcer le rechargement des données
    fun loadData(force: Boolean = false) {//Simulation de chargement de donnée

        errorMessage.value = ""

        if (myList.isEmpty() || force) {

            runInProgress = true
            myList.clear()

            //Tache asynchrone
            viewModelScope.launch(Dispatchers.Default) {
                try {
                    //Ma requête ici
                    val weatherList = WeatherAPI.loadWeatherAround(searchText.value)

                    //Version oldschool
//                for (weatherBean in weatherList) {
//                    val pictureData =
//                    myList.add(pictureData)
//                }

                    myList.addAll(weatherList.map {
                        PictureData(
                            it.weather.getOrNull(0)?.icon ?: "",
                            it.name,
                            "Il fait ${it.main.temp}° à ${it.name} avec un vent de ${it.wind.speed} m/s"
                        )
                    })
                }
                catch (e: Exception) {
                    e.printStackTrace()//affiche la stacktrace de l'erreur dans la console
                    errorMessage.value = "Une erreur est survenue : ${e.message}"
                }

                //Version donnée en dur
                //myList.addAll(pictureList.shuffled()) //Charge la liste en mode mélangé
                runInProgress = false
            }
        }
    }

    fun loadMexicanFoodData(force: Boolean = false) {//Simulation de chargement de donnée

        errorMessage.value = ""

        if (myList.isEmpty() || force) {

            runInProgress = true
            myList.clear()

            //Tache asynchrone
            viewModelScope.launch(Dispatchers.Default) {
                try {
                    //Ma requête ici
                    val list = MexicanFoodAPI.loadFoodList()
                    myList.addAll(list.map {
                        PictureData(
                            it.image,
                            it.title,
                            "Difficulty : ${it.difficulty}"
                        )
                    })
                }
                catch (e: Exception) {
                    e.printStackTrace()//affiche la stacktrace de l'erreur dans la console
                    errorMessage.value = "Une erreur est survenue : ${e.message}"
                }

                //Version donnée en dur
                //myList.addAll(pictureList.shuffled()) //Charge la liste en mode mélangé
                runInProgress = false
            }
        }
    }

}