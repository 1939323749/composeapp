package cn.snowlie.app.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
@Composable
fun Basic() {
    var currentScreen by remember { mutableStateOf("Profile") }

    Scaffold(
        bottomBar = { SootheBottomNavigation(currentScreen){ selectedScreen -> currentScreen = selectedScreen }  }
    ) { padding ->
        if (currentScreen == "Home") {
            HomeScreen(Modifier.padding(padding))
        } else {
            ProfileScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun ProfileScreen(padding: Modifier) {
    Surface(
        modifier = padding.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Text(text = "Profile", style = MaterialTheme.typography.h1)
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min=20.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
        ),
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        },
    )
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier= Modifier,
){
    Column(
        modifier = modifier
    ) {
        Text(text = "Align Your Body")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row {
            Text(
                text = "Short mantras to repeat and focus on during meditation.",
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,

) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement =  Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        item {
            Text(text = "Align Your Body")
        }

    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        item {
            Text(text = "Favorite Collections")
        }
    }
}

@Composable
fun HomeSection(
    modifier:  Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = "test",
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(Modifier.padding(top = 16.dp)) {
            AlignYourBodyRow()
        }
        HomeSection(Modifier.padding(top = 16.dp)) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SootheBottomNavigation(currentScreen: String, onItemClick: (String) -> Unit = {}) {
    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = { Text("Home") },
            selected = currentScreen == "Home",
            onClick = { onItemClick("Home") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = { Text("Profile") },
            selected = currentScreen == "Profile",
            onClick = { onItemClick("Profile") }
        )
    }
}