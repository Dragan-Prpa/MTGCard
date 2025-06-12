package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
    fun DisplayMTGImage(card: MTGCard, modifier: Modifier) {
        val aspectRatio = 745f / 1040f
        var image by remember { mutableStateOf(card.fullImageUrl) }
        LaunchedEffect(card) {
            image = card.fullImageUrl
        }
        Card(
            modifier = modifier
                .padding(16.dp)
                .aspectRatio(aspectRatio)
                .clip(RoundedCornerShape(18.dp))
                .clickable {
                if(card.backImageUrl != "")
                {
                    if(image==card.fullImageUrl)
                    {
                        image=card.backImageUrl
                    }
                    else{
                        image=card.fullImageUrl
                    }
                }
                }
        ) {
            AsyncImage(
                model = image,
                contentDescription = card.title,
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }




@Composable
    fun DisplayMTGCard(card: MTGCard, modifier: Modifier = Modifier) {

        Card(modifier = modifier.padding(25.dp, 12.dp).size(345.dp, 480.dp)) {
            Column(modifier = Modifier.padding(5.dp, 5.dp)) {
                Row {
                    Text(text = card.title)
                    Spacer(modifier = Modifier.weight(1f))
                    DisplayMana(card.manaCost)
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
    fun DisplayMana(manaList: List<String>, modifier: Modifier = Modifier) {
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
