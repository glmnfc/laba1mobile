package com.example.laba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BusinessCardApp()
        }
    }
}

@Composable
fun BusinessCardApp() {
    val backgroundColor = colorResource(id = R.color.background)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

        BusinessCardContent(isLandscape = isLandscape)
    }
}

@Composable
fun BusinessCardContent(isLandscape: Boolean) {
    val spacingMedium = dimensionResource(id = R.dimen.spacing_medium)
    val spacingLarge = dimensionResource(id = R.dimen.spacing_large)
    val surfaceColor = colorResource(id = R.color.surface)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(LocalLayoutDirection.current),
                top = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding(),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(LocalLayoutDirection.current),
                bottom = WindowInsets.safeDrawing.asPaddingValues().calculateBottomPadding()
            )
            .background(color = surfaceColor),
        contentAlignment = if (isLandscape) Alignment.CenterStart else Alignment.Center
    ) {
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacingLarge),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AvatarSection(modifier = Modifier.padding(end = spacingMedium))
                ContactInfoSection(modifier = Modifier.weight(1f))
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacingLarge),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AvatarSection(modifier = Modifier.padding(bottom = spacingMedium))
                ContactInfoSection(modifier = Modifier)
            }
        }
    }
}

@Composable
fun AvatarSection(modifier: Modifier = Modifier) {
    val avatarSize = dimensionResource(id = R.dimen.avatar_size)

    Image(
        painter = painterResource(id = R.drawable.avatar),
        contentDescription = stringResource(id = R.string.full_name),
        modifier = modifier
            .size(avatarSize),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ContactInfoSection(modifier: Modifier = Modifier) {
    val textColor = colorResource(id = R.color.on_surface)
    val textVariantColor = colorResource(id = R.color.on_surface_variant)

    val textSizeName = dimensionResource(id = R.dimen.text_size_name).value.sp
    val textSizeGroup = dimensionResource(id = R.dimen.text_size_group).value.sp

    val spacingExtraSmall = dimensionResource(id = R.dimen.spacing_extra_small)
    val spacingContactGap = dimensionResource(id = R.dimen.spacing_contact_gap)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingExtraSmall)
    ) {
        Text(
            text = stringResource(id = R.string.full_name),
            fontWeight = FontWeight.Bold,
            color = textColor,
            fontSize = textSizeName
        )

        Text(
            text = stringResource(id = R.string.group),
            color = textVariantColor,
            fontSize = textSizeGroup
        )

        Spacer(modifier = Modifier.height(spacingContactGap))

        ContactItem(
            icon = "📧",
            text = stringResource(id = R.string.contact_email)
        )
        ContactItem(
            icon = "📱",
            text = stringResource(id = R.string.contact_phone)
        )
        ContactItem(
            icon = "💬",
            text = stringResource(id = R.string.contact_telegram)
        )
    }
}

@Composable
fun ContactItem(icon: String, text: String) {
    val primaryColor = colorResource(id = R.color.primary)
    val textColor = colorResource(id = R.color.on_surface)
    val textSizeContact = dimensionResource(id = R.dimen.text_size_contact).value.sp
    val iconPaddingEnd = dimensionResource(id = R.dimen.icon_padding_end)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            modifier = Modifier.padding(end = iconPaddingEnd),
            color = primaryColor,
            fontSize = textSizeContact
        )
        Text(
            text = text,
            color = textColor,
            fontSize = textSizeContact
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPortrait() {
    BusinessCardContent(isLandscape = false)
}

@Preview(showBackground = true, widthDp = 800, heightDp = 480, locale = "ru")
@Composable
fun PreviewLandscape() {
    BusinessCardContent(isLandscape = true)
}