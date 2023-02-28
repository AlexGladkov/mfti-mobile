package tech.mobiledeveloper.mfti.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Hello, $name", fontWeight = FontWeight.Medium, fontSize = 24.sp)
    }
}