package com.example.mainapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainapp.ui.theme.MainAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding
                    NavScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(screenId: MutableState<Int>, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(
            enabled = true,
            shape = RectangleShape,
            onClick = {
                screenId.value = 1
            }
        ) {
            Text("Регистрация")
        }
    }
}




@Composable
fun MyRow(text: String, textState: MutableState<TextFieldValue>, errorLabel: String? = null): TextFieldValue {
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        label = { Text(text) },
        isError = errorLabel != null,
        supportingText = {
            if (errorLabel != null) {
                Text(errorLabel, color = Color.Red)
            }
        }
    )
    return textState.value
}

@Composable
fun Greeting(modifier: Modifier = Modifier, screenId: MutableState<Int>) {
    val nameState = remember { mutableStateOf(TextFieldValue("")) }
    val genderState = remember { mutableStateOf(TextFieldValue("")) }
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }
    val confirmPasswordState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Назад",
                style = TextStyle(
                    color = Color(color = 0xFF868080),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),
                modifier = Modifier.clickable {
                    screenId.value = 0
                }
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Регистрация",
                style = TextStyle(
                    color = Color(0xFF426B8D),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.kotlin_svgrepo_com_1),
                contentDescription = "Kotlin logo",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(90.dp))

        MyRow("Поле для имени", nameState)
        Spacer(modifier = Modifier.height(18.dp))
        MyRow("Поле для почты", genderState)
        Spacer(modifier = Modifier.height(18.dp))
        MyRow("Поле для указания пола", emailState)
        Spacer(modifier = Modifier.height(18.dp))

        val passwordLabel = if (passwordState.value.text != confirmPasswordState.value.text) {
            "Пароли не совпадают"
        } else {
            null
        }

        MyRow("Поле для пароля", passwordState, errorLabel = passwordLabel)
        Spacer(modifier = Modifier.height(18.dp))
        MyRow("Подтвердите пароль", confirmPasswordState, errorLabel = passwordLabel)
        Spacer(modifier = Modifier.height(30.dp))

        fun checkAllFieldsFilled(): Boolean {
            return nameState.value.text.isNotBlank() &&
                    genderState.value.text.isNotBlank() &&
                    emailState.value.text.isNotBlank() &&
                    passwordState.value.text.isNotBlank() &&
                    confirmPasswordState.value.text.isNotBlank() &&
                    passwordState.value.text == confirmPasswordState.value.text
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Уже есть аккаунт",
                style = TextStyle(
                    color = Color(color = 0xFF868080),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.clickable { }
            )
            Button(onClick = {}, enabled = checkAllFieldsFilled()) {
                Text(
                    text = "Отправить",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                    )
                )
            }
        }
    }
}

@Composable
fun NavScreen() {
    val screenId = remember { mutableStateOf(0) }

    when (screenId.value) {
        0 -> {
            MainScreen(screenId = screenId)
        }

        1 -> {
            Greeting(screenId = screenId)
        }
    }
}
