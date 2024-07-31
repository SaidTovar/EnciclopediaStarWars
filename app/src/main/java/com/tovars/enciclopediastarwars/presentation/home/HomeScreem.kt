package com.tovars.enciclopediastarwars.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Groups3
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonalInjury
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import app.rive.runtime.kotlin.core.ExperimentalAssetLoader
import app.rive.runtime.kotlin.core.Fit
import com.tovars.enciclopediastarwars.R
import com.tovars.enciclopediastarwars.presentation.ComposableRiveAnimationView
import com.tovars.enciclopediastarwars.presentation.model.People
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import kotlin.random.Random

@Composable
fun HomeScreem(homeViewModel: HomeViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {

            TopBar()

        },
        content = { padingvalue ->

            Content(padingvalue, homeViewModel)

        }
    )

}

@OptIn(ExperimentalAssetLoader::class)
@Composable
private fun TopBar() {


    Box(
        modifier = Modifier.fillMaxWidth()

    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)

        ) {

            val (tittle, searchBar) = createRefs()

            val hazeState = remember { HazeState() }

            ComposableRiveAnimationView(
                modifier = Modifier.haze(hazeState),
                animation = R.raw.starwars,
                fit = Fit.FIT_WIDTH,
                onInit = {}
            )

            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black.copy(alpha = 0.15f))
                    .padding(5.dp)
                    .constrainAs(tittle) {

                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                    },
                contentAlignment = Alignment.Center

            ) {

                Text(
                    "Enciclopedia Star Wars",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily(Font(R.font.stjedise))
                )

            }

            //Search Bar

            SearchBar(
                modifier = Modifier
                    .constrainAs(searchBar) {

                        //width = Dimension.fillToConstraints

                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                    }
                    .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                    .hazeChild(
                        hazeState,
                        shape = RoundedCornerShape(10.dp),
                        style = HazeStyle(
                            tint = Color.Unspecified,
                            blurRadius = 5.dp,
                            noiseFactor = 0.15f
                        )
                    )
            )

        }


    }


}

@Composable
private fun SearchBar(
    modifier: Modifier

) {

    var mensaje by remember { mutableStateOf("") }

    BasicTextField(
        value = mensaje,
        onValueChange = {

            //onChangeMenssage(it)
            //isWriting = true
            mensaje = it

        },
        modifier = modifier
            //.defaultMinSize(minWidth = sizeTextMessage)
            //.requiredWidthIn(max = 280.dp)
            .clip(RoundedCornerShape(10.dp)),
        //onTextLayout = { layoutResult.value = it },
        maxLines = 1,
        textStyle = TextStyle(
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.starjedi))
        ),
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->

            Row(
                modifier = Modifier
                    //.background(color = Color(0x4d121212)),
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0x99000000),
                                Color.Transparent
                            )
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        //.background(color = Color(0xB2121212))
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {

                    innerTextField()

                    if (mensaje.isBlank()) {

                        Text(
                            text = "Escribe una busqueda...", // Hint
                            style = TextStyle.Default.copy(color = Color(0xFF125557)),
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily(Font(R.font.starjedi))
                        )

                    }


                }

                Box(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0x67125557))
                        .clickable { }
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        //modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

        }
    )

}

@OptIn(ExperimentalAssetLoader::class)
@Composable
private fun Content(padingvalue: PaddingValues, homeViewModel: HomeViewModel) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padingvalue)

    ) {

        ComposableRiveAnimationView(
            animation = R.raw.cosmos,
            fit = Fit.FILL,
            onInit = {}
        )

        Column {

            ItemsFilter()

            ItemsList(homeViewModel)

        }

    }

}

@Composable
private fun ItemsFilter() {

    val items = listOf(
        Icons.Default.Movie,
        Icons.Default.PersonalInjury,
        Icons.Outlined.Public,
        Icons.Default.Groups3,
        Icons.Default.Rocket,
        Icons.Default.LocalTaxi,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {

        items.forEach {
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0x67125557))
                    .clickable { }
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = it,
                    contentDescription = "Search",
                    tint = Color.White,
                    //modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

    }

}


data class StarWarsItem(
    val title: String,
    val description: String,
    val image: Int,
    val type: ItemType2,
    val id: Long = Random.nextLong()
)

