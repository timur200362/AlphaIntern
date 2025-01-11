package com.example.alphaintern.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = binNumber,
            onValueChange = {
                if (it.all { char -> char.isDigit() } && it.length <= 8) {
                    binNumber = it
                }
            },
            label = { Text("Введите BIN номер (6-8 цифр)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = binNumber.length !in (6..8),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Должно быть от 6 до 8 цифр",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = {
                if (binNumber.length in 6..8) {
                    viewModel.loadCardInfo(binNumber)
                }
            },
            modifier = Modifier.padding(top = 2.dp)
        ) {
            Text("Получить информацию о карте")
        }

        if (loading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        } else {
            cardInfo?.let {
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "Информация о карте",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                Text("Страна: ${it.country.name}")
                Text("Широта: ${it.country.latitude}")
                Text("Долгота: ${it.country.longitude}")
                Text("Тип карты: ${it.type}")

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "Данные банка:",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    )
                Text("URL: ${it.bank.url}")
                Text("Телефон: ${it.bank.phone}")
                Text("Сайт: ${it.bank.name}")
                Text("Город: ${it.bank.city}")
            }
        }

        errorMessage?.let { message ->
            Text(text = message, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

