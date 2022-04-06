package com.example.testtaskforintellias.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@ExperimentalComposeUiApi
@Composable
fun InputField(
    modifier: Modifier=Modifier,
    valueState:MutableState<String>,
    labelId:String,
    enabled:Boolean,
    isSingleLine:Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,


){
    val keyboardController= LocalSoftwareKeyboardController.current
    OutlinedTextField (value = valueState.value,
        onValueChange = {valueState.value=it},
        label={Text(text = labelId)},
        leadingIcon={ Icon(imageVector = Icons.Rounded.Search,
            contentDescription = "money icon")
        },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground),
        modifier= modifier
            .padding(bottom = 7.dp, start = 7.dp, end = 7.dp)
            .fillMaxWidth(),
        enabled = enabled,
//        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
//            imeAction = imeAction),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
       // keyboardActions = onAction,
        keyboardActions = KeyboardActions {
           onAction
            keyboardController?.hide()
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.LightGray
        )

    )
}

@ExperimentalComposeUiApi
@Composable
fun NoteInputText(
    modifier:Modifier=Modifier,
    text:String,
    label:String,
    maxLine:Int=1,
    onTextChange:(String)->Unit,
    onImeAction: ()->Unit={}
){
    val keyboardController=LocalSoftwareKeyboardController.current
    TextField(value = text,
        onValueChange =onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ) ,
        maxLines=maxLine,
        label = {Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            onImeAction()
            keyboardController?.hide()
        },
        modifier = modifier

    )


}