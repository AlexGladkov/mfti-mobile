package tech.mobiledeveloper.mfti.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobiledeveloper.mfti.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage

data class Restaurant(
    val name: String,
    val deliveryTime: String,
    val logo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var searchValue by remember { mutableStateOf("") }
    val nearestRestaurant = listOf(
        Restaurant(name = "KFC", deliveryTime = "12 min", logo = "https://play-lh.googleusercontent.com/s7slUGiae9bq7XuYur0GWd_qDp_UXgo_5BIpzOT_BvKGg17TYG5QDr3ckqPcpq20jVU"),
        Restaurant(name = "Burger King", deliveryTime = "10 min", logo = "https://img.wongnai.com/p/1920x0/2019/02/26/cebf62f2742e4756a42b6b505070d6fe.jpg")
    )

    val popularRestaurant = listOf(
        Restaurant(name = "Stars Coffee", deliveryTime = "25 min", logo = "https://riamo.ru/files/image/23/76/25/-gallery!00r2.jpg"),
        Restaurant(name = "Black Star Burger", deliveryTime = "5 min", logo = "https://www.rusfranch.ru/u/www/images/catalog/logo_image_nwna9cyklpaicbxlpzm2c7pvqbck0mgfa.png")
    )

    Column {
        Text(
            modifier = Modifier.padding(start = 31.dp, top = 60.dp),
            text = stringResource(id = R.string.main_title),
            lineHeight = 40.sp,
            fontSize = 31.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                value = searchValue,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,
                    containerColor = Color(0x2FF9A84D),
                    textColor = Color(0xFFDA6317)
                ),
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_hint),
                        color = Color(0x28DA6317)
                    )
                },
                onValueChange = {
                    searchValue = it
                })
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                nearestRestaurant.forEach {
                    item {
                        RestaurantCell(model = it)
                    }
                }

                popularRestaurant.forEach {
                    item {
                        RestaurantCell(model = it)
                    }
                }
            },
            contentPadding = PaddingValues(horizontal = 25.dp)
        )
    }
}

@Composable
fun RestaurantCell(model: Restaurant) {
    Card(
        modifier = Modifier.size(width = 147.dp, height = 184.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(73.dp),
                model = model.logo,
                contentDescription = "${model.name} logo"
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = model.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = model.deliveryTime,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp
            )
        }

    }
}