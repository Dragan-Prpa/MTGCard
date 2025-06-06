package com.example.ljamaljubav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ljamaljubav.ui.theme.LjamaLjubavTheme
import fetchCard
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
                                            fetchCard(searchQuery.replace(" ","-").replace(",",""))
                                        }
                                    }
                                }
                            )

                        }
                        items(items = cardsState.value ?: emptyList()) { card ->
                            displayMTGCard(
                                card = card,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }


                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello, $name!",
            modifier = modifier
        )
    }


    @Composable
    fun displayMTGCard(card: MTGCard, modifier: Modifier = Modifier) {

        Card(modifier = modifier.padding(25.dp, 12.dp).size(345.dp, 480.dp)) {
            Column(modifier = Modifier.padding(5.dp, 5.dp)) {
                Row {
                    Text(text = card.title)
                    Spacer(modifier = Modifier.weight(1f))
                    displayMana(card.manaCost)
                }

                AsyncImage(
                    model = card.imageUrl,
                    contentDescription = "MTG Card",
                    modifier = Modifier
                        .size(width = 320.dp, height = 229.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )

                Text(text = card.type, textAlign = TextAlign.Start)

                Spacer(modifier = Modifier.weight(1f))

                Text(text = card.description, fontSize = 14.sp)

                Spacer(modifier = Modifier.weight(1f))

                if(card.strength>-1 || card.toughness>-1) {
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "${card.strength}/${card.toughness}")

                    }
                }
            }
        }
    }


    @Composable
    fun displayMana(manaList: List<String>, modifier: Modifier = Modifier) {
        Row() {

            for (mana in manaList) {

                    when {
                        mana == "1" -> Image(
                            painter = painterResource(id = R.drawable.c1),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "2" -> Image(
                            painter = painterResource(id = R.drawable.c2),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "3" -> Image(
                            painter = painterResource(id = R.drawable.c3),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "4" -> Image(
                            painter = painterResource(id = R.drawable.c4),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "5" -> Image(
                            painter = painterResource(id = R.drawable.c5),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )
                        mana == "B" -> Image(
                            painter = painterResource(id = R.drawable.b),
                            contentDescription = "black mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "W" -> Image(
                            painter = painterResource(id = R.drawable.w),
                            contentDescription = "white mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "R" -> Image(
                            painter = painterResource(id = R.drawable.r),
                            contentDescription = "red mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "G" -> Image(
                            painter = painterResource(id = R.drawable.g),
                            contentDescription = "green mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        mana == "U" -> Image(
                            painter = painterResource(id = R.drawable.u),
                            contentDescription = "blue mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )
                }
            }

        }
    }
    @Composable
    fun searchBar(
        query: String,
        onQueryChange: (String) -> Unit,
        onSearchSubmit: () -> Unit
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search cards...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchSubmit()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=32.dp)

        )
    }
}


