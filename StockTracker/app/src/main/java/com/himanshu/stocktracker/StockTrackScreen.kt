package com.himanshu.stocktracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun CryptoScreen(modifier: Modifier = Modifier){
    val cryptoViewModel : MainViewModel = viewModel()
    val viewState by cryptoViewModel.cryptoState

    Box(modifier = modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(
                    text = viewState.error ?: "Error occurred",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
                    color = Color.Red
                )
            }
            else -> {
                CryptoList(crypto = viewState.list)
            }
        }
    }
}

@Composable
fun CryptoList(crypto: List<Crypto>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp) // Reduced padding for more space
    ) {
        items(crypto) { cryptocurrency ->
            CryptoItem(crypto = cryptocurrency)
        }
    }
}


@Composable
fun CryptoItem(crypto: Crypto){
    val priceColor = if (crypto.priceChangePercentage24h >= 0) {
        Color(0xFF4CAF50)
    } else {
        Color(0xFFF44336)
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(crypto.image),
            contentDescription = crypto.name,
            modifier = Modifier
                .fillMaxWidth() // Changed from size(100.dp) to fillMaxWidth()
                .aspectRatio(1f)
                .padding(4.dp)
        )
        Text(
            text = crypto.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "$${crypto.currentPrice}",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text(
            text = "${crypto.priceChangePercentage24h}",
            color = priceColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
