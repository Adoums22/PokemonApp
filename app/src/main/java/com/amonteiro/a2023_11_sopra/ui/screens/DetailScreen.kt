package com.amonteiro.a2023_11_sopra.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
            DetailScreen(0)
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(position:Int, navController : NavHostController? = null, viewModel : MainViewModel = viewModel()){

    //V1 avec la position mais on n'est pas sur la bonne liste
    //val pictureData = viewModel.myList[position]
    //V2 on met dans le view model l'élément choisi
    val pictureData = viewModel.selectedPictureData ?: viewModel.myList.first()

    Column(horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier.padding(8.dp)) {

        Text(text = pictureData.text,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp
            )

        GlideImage(
            model = pictureData.url ,
            //Dans string.xml
            contentDescription = "Image",
            //En dur
            //contentDescription = "une photo de chat",
            loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
            // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier.fillMaxWidth().weight(2f)
        )

        Text(pictureData.longText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )

        Button(
            onClick = { navController?.popBackStack() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
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