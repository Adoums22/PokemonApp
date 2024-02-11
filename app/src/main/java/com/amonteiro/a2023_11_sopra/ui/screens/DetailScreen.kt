package com.amonteiro.a2023_11_sopra.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.amonteiro.a2023_11_sopra.R
import com.amonteiro.a2023_11_sopra.model.MainViewModel
import com.amonteiro.a2023_11_sopra.ui.theme.A2023_11_sopraTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview(){
    A2023_11_sopraTheme() {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            DetailScreen()
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(navController: NavHostController? = null, viewModel: MainViewModel = viewModel()){

    val pokemonData = viewModel.selectedPokemonResultBean ?: viewModel.myList.first()
    val attackIcon = painterResource(R.drawable.attack)
    val lifeIcon = painterResource(R.drawable.lifebar)
    val speedIcon = painterResource(R.drawable.speed)

// Extract type names from the apiTypes list
    val typeNames = pokemonData.apiTypes.map { it.name }
    val typeImage = pokemonData.apiTypes.map { it.image }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = pokemonData.name,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        GlideImage(
            model = pokemonData.image,
            contentDescription = "Image",
            loading = placeholder(R.mipmap.ic_launcher_round),
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Texte avec image pour chaque type
            pokemonData.apiTypes.forEach { type ->
                TextWithImage(
                    text = type.name,
                    imageUrl = type.image
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Premier texte avec icône
            TextWithIcon(
                text = "Attack:${pokemonData.stats.attack}",
                icon = attackIcon
            )

            // Deuxième texte avec icône
            TextWithIcon(
                text = "Defense:${pokemonData.stats.defense}",
                icon = lifeIcon
            )

            // Troisième texte avec icône
            TextWithIcon(
                text = "Speed:${pokemonData.stats.speed}",
                icon = speedIcon
            )
        }


        Button(
            onClick = { navController?.popBackStack() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Load data",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Retour")
        }
    }
}

@Composable
fun TextWithIcon(text: String, icon: Painter) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "" + text,
            textAlign = TextAlign.Center,
        )
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun TextWithImage(text: String, imageUrl: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
        )
    }
}


