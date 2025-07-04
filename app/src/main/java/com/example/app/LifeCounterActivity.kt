package com.example.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class LifeCounterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())  // hides status + nav bar
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            LotusStyleLifeCounter()
        }
    }
}
@Composable
fun LotusStyleLifeCounter() {
    val playerColors = listOf(Color(0xFFFFC107), Color(0xFFFF1744), Color(0xFFE91E63), Color(0xFF3D5AFE))

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)
        ,verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement =  Arrangement.spacedBy(8.dp)) {
            PlayerLifePanel("Player 1", playerColors[0], rotation = 270f, modifier = Modifier.weight(1f))
            PlayerLifePanel("Player 2", playerColors[1], rotation = 90f, modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement =  Arrangement.spacedBy(8.dp)) {
            PlayerLifePanel("Player 3", playerColors[2], rotation = 270f, modifier = Modifier.weight(1f))
            PlayerLifePanel("Player 4", playerColors[3], rotation = 90f, modifier = Modifier.weight(1f))
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .clickable { /* Handle menu click */ }
        ) {
            Text("MENU", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
        }
    }
}


@Composable
fun PlayerLifePanel(
    name: String,
    backgroundColor: Color,
    rotation: Float,
    modifier: Modifier = Modifier
) {
    var life by remember { mutableStateOf(40) }

    // Rotate the entire box (includes logic + UI)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(8.dp)
            .clickable(
                onClick = {}
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { offset ->
                        if (rotation == 270f) {
                            if (offset.y < size.height / 2) life += 10 else life -= 10
                        } else if (rotation == 90f) {
                            if (offset.y < size.height / 2) life -= 10 else life += 10
                        }
                    },
                    onTap = { offset ->
                        if (rotation == 270f) {
                            if (offset.y < size.height / 2) life++ else life--
                        } else if (rotation == 90f) {
                            if (offset.y < size.height / 2) life-- else life++
                        }
                    }
                )
            }
            .rotate(rotation)


    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "-",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black.copy(alpha = 0.2f)
                )
            }



            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$life",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }


            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black.copy(alpha = 0.2f)
                )
            }
        }
    }
}
