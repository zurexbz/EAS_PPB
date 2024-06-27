package com.example.tugasppb

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tugasppb.ui.theme.TugasPPBTheme

@Composable
fun HomeScreen(navController: NavController) {
    TugasPPBTheme {
        Scaffold(
            topBar = { TopAppBar() },
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            BaseGround(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.navbar),
                    contentDescription = "Navbar",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(80.dp))
                Image(
                    painter = painterResource(id = R.drawable.alfamind),
                    contentDescription = "alfamind",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(80.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "User",
                    modifier = Modifier.size(38.dp)
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Food",
                    modifier = Modifier.size(width = 100.dp, height = 100.dp)
                )
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.wallet),
                    contentDescription = "Food",
                    modifier = Modifier.size(width = 250.dp, height = 100.dp)
                )
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Account",
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun BaseGround(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()) ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Good morning,",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "M. Firman Fardiansyah!",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ImageCarousel(navController = navController)
        Spacer(modifier = Modifier.height(15.dp))
        ListSection(title = "Promo 7.7 Super Sale!", movies = nowPromoSuperSale)
        Spacer(modifier = Modifier.height(15.dp))
        ListSection(title = "Diskon up to 50%!", movies = diskonSale)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val images = listOf(R.drawable.carousell1, R.drawable.carousell2, R.drawable.carousell3)

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        val isFirstImage = page == 0
        Image(
            painter = painterResource(id = images[page]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(15.dp))
                .clickable {
                if (isFirstImage) {
                    navController.navigate("detailfilm")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(images.size) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .background(
                        if (pagerState.currentPage == index) Color.Gray else Color.LightGray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun ListSection(title: String, movies: List<Int>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, bottom = 8.dp)
        )
        LazyRow {
            items(movies.size) { index ->
                Image(
                    painter = painterResource(id = movies[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp, 180.dp)
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

val nowPromoSuperSale = listOf(R.drawable.beras2, R.drawable.beras1, R.drawable.rice, R.drawable.galon)
val diskonSale = listOf(R.drawable.galon, R.drawable.rice, R.drawable.beras2, R.drawable.beras1)

