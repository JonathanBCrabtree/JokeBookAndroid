package me.jonathanbcrabtree.jokebook.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Joke(
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)
