package com.example.doe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTopAppBar()
            NavigationBarSample()
            MusicListScreen()
        }
    }
}


@Composable
fun MusicCard(title: String, artist: String, albumArtResId: Int) {
    var isLiked by remember { mutableStateOf(false) }
    var isAddClicked by remember { mutableStateOf(false) }
    var isTrashVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = albumArtResId),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.bodyLarge)
                Text(text = artist, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                isLiked = !isLiked
                            },
                        tint = if (isLiked) Color.Red else LocalContentColor.current.copy(alpha = 0.6f)
                    )
                    if (isTrashVisible) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                                .clickable {
                                    // Implement delete logic here
                                    isTrashVisible = false
                                    isAddClicked = false
                                },
                            tint = Color.Gray
                        )
                    } else {
                        Icon(
                            imageVector = if (isAddClicked) Icons.Default.Delete else Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    isAddClicked = !isAddClicked
                                    isTrashVisible = !isTrashVisible
                                }
                        )
                    }
                }
            }
        }
    }
}





@Composable
fun MusicListScreen() {
    Column {
        SimpleTopAppBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MusicCard(
                title = "No Rules",
                artist = "TXT",
                albumArtResId = R.drawable.album_1
            )
            MusicCard(
                title = "Hooked",
                artist = "Why Don't We",
                albumArtResId = R.drawable.album_2
            )
            MusicCard(
                title = "Havana",
                artist = "Camila Cabello",
                albumArtResId = R.drawable.album_3
            )
            MusicCard(
                title = "God is a women",
                artist = "Ariana Grande",
                albumArtResId = R.drawable.album_4
            )
            MusicCard(
                title = "Levitating",
                artist = "Dua Lipa",
                albumArtResId = R.drawable.album_5
            )
            MusicCard(
                title = "I Like Me Better",
                artist = "Lauv",
                albumArtResId = R.drawable.album_6
            )
        }
    }
}



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTopAppBar() {
    TopAppBar(
        windowInsets = TopAppBarDefaults.windowInsets,
        title = { Text("Songs") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(185, 180, 227)
        ),
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Settings, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Localized description")
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 150, heightDp = 200)
@Composable
fun SimpleTopAppBarPreview() {
    SimpleTopAppBar()
}



@Composable
fun NavigationBarSample() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Surface(
                color = Color.Red, // Set your desired background color here
            ) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(Icons.Filled.Search, contentDescription = item) },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        }
    }
}







