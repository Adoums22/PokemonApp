package com.amonteiro.a2023_11_sopra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.amonteiro.a2023_11_sopra.ui.screens.SearchScreen
import com.amonteiro.a2023_11_sopra.ui.theme.A2023_11_sopraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A2023_11_sopraTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {

                    //Démarrage de l'application
                    SearchScreen()
                }
            }
        }
    }
}
