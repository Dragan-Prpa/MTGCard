package com.example.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.LjamaLjubavTheme


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LjamaLjubavTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(150.dp))


                        Text(
                            text = "MTG APLIKACIJA",
                            fontWeight = FontWeight.Bold,
                            fontSize = 45.sp
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        MenuButton(SearchActivity::class.java,"SEARCH")

                        Spacer(modifier = Modifier.height(40.dp))

                        MenuButton(LifeCounterActivity::class.java,"LIFE COUNTER")

                        Spacer(modifier = Modifier.height(40.dp))

                        MenuButton(SearchActivity::class.java,"DECK BUILDER")


                    }
                }


            }
        }
    }
}

@Composable
fun MenuButton(Activity:Class<*>, text:String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, Activity)
            context.startActivity(intent)
            (context as? Activity)?.finish()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, fontSize = 24.sp, color = Color.White)
    }
}


