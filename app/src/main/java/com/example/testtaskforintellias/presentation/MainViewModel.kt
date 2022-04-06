package com.example.testtaskforintellias.presentation


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testtaskforbootcamp.data.WordListMapper
import com.example.testtaskforbootcamp.domain.AddWordItemUseCase
import com.example.testtaskforbootcamp.domain.GetWordItemUseCase
import com.example.testtaskforbootcamp.domain.GetWordListUseCase
import com.example.testtaskforbootcamp.domain.WordItem
import com.example.testtaskforintellias.data.network.WordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val mapper: WordListMapper,
    private val wordRepository: com.example.testtaskforintellias.data.network.Retrofit,
    private val addWordItemUseCase: AddWordItemUseCase,
    private val getWordItemUseCase: GetWordItemUseCase,
    getWordListCase: GetWordListUseCase,
) : AndroidViewModel(application) {

val isConect= MutableStateFlow(true)
val isWrongWord=MutableStateFlow(true)

    val wordLiveData by lazy { MutableStateFlow<WordResponse.WordResponseItem?>(null) }
    val wordDBLiveData =   MutableStateFlow<WordItem?>(null)                              //MutableLiveData<WordItem>()
    val wordList = getWordListCase.getWordList()
    var list = mutableSetOf<String>()


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen


    init {
        fetchWord("Hello")

    }

    fun fetchWord(word: String) {
        isConect.value=com.example.testtaskforintellias.data.network.Retrofit.isNoConnection
       isWrongWord.value=com.example.testtaskforintellias.data.network.Retrofit.isWrongWord
        val parseWord = parseInputName(word)
        val wordValid = validateInput(parseWord)
        if (wordValid) {

            viewModelScope.launch(Dispatchers.IO) {

                if (list.isEmpty() || !list.contains(word.lowercase(Locale.getDefault()))) {
               val  wordResponseList=  wordRepository.getWord(word)
               val   wordResponse=wordResponseList

if(wordResponse is WordResponse.WordResponseItem){
                    val wordItem = mapper.mapWordResponseToWordItem1(wordResponse )
    wordDBLiveData.value=wordItem
                    try {
                        val list1 = mutableListOf<WordItem>()
                        list1.add(mapper.mapWordResponseToWordItem1(wordResponse))
                        wordLiveData.value=wordResponse

                        addWordItemUseCase.addWordItem(wordItem)

                        list.add(
                            mapper.mapWordResponseToWordItem1(wordResponse).word.lowercase(
                                Locale.getDefault()
                            )
                        )

                    } catch (exception: Exception) {
                        if (exception is HttpException) {
                            exception.message()
                        }
                    }}}

                else  {
        val dbWord = getWordItemUseCase.getWordItem(word.lowercase(Locale.getDefault()))

        wordDBLiveData.value=dbWord

    }

                }
            }
        }


    private fun validateInput(inputName: String): Boolean {
        var result = true
        if (inputName.isBlank()) {
            _errorInputName.value = true
            result = false
        }

        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    private fun parseInputName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }


}