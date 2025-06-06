package com.example.app

import FetchCard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.app.ui.theme.LjamaLjubavTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            LjamaLjubavTheme {
                val cardsState = remember { mutableStateOf<List<MTGCard>?>(null) }
                var searchQuery by remember { mutableStateOf("") }
                val coroutineScope = rememberCoroutineScope()


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn {
                        item{
                            searchBar(
                                query = searchQuery,
                                onQueryChange = { searchQuery = it },
                                onSearchSubmit = {
                                    coroutineScope.launch {
                                        cardsState.value = withContext(Dispatchers.IO) {
                                            FetchCard(searchQuery.replace(" ","-").replace(",",""))
                                        }
                                    }
                                }
                            )

                        }
                        items(items = cardsState.value ?: emptyList()) { card ->
                            DisplayMTGCard(
                                card = card,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }


                    }
                }
            }
        }
    }





}


