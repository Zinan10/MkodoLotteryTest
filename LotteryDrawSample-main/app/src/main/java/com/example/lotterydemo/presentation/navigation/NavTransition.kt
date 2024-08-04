package com.example.lotterydemo.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.unit.LayoutDirection

private const val ExitTweenDelay = 0
private const val ExitTweenDuration = 150
private const val EnterTweenDelay = 0
private const val EnterTweenDuration = 250

val NavExitTransitionDefault: ExitTransition =
    fadeOut(
        animationSpec = tween(
            easing = LinearEasing,
            delayMillis = ExitTweenDelay,
            durationMillis = ExitTweenDuration
        )
    )

val NavEnterTransitionDefault: EnterTransition =
    enterFadeInTransition()

val NavEnterTransitionVertical: EnterTransition =
    slideInVertically(
        animationSpec = spring(
            stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioNoBouncy
        ),
        initialOffsetY = { it }
    ).plus(enterFadeInTransition())

val NavEnterTransitionHorizontalLeft: EnterTransition =
    enterTransitionHorizontal(LayoutDirection.Rtl)
val NavEnterTransitionHorizontalRight: EnterTransition =
    enterTransitionHorizontal(LayoutDirection.Ltr)

private fun enterTweenSpec(): TweenSpec<Float> =
    tween(
        easing = LinearEasing,
        delayMillis = EnterTweenDelay,
        durationMillis = EnterTweenDuration
    )

private fun enterFadeInTransition(): EnterTransition =
    fadeIn(animationSpec = enterTweenSpec())

private fun enterTransitionHorizontal(direction: LayoutDirection): EnterTransition =
    slideInHorizontally(
        animationSpec = spring(
            stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioNoBouncy
        ),
        initialOffsetX = {
            when (direction) {
                LayoutDirection.Rtl -> it / 2
                LayoutDirection.Ltr -> -it / 2
            }
        }
    ).plus(enterFadeInTransition())
