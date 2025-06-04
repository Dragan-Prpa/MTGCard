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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ljamaljubav.ui.theme.LjamaLjubavTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val ljama: String;
        ljama = "Filip Ljamic";

        val Minthara = MTGCard(
            title = "Minthara, Merciless Soul",
            manaCost = listOf("c", "c", "w", "b"),
            description = "Ward x, where X is the number of experience" +
                    "counters you have.\nAt the beginning of your end step, if a permanent you controlled left the battlefield" +
                    "this turn, you get an experience counter.\nCreatures you control get +1/+0 for each" +
                    "experience counter you have.",
            type = "Legendary Creature - Elf Cleric",
            strength = 2,
            toughness = 2
        )

        val Ureni = MTGCard(
            title = "Ureni, the Song Unending",
            manaCost = listOf("c", "c", "c", "c", "c", "g", "u", "r"),
            description = "Flying, protection from white and" +
                    " from black\nWhen Ureni enters, it deals X damage divided as you choose among any number of target creatures and/or planeswalkers your opponents control, where X is the number of lands you" +
                    "control.",
            type = "Legendary Creature - Spirit Dragon",
            strength = 10,
            toughness = 10

        )





        setContent {
            LjamaLjubavTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn {

                        item {
                            displayMTGCard(
                                card = Ureni,
                                modifier = Modifier.padding(innerPadding)
                            );
                        }
                        item {
                            displayMTGCard(
                                card = Minthara,
                                modifier = Modifier.padding(innerPadding)
                            );

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
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "MTG Card",
                    modifier = Modifier.size(width = 320.dp, height = 229.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Text(text = card.type, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.weight(1f));
                Text(text = card.description, fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f));
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "${card.strength}/${card.toughness}")

                }
            }
        }
    }


    @Composable
    fun displayMana(manaList: List<String>, modifier: Modifier = Modifier) {
        Row() {
            var counter = 0;
            var check = false;
            for (mana in manaList) {

                if (mana == "c") {
                    counter++;
                }

                if (mana != "c" && !check) {
                    check = true
                    when {
                        counter == 1 -> Image(
                            painter = painterResource(id = R.drawable.c1),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        counter == 2 -> Image(
                            painter = painterResource(id = R.drawable.c2),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        counter == 3 -> Image(
                            painter = painterResource(id = R.drawable.c3),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        counter == 4 -> Image(
                            painter = painterResource(id = R.drawable.c4),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )

                        counter == 5 -> Image(
                            painter = painterResource(id = R.drawable.c5),
                            contentDescription = "colorless mana",
                            modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                        )
                    }
                }

                when {
                    mana == "b" -> Image(
                        painter = painterResource(id = R.drawable.b),
                        contentDescription = "black mana",
                        modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                    )

                    mana == "w" -> Image(
                        painter = painterResource(id = R.drawable.w),
                        contentDescription = "white mana",
                        modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                    )

                    mana == "r" -> Image(
                        painter = painterResource(id = R.drawable.r),
                        contentDescription = "red mana",
                        modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                    )

                    mana == "g" -> Image(
                        painter = painterResource(id = R.drawable.g),
                        contentDescription = "green mana",
                        modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                    )

                    mana == "u" -> Image(
                        painter = painterResource(id = R.drawable.u),
                        contentDescription = "blue mana",
                        modifier = Modifier.size(18.dp).padding(1.dp, 1.dp)
                    )
                }
            }


        }
    }
}


