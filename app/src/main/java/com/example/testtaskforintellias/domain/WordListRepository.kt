package com.example.testtaskforintellias.domain

import androidx.lifecycle.LiveData


interface WordListRepository {

    suspend fun getWordItem(word: String): WordItem

    fun getWordList():LiveData<List<WordItem>>

    suspend fun addWordItem(wordItem: WordItem)

}