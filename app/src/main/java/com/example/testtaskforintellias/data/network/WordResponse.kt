package com.example.testtaskforintellias.data.network


import com.example.testtaskforintellias.domain.WordItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

class WordResponse : ArrayList<WordResponse.WordResponseItem>() {
    @JsonClass(generateAdapter = true)
    data class WordResponseItem @Inject constructor(
        var itemId: Int = WordItem.UNDEFINED_ID,
        @Json(name = "meanings")
        val meanings: List<Meaning>,
        @Json(name = "phonetics")
        val phonetics: List<Phonetic>,
        @Json(name = "word")
        val word: String = ""
    ) {
        @JsonClass(generateAdapter = true)
        data class Meaning(
            @Json(name = "definitions")
            val definitions: List<Definition>,
            @Json(name = "partOfSpeech")
            val partOfSpeech: String = ""
        ) {
            @JsonClass(generateAdapter = true)
            data class Definition(
                @Json(name = "definition")
                val definition: String = "",
                @Json(name = "example")
                val example: String = "",
                @Json(name = "synonyms")
                val synonyms: List<String>
            )
        }

        @JsonClass(generateAdapter = true)
        data class Phonetic(
            @Json(name = "audio")
            val audio: String = "",
            @Json(name = "text")
            val text: String = ""
        )
    }
}