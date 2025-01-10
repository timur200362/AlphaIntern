package com.example.alphaintern.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alphaintern.presentation.mvvm.CardInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
}

@Composable
fun CardInfoScreen(
    viewModel: CardInfoViewModel = koinViewModel()
) {
    val cardInfo by viewModel.cardInfo.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    var binNumber by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = binNumber,
            onValueChange = {
                if (it.all { char -> char.isDigit() } && it.length <= 8) {
                    binNumber = it
                }
            },
            label = { Text("Введите BIN номер (6-8 цифр)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = binNumber.length !in (6..8)
        )

        Text(
            text = "Должно быть от 6 до 8 цифр",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(onClick = {
            if (binNumber.length in 6..8) {
                viewModel.loadCardInfo(binNumber)
            }
        }) {
            Text("Получить информацию о карте")
        }

        if (loading) {
            CircularProgressIndicator()
        } else {
            cardInfo?.let {
                Text(
                    "Страна: ${it.country}, " +
                            "Широта: ${it.country.latitude}, " +
                            "Долгота: ${it.country.longitude}" +
                            "Тип карты: ${it.type}, "
                )
                Text(
                    "Данные банка:" +
                            "URL: ${it.bank.url}" +
                            "Телефон: ${it.bank.phone}" +
                            "Сайт: ${it.bank.name}" +
                            "Город: ${it.bank.city}"
                )
            }
        }

        errorMessage?.let { message ->
            Text(text = message, color = Color.Red)
        }
    }
}
