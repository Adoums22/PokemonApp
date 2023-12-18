package com.amonteiro.a2023_11_sopra.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amonteiro.a2023_11_sopra.R
import com.amonteiro.a2023_11_sopra.exo.PictureData
import com.amonteiro.a2023_11_sopra.exo.pictureList
import com.amonteiro.a2023_11_sopra.ui.theme.A2023_11_sopraTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

//Code affiché dans la Preview
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SearchScreenPreview() {
    A2023_11_sopraTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            SearchScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun SearchScreen() {
    Column {
        PictureRowItem(            data = pictureList[0])
    }
}

//Composable affichant 1 PictureData
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureData) {
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
               .widthIn(max= 100.dp)
       )

       Column {
           Text(
               text = data.text,
               fontSize = 20.sp,
               textAlign = TextAlign.Center,
               color = Color.Black
           )
           Text(
               text = data.longText.take(20) + "...",
               fontSize = 14.sp,
               textAlign = TextAlign.Center,
               color = Color.Blue
           )
       }
   }
}