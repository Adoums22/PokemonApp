package com.amonteiro.a2023_11_sopra.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val searchText =  mutableStateOf("")
    val myList = mutableStateListOf<PictureData>()

    var selectedPictureData : PictureData? = null


    fun uploadSearchText(newText : String){
        searchText.value = newText
    }

    //force : Permet de forcer le rechargement des données
    fun loadData(force :Boolean  = false) {//Simulation de chargement de donnée

        if(myList.isEmpty() || force) {

            myList.clear()

            //Tache asynchrone
            viewModelScope.launch(Dispatchers.Default) {

                //Ma requête ici
                Thread.sleep(1000) //simule temps de la requête


                myList.addAll(pictureList.shuffled()) //Charge la liste en mode mélangé

            }
        }
    }

}