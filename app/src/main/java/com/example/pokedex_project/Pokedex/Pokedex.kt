package com.example.pokedex_project.Pokedex

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokedex_project.MainActivity
import com.example.pokedex_project.R
import com.example.pokedex_project.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/*
*
*Autor: Pablo Muñoz
*Esqueleto del diseño de la pantalla que contiene los poquemons
*
* */


@Composable
fun PokedexScreen(viewModel: PokedexViewModel, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopAppBar(coroutineScope = coroutineScope, scaffoldState = scaffoldState) },
        drawerContent = { Mydrawer() { onCloseDrawer(coroutineScope, scaffoldState) } },
        drawerGesturesEnabled = false
    ) { paddingValues ->
        RecyclerViewPokemon(modifier = Modifier.padding(paddingValues))

    }
}

/*
*
* Autor: Pablo Muñoz
* Diseño de la barra superior de la pantalla
*
* */
@Composable
fun MyTopAppBar(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(title = {
        Image(
            painter = painterResource(id = R.drawable.pokedex),
            contentDescription = "Pokemon Image"
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.1f)
        )
        Text(text = "End of degree project")
    },
        actions = {
            IconButton(onClick = { onMenuPressed(coroutineScope, scaffoldState) }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu button")
            }
        })
}

/*
*
* Autor: Pablo Muñoz
* Menu Desplegable de la aplicación
*
* */
@Composable
fun Mydrawer(onCloseDrawer: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Primera opcción",
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Salir del menu",
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
    }
}

/*
*
* Autor: Pablo Muñoz
* Recyclerview que contendrá a los pokemons
*
* */
@Composable
fun RecyclerViewPokemon(modifier: Modifier) {
    LazyColumn {
        items(getPokemons()){ Pokemon ->
            ItemPokemon(pokemon = Pokemon)
        }
    }
}

/*
*
* Autor: Pablo Muñoz
* Carta para el recyclerview de pokemons
*
* */
@Composable
fun ItemPokemon(pokemon: Pokemon) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        ConstraintLayout {
            val (foto, id, nombre, tipo, tipo2) = createRefs()
            AsyncImage(model = pokemon.imagen, contentDescription = "Foto del pokemon",
                Modifier
                    .constrainAs(foto)
                    {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .fillMaxHeight())
            Text(text = "Id: " + pokemon.id.toString(), Modifier.constrainAs(id) {
                start.linkTo(foto.end)
                top.linkTo(parent.top)
            })
            Text(text = "Name: " + pokemon.nombre, Modifier.constrainAs(nombre){
                start.linkTo(id.start)
                top.linkTo(id.bottom)
            })
            Text(text ="Type: " + pokemon.tipo1, Modifier.constrainAs(tipo){
                start.linkTo(id.start)
                top.linkTo(nombre.bottom)
            })
            if (!pokemon.tipo2.isNullOrEmpty()){
            Text(text ="Second type: " + pokemon.tipo2!!, Modifier.constrainAs(tipo2){
                start.linkTo(id.start)
                top.linkTo(tipo.bottom)
            })
            }
        }

    }
}





