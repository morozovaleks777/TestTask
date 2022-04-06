package com.example.testtaskforintellias.domain

import javax.inject.Inject

class GetWordItemUseCase @Inject constructor(private val wordListRepository: WordListRepository) {
    suspend fun getWordItem(word: String): WordItem {
        return wordListRepository.getWordItem(word)
    }
}