sealed class ItemType2(val icon: ImageVector) {
    data object Movie : ItemType2(Icons.Filled.Movie)
    data object Person : ItemType2(Icons.Filled.Person)
    data object Planet : ItemType2(Icons.Filled.Public)
    data object Starship : ItemType2(Icons.Filled.RocketLaunch)
    data object Vehicle : ItemType2(Icons.Filled.DirectionsCar)
}

@Composable
private fun ItemsList(homeViewModel: HomeViewModel) {

    val itemsList = listOf(
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType2.Movie),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType2.Person),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType2.Starship),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType2.Vehicle),
        // Add more items as needed
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType2.Planet),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType2.Movie),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType2.Person),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType2.Starship),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType2.Vehicle),
        // Add more items as needed
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType2.Planet),

        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType2.Movie),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType2.Person),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType2.Starship),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType2.Vehicle),
        // Add more items random
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType2.Planet),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType2.Movie),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType2.Person),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType2.Starship),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType2.Vehicle),
        // Add more items as needed
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType2.Planet),

        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType2.Movie),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType2.Person),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType2.Starship),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType2.Vehicle),
        // Add more items as needed
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType2.Planet),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType2.Movie),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType2.Person),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType2.Starship),
    )

    val listPeople = homeViewModel.listPeople.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
        contentPadding = PaddingValues(16.dp),

        ) {

        items(listPeople.itemCount) {

            listPeople[it]?.let { people ->

                CardItem2(people)

            }

        }

       /* items(itemsList, key = { it.id }) {

            CardItem(it)

        }*/

    }

}

@Composable
private fun CardItem2(people: People) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
    ) {

        ConstraintLayout {

            val (itemBox, title, description, icon) = createRefs()
/*
            val painter = painterResource(id = itemStarWarsItem.image)

            val aspectRatio = painter.intrinsicSize.width / painter.intrinsicSize.height

            Image(
                painter = painter,
                contentDescription = itemStarWarsItem.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.aspectRatio(aspectRatio)
            )*/

            Box(
                modifier = Modifier
                    .background(brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )).constrainAs(itemBox) {

                        top.linkTo(title.top, margin = -(4).dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }

            ) {

            }

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = people.birth_year,
                modifier = Modifier.constrainAs(icon) {

                    top.linkTo(title.top)
                    bottom.linkTo(description.bottom)
                    end.linkTo(parent.end, margin = 4.dp)

                }
            )

            Text(
                people.name,
                modifier = Modifier.constrainAs(title) {

                    bottom.linkTo(description.top)
                    start.linkTo(parent.start, margin = 4.dp)

                }
            )

            Text(
                people.birth_year,
                modifier = Modifier.constrainAs(description) {

                    bottom.linkTo(parent.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 4.dp)

                }
            )


        }


    }

}

@Composable
private fun CardItem(itemStarWarsItem: StarWarsItem) {

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
    ) {

        ConstraintLayout {

            val (itemBox, title, description, icon) = createRefs()

            val painter = painterResource(id = itemStarWarsItem.image)

            val aspectRatio = painter.intrinsicSize.width / painter.intrinsicSize.height

            Image(
                painter = painter,
                contentDescription = itemStarWarsItem.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.aspectRatio(aspectRatio)
            )

            Box(
                modifier = Modifier
                    .background(brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )).constrainAs(itemBox) {

                        top.linkTo(title.top, margin = -(4).dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }

            ) {

            }

            Icon(
                imageVector = itemStarWarsItem.type.icon,
                contentDescription = itemStarWarsItem.title,
                modifier = Modifier.constrainAs(icon) {

                    top.linkTo(title.top)
                    bottom.linkTo(description.bottom)
                    end.linkTo(parent.end, margin = 4.dp)

                }
            )

            Text(
                itemStarWarsItem.title,
                modifier = Modifier.constrainAs(title) {

                    bottom.linkTo(description.top)
                    start.linkTo(parent.start, margin = 4.dp)

                }
            )

            Text(
                itemStarWarsItem.description,
                modifier = Modifier.constrainAs(description) {

                    bottom.linkTo(parent.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 4.dp)

                }
            )


        }


    }

}

