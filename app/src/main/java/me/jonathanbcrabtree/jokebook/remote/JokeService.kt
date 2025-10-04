package me.jonathanbcrabtree.jokebook.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import me.jonathanbcrabtree.jokebook.remote.dto.Joke

interface JokeService {
    suspend fun getJokes(type: String): List<Joke>
    fun close()

    companion object {
        fun create(): JokeService {
            return JokeServiceImpl(
                client = HttpClient(Android) {
                    install(ContentNegotiation) {
                        json()
                    }
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                })
        }
    }
}


