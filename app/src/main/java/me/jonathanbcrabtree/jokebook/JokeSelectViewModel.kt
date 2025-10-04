package me.jonathanbcrabtree.jokebook

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import me.jonathanbcrabtree.jokebook.remote.JokeService
import me.jonathanbcrabtree.jokebook.remote.dto.Joke

class JokeSelectViewModel : ViewModel() {

    val jokeService = JokeService.create()
    val jokes = mutableStateListOf<Joke>()
    val selectedJoke = mutableStateOf<Joke?>(null)
    val punchlineRequested = mutableStateOf<Boolean>(false)

    suspend fun getJokes(type: String) {
        jokes.clear()
        jokes.addAll(jokeService.getJokes(type))
    }

    override fun onCleared() {
        super.onCleared()
        jokeService.close()
    }
}