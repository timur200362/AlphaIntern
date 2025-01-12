package com.example.alphaintern.presentation.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
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
    val context = LocalContext.current
    val cardInfo by viewModel.cardInfo.observeAsState()
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    var binNumber by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Поиск",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                keyboardController?.hide()
                if (binNumber.length in 6..8) {
                    viewModel.getCardInfo(binNumber)
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
                Text(
                    "Широта: ${it.country.latitude}",
                    modifier = Modifier.clickable {
                        if (it.country.latitude != null && it.country.longitude != null) {
                            openMap(context, it.country.latitude, it.country.longitude)
                        } else {
                            Toast.makeText(context, "Координаты отсутствуют", Toast.LENGTH_SHORT).show()
                        }
                    }
                )

                Text(
                    "Долгота: ${it.country.longitude}",
                    modifier = Modifier.clickable {
                        if (it.country.longitude != null && it.country.latitude != null) {
                            openMap(context, it.country.longitude, it.country.latitude)
                        } else {
                            Toast.makeText(context, "Координаты отсутствуют", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
                Text("Тип карты: ${it.type}")

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "Данные банка:",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    )
                Text(
                    "URL: ${it.bank.url}",
                    modifier = Modifier.clickable {
                        if (it.bank.url != null) {
                            openUrl(context, it.bank.url)
                        } else {
                            Toast.makeText(context, "Ссылка отсутствует", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
                Text(
                    "Телефон: ${it.bank.phone}",
                    modifier = Modifier.clickable {
                        if (it.bank.phone != null) {
                            openDialer(context, it.bank.phone)
                        } else {
                            Toast.makeText(context, "Номер телефона отсутствует", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
                Text("Сайт: ${it.bank.name}")
                Text("Город: ${it.bank.city}")
            }
        }

        errorMessage?.let { message ->
            Text(text = message, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

private fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    context.startActivity(intent)
}

private fun openDialer(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}

private fun openMap(context: Context, latitude: Double, longitude: Double) {
    val uri = Uri.parse("geo:$latitude, $longitude")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}