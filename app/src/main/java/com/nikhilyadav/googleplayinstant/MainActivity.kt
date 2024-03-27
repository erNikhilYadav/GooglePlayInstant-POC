package com.nikhilyadav.googleplayinstant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhilyadav.googleplayinstant.ui.theme.GooglePlayInstantTheme

class MainActivity : ComponentActivity() {

    val quotes = listOf(
        "The only way to do great work is to love what you do.",
        "Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it.",
        "Programs must be written for people to read, and only incidentally for machines to execute.",
        "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.",
        "The best way to predict the future is to invent it.",
        "Give a man a program, frustrate him for a day. Teach a man to program, frustrate him for a lifetime.",
        "Don't worry if it doesn't work right. If everything did, you'd be out of a job.",
        "Talk is cheap. Show me the code.",
        "Sometimes it pays to stay in bed on Monday, rather than spending the rest of the week debugging Monday's code.",
        "Walking on water and developing software from a specification are easy if both are frozen."
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GooglePlayInstantTheme {
                var currentQuote by remember { mutableStateOf(quotes.random()) }
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Greeting(currentQuote)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            currentQuote = quotes.random()
                        }) {
                            Text("Click to get Random Quote")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GooglePlayInstantTheme {
        Greeting("Android")
    }
}