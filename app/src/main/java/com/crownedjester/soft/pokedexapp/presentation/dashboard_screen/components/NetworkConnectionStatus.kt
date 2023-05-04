package com.crownedjester.soft.pokedexapp.presentation.dashboard_screen.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.crownedjester.soft.pokedexapp.R

@Composable
fun NetworkConnectionStatus(hasConnection: Boolean, modifier: Modifier = Modifier) {

    val bgColor = remember { Animatable(colorConnectionLost) }

    val icon =
        if (!hasConnection) Icons.Default.WifiOff else Icons.Default.Wifi
    val message =
        if (!hasConnection) stringResource(R.string.connection_lost_message) else stringResource(R.string.connection_restored_message)

    LaunchedEffect(key1 = hasConnection) {
        if (hasConnection) bgColor.animateTo(colorConnectionRestored) else bgColor.animateTo(
            colorConnectionLost
        )
    }

    AnimatedVisibility(
        visible = !hasConnection,
        enter = slideInVertically(animationSpec = TweenSpec(durationMillis = ENTER_ANIMATION_DURATION)) { maxHeight -> -maxHeight } + fadeIn(),
        exit = slideOutVertically(
            animationSpec = TweenSpec(
                durationMillis = EXIT_ANIMATION_DURATION,
                delay = EXIT_ANIMATION_DELAY
            )
        ) { maxHeight -> -maxHeight } + fadeOut(animationSpec = TweenSpec(delay = EXIT_ANIMATION_DELAY))
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = bgColor.value)
                .height(messageHeight),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = "message icon")

            Spacer(modifier = Modifier.width(gapBetweenItems))

            Text(text = message)
        }
    }

}

private val gapBetweenItems = 16.dp
private const val EXIT_ANIMATION_DURATION = 1500
private const val EXIT_ANIMATION_DELAY = 1000
private const val ENTER_ANIMATION_DURATION = 1500
private val messageHeight = 36.dp
private val colorConnectionLost = Color.Red.copy(0.65f)
private val colorConnectionRestored = Color.Green.copy(0.65f)