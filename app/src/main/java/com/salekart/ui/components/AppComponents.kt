package com.salekart.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salekart.R
import com.salekart.data.entity.Product
import com.salekart.data.entity.ProductResponse
import com.salekart.ui.theme.Purple40
import com.salekart.ui.theme.Purple80
import com.salekart.ui.theme.PurpleGrey40

@Composable
fun Loader() {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp), color = Purple40
        )
    }
}

@Composable
fun NormalTextComponents(textValue: String) {
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            color = PurpleGrey40
        )
    )
}

@Composable
fun BoldTextComponents(textValue: String) {
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = Purple40
        )
    )
}

@Composable
fun HeadingTextComponents(textValue: String) {
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ProductRowComponent(page: Int, product: Product) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.White)) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = product.images[0],
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.logo),
            error = painterResource(id = R.drawable.logo))

        Spacer(modifier = Modifier.size(24.dp))
        HeadingTextComponents(textValue = product.title)
        BoldTextComponents(textValue = "Product description :")
        NormalTextComponents(textValue = product.description)
        Spacer(modifier = Modifier.size(2.dp))
        NormalTextComponents(textValue = product.description)
        Spacer(modifier = Modifier.size(30.dp))
        MoreDetailsComponents(product)
    }
}

@Composable
fun MoreDetailsComponents(product: Product) {
    Log.e("Price", product.price.toString())
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(end = 10.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        if (product.brand.isNotEmpty()) {
            NormalTextComponents(textValue = "Brand: ${product.brand}\n\nPrice: ${product.price}")
            Spacer(modifier = Modifier.size(2.dp))
        } else {
            Spacer(modifier = Modifier.size(2.dp))
            NormalTextComponents(textValue = "Brand: ${product.category}\n\nPrice: ${product.price}")
        }
    }
}

