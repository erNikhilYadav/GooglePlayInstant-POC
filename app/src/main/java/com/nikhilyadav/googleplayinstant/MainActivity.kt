package com.nikhilyadav.googleplayinstant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.instantapps.InstantApps
import com.nikhilyadav.googleplayinstant.ui.theme.GooglePlayInstantTheme

class MainActivity : ComponentActivity() {

    /*
        Dummy Data to display something on screen
    */
    private val quotes = listOf(
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

    /**
     * Intent to launch after the app has been installed.
     */
    private val postInstallIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://instant-app-poc.com/")
    ).addCategory(Intent.CATEGORY_BROWSABLE).putExtras(Bundle().apply {
        putString("The key to", "sending data via intent")
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isInstantApp = InstantApps.getPackageManagerCompat(this).isInstantApp
        setContent {
            GooglePlayInstantTheme {
                var currentQuote by remember { mutableStateOf(quotes.random()) }
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "Running As: ", fontWeight = FontWeight.Bold)
                            Text(text = if (isInstantApp) "Instant App" else "Main App")
                        }

                        Quote(currentQuote)

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(onClick = {
                                currentQuote = quotes.random()
                            }) {
                                Text("Get New")
                            }
                            Button(
                                enabled = isInstantApp,
                                onClick = {
                                    if (isInstantApp) {
                                        InstantApps.showInstallPrompt(
                                            this@MainActivity,
                                            postInstallIntent,
                                            REQUEST_CODE,
                                            REFERRER
                                        )
                                    } else {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Check Settings to deploy as Sample",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }) {
                                Text(text = "Install App")
                            }
                        }

                    }
                }
            }
        }
    }

    companion object {
        private val REFERRER = "MainActivity"
        private val REQUEST_CODE = 7
    }

}

@Composable
fun Quote(quote: String, modifier: Modifier = Modifier) {
    Text(
        text = quote,
        modifier = modifier.padding(16.dp),
        style = MaterialTheme.typography.headlineLarge
    )
}

@Preview(showBackground = true)
@Composable
fun QuotePreview() {
    GooglePlayInstantTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            color = Color.White
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Quote("The only way to do great work is to love what you do.")
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                    }) {
                        Text("Get New")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text("Install App")
                    }
                }

            }
        }
    }
}