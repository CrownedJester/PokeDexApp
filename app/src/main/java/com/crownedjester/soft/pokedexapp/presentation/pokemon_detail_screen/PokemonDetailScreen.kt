@file:Suppress("DEPRECATION")

package com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Scale
import com.crownedjester.soft.pokedexapp.R
import com.crownedjester.soft.pokedexapp.data.local.entity.Types
import com.crownedjester.soft.pokedexapp.domain.model.Pokemon
import com.crownedjester.soft.pokedexapp.presentation.PokemonViewModel
import com.crownedjester.soft.pokedexapp.presentation.common_components.TypesRow
import com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen.components.DetailTopBar
import com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen.pages.PokemonAboutPage
import com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen.pages.PokemonStatsPage
import com.crownedjester.soft.pokedexapp.presentation.util.UiEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    val viewModel: PokemonViewModel = viewModel(LocalContext.current as ComponentActivity)

    val tabs = listOf(stringResource(R.string.about), stringResource(R.string.base_stats))


    val pagerState = rememberPagerState(0)

    val (_, name, height, weight, types, stats, abilities, art) = pokemon

    val bgColor = Types.typesWithColors[types.data.first()] ?: defaultColor

    ConstraintLayout(modifier = modifier) {

        val (topBar,
            bgBox,
            pokemonName,
            typesRow,
            pokeImage,
            dataPanel,
            pagerTabs,
            pager) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(LOW_LAYER_Z_INDEX)
                .background(
                    color = bgColor.copy(alpha = BG_ALPHA),
                )
                .constrainAs(bgBox) {
                    linkTo(parent.top, parent.bottom)
                    linkTo(parent.start, parent.end)

                }
        )

        DetailTopBar(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .zIndex(TOP_LAYER_Z_INDEX)
                .height(64.dp)
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }, onClick = {
                viewModel.sendEvent(UiEvent.NavigateUp)
            }
        )

        Text(text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .zIndex(TOP_LAYER_Z_INDEX)
                .constrainAs(pokemonName) {
                    top.linkTo(topBar.bottom, 12.dp)
                    start.linkTo(parent.start, 24.dp)
                })

        TypesRow(types = types, modifier = Modifier.constrainAs(typesRow) {
            top.linkTo(pokemonName.bottom, 10.dp)
            start.linkTo(pokemonName.start, 8.dp)
        })

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .zIndex(MIDDLE_LAYER_Z_INDEX)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = controlPanelCorner,
                    topEnd = controlPanelCorner
                )
            )
            .constrainAs(dataPanel) {
                start.linkTo(bgBox.start)
                bottom.linkTo(bgBox.bottom)
            })

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(art)
                .scale(Scale.FIT)
                .error(drawableResId = R.drawable.image_pokemon_placeholder)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "pokemon full image",
            modifier = Modifier
                .size(200.dp)
                .zIndex(TOP_LAYER_Z_INDEX)
                .graphicsLayer {
                    rotationY = 180f
                }
                .constrainAs(pokeImage) {
                    bottom.linkTo(dataPanel.top, margin = (-24).dp)
                    linkTo(parent.start, parent.end)
                }
        )

        Tabs(
            pagerState = pagerState,
            tabs = tabs,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .zIndex(TOP_LAYER_Z_INDEX)
                .constrainAs(pagerTabs) {
                    top.linkTo(dataPanel.top, margin = 24.dp)
                    linkTo(parent.start, parent.end)
                }
        )

        HorizontalPager(
            pageCount = tabs.size,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .zIndex(TOP_LAYER_Z_INDEX)
                .constrainAs(pager) {
                    top.linkTo(pagerTabs.bottom, margin = 12.dp)
                    start.linkTo(dataPanel.start)
                }) { pageInd ->
            when (pageInd) {
                0 -> PokemonAboutPage(
                    height = height,
                    weight = weight,
                    abilities = abilities,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                )

                1 -> PokemonStatsPage(
                    stats = stats,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp)
                )
            }
        }
    }


}


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
private fun Tabs(pagerState: PagerState, tabs: List<String>, modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        selectedTabIndex = pagerState.currentPage,
        indicator = { positions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState, positions
                ),
                height = 2.dp,
                color = tabRowIndicatorColor
            )

        }) {
        tabs.forEachIndexed { index, s ->
            Tab(
                selected = pagerState.currentPage == index,
                text = {
                    Text(
                        text = s,
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                    )
                },
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } })
        }

    }

}

private val tabRowIndicatorColor = Color(255, 138, 101, 255)
private val defaultColor = Color(255, 213, 79, 255)
private const val BG_ALPHA = 0.8f
private const val LOW_LAYER_Z_INDEX = 0f
private const val MIDDLE_LAYER_Z_INDEX = 45f
private const val TOP_LAYER_Z_INDEX = 90f
private val controlPanelCorner = 24.dp