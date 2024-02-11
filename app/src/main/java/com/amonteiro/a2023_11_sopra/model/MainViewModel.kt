package com.amonteiro.a2023_11_sopra.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amonteiro.a2023_11_sopra.exo.PokemonAPI
import com.amonteiro.a2023_11_sopra.exo.PokemonResultBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //Mutablestatof pour que le composant graphqiue puisse venir observer les changements de valeurs
    val searchText = mutableStateOf("")
    val errorMessage = mutableStateOf("")

    //by permet d'eviter d'écrire les .value
    var runInProgress by mutableStateOf(false)
    val myList = mutableStateListOf<PokemonResultBean>()

    var selectedPokemonResultBean: PokemonResultBean? = null


    fun uploadSearchText(newText: String) {
        Log.d("MainViewModel", "Nouveau texte de recherche: $newText")
        searchText.value = newText
        Log.d("MainViewModel", "Texte de recherche mis à jour: ${searchText.value}")
    }


    fun loadPokemonData(force: Boolean = false) {//Simulation de chargement de donnée

        errorMessage.value = ""

        if (myList.isEmpty() || force) {

            runInProgress = true
            myList.clear()

            //Tache asynchrone
            viewModelScope.launch(Dispatchers.Default) {
                try {
                    //Ma requête ici
                    val list = PokemonAPI.loadPokemonList(searchText.value)
                    myList.addAll(list.map {
                        PokemonResultBean(
                            it.image,
                            it.name,
                            "Pokedex : ${it.pokedexId}",
                            it.stats,
                            it.apiTypes
                        )
                    })

                }
                catch (e: Exception) {
                    e.printStackTrace()//affiche la stacktrace de l'erreur dans la console
                    errorMessage.value = "Une erreur est survenue : ${e.message}"
                }
                runInProgress = false
            }
        }
    }

}