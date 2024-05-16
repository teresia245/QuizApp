package com.example.quizapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.quizapplication1.navigation.AppNavHost
import com.example.quizapplication1.ui.theme.QuizApplication1Theme
import com .google.firebase.auth.FirebaseAuth



class MainActivity: ComponentActivity(){
    override fun onCreate(savedinstanceState: Bundle?){
        super.onCreate(savedinstanceState)
        setContent {
            QuizApplication1Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    AppNavHost()
                }
            }
        }
    }
}