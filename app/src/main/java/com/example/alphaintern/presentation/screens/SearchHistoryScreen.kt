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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alphaintern.R
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
            text = stringResource(id = R.string.search_history_title),
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
                        Text(stringResource(id = R.string.bin_label, card.bin))
                        Text(stringResource(id = R.string.bank_country_label) + "${card.country}")
                        Text(stringResource(id = R.string.bank_latitude_label) + "${card.latitude}")
                        Text(stringResource(id = R.string.bank_longitude_label) + "${card.longitude}")
                        Text(stringResource(id = R.string.bank_card_type_label) + "${card.type}")
                        Text(stringResource(id = R.string.bank_url_label) + "${card.bankUrl}")
                        Text(stringResource(id = R.string.bank_phone_label) + "${card.bankPhone}")
                        Text(stringResource(id = R.string.bank_website_label) + "${card.bankWebsite}")
                        Text(stringResource(id = R.string.bank_city_label) + "${card.bankCity}")
                    }
                }
            }
        }
    }
}
