package com.example.quizapplication1.data

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizApp(questions: List<QuizQuestion>) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }

    val currentQuestion = questions[currentQuestionIndex]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz App") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            )
        }
    ) {innerPadding->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Green)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            Text(text = currentQuestion.question,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Display options
            currentQuestion.options.forEach { option ->
                Button(
                    onClick = { selectedOption = option },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) Color.Blue else Color.Red
                    )
                ) {
                    Text(text = option)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Next button
            Button(
                onClick = {
                    if (selectedOption == currentQuestion.correctAnswer) {
                        score++
                    }
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                        selectedOption = ""
                    } else {
                        // Quiz finished
                        // You can navigate to a result screen or restart the quiz here
                        Toast.makeText(context, "Your score is: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
                    }
                },
                enabled = selectedOption.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
                    .background(color = Color.Magenta)
            ) {
                Text(text = if (currentQuestionIndex < questions.size - 1) "Next" else "Finish")
            }
        }
    }
}


@Composable
fun MyApp(navController: NavHostController){
    val questions = listOf(
        QuizQuestion(
            "What is the capital of France?",
            listOf("London", "Paris", "Berlin", "Rome"),
            "Paris"
        ),
        QuizQuestion(
            "Which planet is known as the Red Planet?",
            listOf("Earth", "Mars", "Jupiter", "Venus"),
            "Mars"
        ),
        QuizQuestion(
            "What is the capital of Kenya?",
            listOf("Nairobi","Kampala","Darlesalaam","mexico"),
            "Nairobi"
        ),
        QuizQuestion(
            "what is the capital of India?",
            listOf("Delhi","Mumbai","Chennai","Kolkata"),
            "Delhi"

            ),
        QuizQuestion(
            "which animal is known as the king of the jungle?",
            listOf("Cheetah","Lion","Elephant","Tiger"),
            "Lion"
        ),
        QuizQuestion(
            "What is the gas we inhale during respiration?",
            listOf("Nitrogen","Oxygen","Hydrogen","carbondioxide"),
            "Oxygen"
        ),

    )

    QuizApp(questions = questions)
}
