package com.amonteiro.a2023_11_sopra.ui.screens

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.amonteiro.a2023_11_sopra.R
import com.amonteiro.a2023_11_sopra.Routes
import com.amonteiro.a2023_11_sopra.model.MainViewModel
import com.amonteiro.a2023_11_sopra.model.PictureData
import com.amonteiro.a2023_11_sopra.ui.theme.A2023_11_sopraTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

//Code affiché dans la Preview
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    A2023_11_sopraTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.Gray) {
            SearchScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun SearchScreen(navController: NavHostController? = null, viewModel: MainViewModel = viewModel()) {

    //Pour déclancher un élément uniquement en arrivant sur l'écran
//    LaunchedEffect("") {
//        viewModel.loadData()
//    }


    //Quand c'était une liste de filtre
    //val filterList = viewModel.myList.filter { it.text.contains(viewModel.searchText.value) }


    Column(modifier = Modifier.padding(8.dp)) {

        SearchBar(text = viewModel.searchText.value, onValueChange = {
            viewModel.uploadSearchText(it)
        })

        Text("Go to MexicanFood",
            modifier = Modifier.clickable {
                navController?.navigate(Routes.MexicanFoodScreen.route)
            }
        )

        Spacer(Modifier.size(8.dp))

//        if(viewModel.runInProgress) {
//            CircularProgressIndicator()
//        }

        if (viewModel.errorMessage.value.isNotBlank()) {
            Text(
                text = viewModel.errorMessage.value,
                color = MaterialTheme.colorScheme.onError,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.error)
                    .padding(
                        8.dp
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
                        viewModel.selectedPictureData = viewModel.myList[it]
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
                    viewModel.loadData(true)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, text: String, onValueChange: (String) -> Unit) {

    TextField(
        value = text, //Valeur par défaut
        onValueChange = onValueChange, //Action
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        label = { Text("Enter text") }, //Texte d'aide qui se déplace
        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}

//Composable affichant 1 PictureData
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureData, onPictureClick: () -> Unit = {}) {

    var expended by remember { mutableStateOf(false) }

    var texte = if (expended) data.longText else (data.longText.take(20) + "...")

    Row(modifier = modifier.fillMaxWidth()) {

//Permission Internet nécessaire
        GlideImage(
            model = data.url,
            //Dans string.xml
            contentDescription = "Image",
            //En dur
            //contentDescription = "une photo de chat",
            loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
            // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier
                .heightIn(max = 100.dp)
                .widthIn(max = 100.dp)
                .clickable {
                    //Clic sur l'image
                    onPictureClick()
                }
        )

        Column(modifier = Modifier.clickable {
            expended = !expended
        }) {

            Text(
                text = data.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = texte,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.Blue,
                modifier = Modifier.animateContentSize()
            )
        }
    }
}