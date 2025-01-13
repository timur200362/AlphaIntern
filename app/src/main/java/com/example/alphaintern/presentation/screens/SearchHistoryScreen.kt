package com.example.alphaintern.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alphaintern.data.database.CardEntity
import com.example.alphaintern.presentation.mvvm.SearchHistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchHistoryScreen(
    viewModel: SearchHistoryViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadSearchHistoryCards()
    }
    val cards by viewModel.cards.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "История поиска",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold, fontSize = 22.sp
            )
        )

        LazyColumn {
            items(cards) { card ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("BIN: ${card.bin}")
                        Text("Страна: ${card.country}")
                        Text("Широта: ${card.latitude}")
                        Text("Долгота: ${card.longitude}")
                        Text("Тип карты: ${card.type}")
                        Text("URL: ${card.bankUrl}")
                        Text("Телефон: ${card.bankPhone}")
                        Text("Сайт: ${card.bankWebsite}")
                        Text("Город: ${card.bankCity}")
                    }
                }
            }
        }
    }
}
