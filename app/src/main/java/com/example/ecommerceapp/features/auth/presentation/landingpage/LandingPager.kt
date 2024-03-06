package com.example.ecommerceapp.features.auth.presentation.landingpage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.destinations.CategoryDetailScreenDestination
import com.example.ecommerceapp.features.destinations.LoginScreenDestination
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.example.ecommerceapp.widgets.CustomButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Destination(start = true)
fun LandingPager(
     navigator: DestinationsNavigator
) {
    val spacing = LocalSpacing.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val pageDetails = listOf(
        LandingPageDetails(
            title = stringResource(id = R.string.open_furniture_title),
            description = stringResource(id = R.string.open_furniture_desc),
            imageId = R.drawable.ic_working_desk
        ),
        LandingPageDetails(
            title = stringResource(id = R.string.relaxing_furniture),
            description = stringResource(id = R.string.relaxing_furniture_desc),
            imageId = R.drawable.ic_relaxing_furniture
        ),
        LandingPageDetails(
            title = stringResource(id = R.string.home_furniture),
            description = stringResource(id = R.string.home_furniture_desc),
            imageId = R.drawable.ic_home_furniture
        )
    )

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        pageDetails.size
    })
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        // Custom curve cutout
        CurvedCutoutView(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.53f),
            color = MaterialTheme.colorScheme.primary
        )

        HorizontalPager(
            state = pagerState,
        ) { index ->
            LandingScreen(
                title = pageDetails[index].title,
                description = pageDetails[index].description,
                image = pageDetails[index].imageId
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.End
        ) {
            // skip button
            Box(modifier = Modifier
                .padding(spacing.medium)
                .clickable {
                    scope.launch {
                        navigator.navigate(
                            CategoryDetailScreenDestination(
                            categoryId = "1"
                        ),
                        )

                        pagerState.scrollToPage(pageDetails.size - 1)
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.skip),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        RevealDotIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = -(screenHeight * 0.134f)),
            count = pageDetails.size,
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.secondary,
        )

        if (pagerState.currentPage == pageDetails.size - 1) {
            CustomButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(spacing.medium)
                    .navigationBarsPadding()
                    .height(54.dp),
                title = "Get Started",
                onClick = {
                    // navigate to next screen
                    navigator.navigate(LoginScreenDestination)
                }
            )
        }

    }
}