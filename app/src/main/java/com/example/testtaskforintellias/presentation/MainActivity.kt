package com.example.testtaskforintellias.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testtaskforbootcamp.presentation.MainViewModel
import com.example.testtaskforintellias.R
import com.example.testtaskforintellias.components.InputField
import com.example.testtaskforintellias.ui.theme.TestTaskForIntelliasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {






    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TestTaskForIntelliasTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                         CreateCard()
                }
            }
        }
    }




//    @SuppressLint("SetTextI18n")
//    private fun observeDB() {
//
//        viewModel.wordDBLiveData.observe(this, {
//            word.text = "from database: \n word :  \n ${it.word}"
//            phonetics.text = "phonetic :    \n ${it.phonetic}"
//            meanings.text = "meanings :  \n ${it.meanings}"
//        })
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun observeWordResponse() {
//
//        viewModel.wordLiveData.observe(this) { response ->
//
//            apply {
//                val wordMeanings = SpannableString(
//                    "definition :  \n ${response.meanings.indices.map { response.meanings }} \n" +
//                            "example : \n ${response.meanings[0].definitions[0].example} \n" +
//                            "synonyms : \n ${response.meanings[0].definitions[0].synonyms}"
//                )
//                wordMeanings.setSpan(
//                    ForegroundColorSpan(
//                        resources.getColor(
//                            R.color.purple_200,
//                            resources.newTheme()
//                        )
//                    ),
//                    0,
//                    10,
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//                )
//
//                val wordText = SpannableString("word :  \n ${response.word}")
//                wordText.setSpan(
//                    ForegroundColorSpan(
//                        resources.getColor(
//                            R.color.purple_200,
//                            resources.newTheme()
//                        )
//                    ), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//                )
//
//                val wordPhonetic =
//                    SpannableString("phonetics :  \n ${response.phonetics[0].text}")
//                wordPhonetic.setSpan(
//                    ForegroundColorSpan(
//                        resources.getColor(
//                            R.color.purple_200,
//                            resources.newTheme()
//                        )
//                    ), 0, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//                )
//
//                word.text = wordText
//                phonetics.text = wordPhonetic
//                meanings.text = wordMeanings
//
//
//
//            }
//        }
//    }
//
//    private fun setButtonListener() {
//        generateButton.setOnClickListener {
//            wordEnter = enterWord.text.toString()
//
//            viewModel.wordList.observe(this, {
//                itemList = it.filter { wordItem: WordItem -> wordItem.word == wordEnter }
//            })
//            viewModel.fetchWord(wordEnter)
//        }
//    }
//
//
//
//    private fun observeViewModel() {
//
//        viewModel.errorInputName.observe(vthis) {
//            val message = if (it) {
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            enterWord.error = message
//
//        }
//        viewModel.closeScreen.observe(this) {
//            this.onBackPressed()
//        }
//    }
//
//    private fun addTextChangeListeners() {
//        enterWord.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//    }
//
//    private fun showToast(){
//        viewModel.isConect.observe(this,{
//            if(it){
//                Toast.makeText(
//                   this,
//                    "oh,no no no internet",
//                    Toast.LENGTH_SHORT
//                ).show()
//                Retrofit.isNoConnection=false}
//
//        })
//        viewModel.isWrongWord.observe(this,{
//            if(it){
//                Toast.makeText(
//                    this,
//                    "oh,no no no its wrong word",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//                Retrofit.isWrongWord=false}
//        })
//    }






}
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
private fun CreateCard(
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val message = remember{mutableStateOf("")}

    val buttonClickedState =  remember {
        mutableStateOf(false)
    }




    val textFieldState= remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.LightGray,
            elevation = 4.dp) {
            Column(
                Modifier.size(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

               CreateImageProfile()
                Divider(
                    //here can be default
                    Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray,
                    startIndent = 0.dp
                )
               CreateInfo(textFieldState,message)
                Button(onClick = {
                    Log.d("test","button clicked")
                    Log.d("test","button clicked2 ${message.value}")
                    mainViewModel.fetchWord(message.value)
                    buttonClickedState.value=!buttonClickedState.value

                }
                ) {
                    Text(text = "Generate",
                        style = MaterialTheme.typography.button )
                }
                OurText(viewModel =mainViewModel)
//                    AnimatedVisibility(visible = buttonClickedState.value) {
//                        OurText( message, viewModel =mainViewModel )
//                    }



            }
        }
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier=Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.DarkGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.book),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
private fun CreateInfo(tfState : MutableState<Boolean>,message: MutableState<String>) {
    Column(Modifier.padding(5.dp)) {
        Text(
            text = "explanatory dictionary",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )

        InputField(valueState =message ,
            labelId = "enter word",
            enabled = true,
            isSingleLine =true )

        Text(message.value, fontSize = 28.sp)



    }
}


@ExperimentalAnimationApi
@Composable
private fun //ColumnScope.
        OurText(viewModel: MainViewModel) {

    val data = viewModel.wordLiveData.collectAsState().value
    Log.d("test", "OurText: $data")
val definition= mutableListOf<String>()
    data?.meanings?.indices?.forEach { i ->
for(element in data.meanings[i].definitions)
        definition.add(element.definition)
    }

        Column(
            modifier = Modifier.padding(5.dp)

        ) {

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState()),
                text = "word : ${data?.word}",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primaryVariant,
                textAlign = TextAlign.Start,

            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState()),
                text = "phonetics : ${data?.phonetics?.get(0)?.text}",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primaryVariant
            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState()),
              //  text = "definition : ${data?.meanings get(0)?.definitions?.indices?.map { data?.meanings. }}",
                text = "definition : ${definition}}",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }






@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreateCard()
}