package com.tovars.enciclopediastarwars.screem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups3
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PersonalInjury
import androidx.compose.material.icons.filled.Rocket
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import app.rive.runtime.kotlin.core.ExperimentalAssetLoader
import app.rive.runtime.kotlin.core.Fit
import com.tovars.enciclopediastarwars.R
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun HomeScreem() {
    Scaffold(
        topBar = {

            TopBar()

        },
        content = { padingvalue ->

            Content(padingvalue)

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
        textStyle = TextStyle(color = Color.White),
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
private fun Content(padingvalue: PaddingValues) {


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

            ItemsList()

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
    val type: ItemType
)

enum class ItemType {
    MOVIE,
    PERSON,
    PLANET,
    STARSHIP,
    VEHICLE
}

@Composable
private fun ItemsList() {

    val itemsList = listOf(

        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.luke2, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),
        StarWarsItem("Tatooine", "Tatooine", R.drawable.tatooine, ItemType.PLANET),
        StarWarsItem("A new hope", "A new hope", R.drawable.a_new_hope, ItemType.MOVIE),
        StarWarsItem("Luke Skywalker", "Luke Skywalker", R.drawable.lukes, ItemType.PERSON),
        StarWarsItem("X-wing", "X-wing", R.drawable.xwing, ItemType.STARSHIP),
        StarWarsItem("Sand Crawler", "Sand Crawler", R.drawable.sandcrawler, ItemType.VEHICLE),

        )

    val itemsList2 = (0..15).toList()

    val itemModifier = Modifier
        .border(1.dp, Color.Blue)
        .width(80.dp)
        .wrapContentSize()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
        contentPadding = PaddingValues(16.dp),

        ) {

        items(itemsList) {

            CardItem(it)

        }

    }

}

@Composable
private fun CardItem(itemStarWarsItem: StarWarsItem) {

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            //.fillMaxWidth()
            .clickable { }
    ) {

        ConstraintLayout(
            //modifier = Modifier.fillMaxWidth()
        ) {

            val (image, title, description) = createRefs()

            Image(
                painter = painterResource(id = itemStarWarsItem.image),
                contentDescription = itemStarWarsItem.title,
                contentScale = ContentScale.Fit
            )

            Text(
                itemStarWarsItem.title,
                modifier = Modifier.constrainAs(title) {

                    top.linkTo(parent.top)
                    //bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
            )

            Text(
                itemStarWarsItem.description,
                modifier = Modifier.constrainAs(description) {

                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
            )

        }


    }

}

