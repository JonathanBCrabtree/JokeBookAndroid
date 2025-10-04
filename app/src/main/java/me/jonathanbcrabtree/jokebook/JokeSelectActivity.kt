package me.jonathanbcrabtree.jokebook

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.jonathanbcrabtree.jokebook.ui.theme.JokeBookTheme

class JokeSelectActivity : ComponentActivity() {
    private val viewModel by viewModels<JokeSelectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            JokeBookTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val joke = viewModel.selectedJoke.value

                    if (joke == null) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Choose a joke category!",
                                modifier = Modifier.padding(innerPadding)
                            )

                            ChooseJokesButton("General", "general")
                            ChooseJokesButton("Knock Knock", "knock-knock")
                            ChooseJokesButton("Programming", "programming")
                            Spacer(modifier = Modifier.height(25.dp))

                            LazyColumn {
                                items(viewModel.jokes) { joke ->
                                    val onClick = {
                                        viewModel.selectedJoke.value = joke
                                    }

                                    Button(onClick = onClick) {
                                        Text(joke.setup)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(25.dp))

                            ActivitySwitchButton("Back to title screen", MainActivity::class.java)
                        }
                    } else {

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = joke.setup,
                                modifier = Modifier.padding(innerPadding)
                            )

                            if (viewModel.punchlineRequested.value) {
                                Text(text = joke.punchline)
                            } else {
                                Button({viewModel.punchlineRequested.value = true}) {
                                    Text(text = "Answer")
                                }
                            }

                            val onClick = {
                                viewModel.selectedJoke.value = null
                                viewModel.punchlineRequested.value = false
                            }

                            Button(onClick) {
                                Text("Back to joke select")
                            }
                        }
                    }


                }
            }
        }
    }

    @Composable
    fun ChooseJokesButton(text: String, type: String) {

        val coroutineScope = rememberCoroutineScope()
        val onClick: () -> Unit = {
            coroutineScope.launch {
                viewModel.getJokes(type)
            }
        }

        Button(onClick) {
            Text(text)
        }
    }
}

