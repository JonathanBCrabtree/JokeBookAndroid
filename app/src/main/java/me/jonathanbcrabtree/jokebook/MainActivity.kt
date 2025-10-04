package me.jonathanbcrabtree.jokebook

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.jonathanbcrabtree.jokebook.ui.theme.JokeBookTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Welcome to JokeBook!",
                            modifier = Modifier.padding(innerPadding)
                        )

                        ActivitySwitchButton("Click me to select jokes!", JokeSelectActivity::class.java)
                    }

                }
            }
        }
    }
}

@Composable
fun ActivitySwitchButton(text: String, clazz: Class<*>) {

    val context = LocalContext.current
    val onClick = {
        val intent = Intent(context, clazz)
        context.startActivity(intent)
    }

    Button(onClick) {
        Text(text)
    }
}

