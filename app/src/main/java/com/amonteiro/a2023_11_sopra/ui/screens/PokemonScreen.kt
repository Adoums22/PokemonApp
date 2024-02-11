package com.amonteiro.a2023_11_sopra.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.amonteiro.a2023_11_sopra.Routes
import com.amonteiro.a2023_11_sopra.model.MainViewModel
import com.amonteiro.a2023_11_sopra.ui.theme.A2023_11_sopraTheme

//Code affiché dans la Preview
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PokemonScreenPreview() {
    A2023_11_sopraTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.Gray) {
            PokemonScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun PokemonScreen(navController : NavHostController? = null, viewModel: MainViewModel = viewModel()) {

    //Pour déclancher un élément uniquement en arrivant sur l'écran
    LaunchedEffect("") {
        viewModel.loadPokemonData()
    }


    //Quand c'était une liste de filtre
    viewModel.myList.filter { it.name.contains(viewModel.searchText.value) }



    Column(modifier = Modifier.padding(8.dp)) {

        SearchBar(text = viewModel.searchText.value, onValueChange = {
            viewModel.uploadSearchText(it)
        })

        Spacer(Modifier.size(8.dp))

        if(viewModel.errorMessage.value.isNotBlank()){
            Text(text = viewModel.errorMessage.value,
                color = MaterialTheme.colorScheme.onError,
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.error).padding(8.dp
                )
                )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.myList.size) {
                PictureRowItem(Modifier.background(Color.White),
                    data = viewModel.myList[it],
                    onPictureClick = {
                        viewModel.selectedPokemonResultBean = viewModel.myList[it]
                        //Navigation vers detail
                        navController?.navigate(Routes.DetailScreen.addParam(it))
                    })
            }
        }

        Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
            Button(
                onClick = { viewModel.uploadSearchText("") },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Clear",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear filter")
            }

            Button(
                onClick = {
                    viewModel.loadPokemonData(true)
                          },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Load data",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Load data")
            }
        }


    }
}