package me.jonathanbcrabtree.jokebook.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import me.jonathanbcrabtree.jokebook.remote.dto.Joke

class JokeServiceImpl(
    private val client: HttpClient
): JokeService {
    override suspend fun getJokes(type: String): List<Joke> {
        return try {
            client.get {
                url("https://official-joke-api.appspot.com/jokes/$type/ten")
            }.body()
        } catch (x: Exception) {
            emptyList()
        }

    }

    override fun close() {
        client.close()
    }
}