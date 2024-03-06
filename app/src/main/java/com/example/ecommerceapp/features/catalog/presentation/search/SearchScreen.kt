package com.example.ecommerceapp.features.catalog.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.presentation.widget.OptionOutlineButton
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.example.ecommerceapp.ui.theme.Spacing
import com.example.ecommerceapp.widgets.StaggeredFlowRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val state by searchViewModel.searchState.collectAsState()

    val spacing = LocalSpacing.current
    val configuration = LocalConfiguration.current
    configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val standardPadding = screenWidth * 0.072f

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                })
            },
        topBar = {
            ScreenAppBar(navigator)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            // search bar
            MySearchBar(
                modifier = Modifier.padding(bottom = standardPadding, start = standardPadding, end = standardPadding),
                query = state.query,
                spacing = spacing,
                onQueryChange = { query ->
                    searchViewModel.onEvent(SearchEvent.OnSearchQueryChange(query))
                },
                onSearch = { query ->
                    searchViewModel.onEvent(SearchEvent.OnSearchCategory(query))
                }
            )

            ScrollableStaggeredFlowRow (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = standardPadding),
                mainAxisSpacing = spacing.medium,
                crossAxisSpacing = spacing.medium,
            ){
                state.loadedCategories.forEach { category ->
                    OptionOutlineButton(
                        title = category.name,
                        onClick = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
//                            navigator.navigate(Routes.CATEGORY)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun MySearchBar(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    query: String,
    spacing: Spacing
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(16.dp)
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onQueryChange("")
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_clear),
                            contentDescription = stringResource(id = R.string.clear_search),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(query)
                }
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Start
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.placeholder_search),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 54.dp)
        )

        Spacer(modifier = Modifier.size(spacing.medium))

        // search button
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
                .clickable {
                    onSearch(query)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search_icon),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun ScreenAppBar(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Discover",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center
                )
            )

            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back to previous screen",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
private fun ScrollableStaggeredFlowRow(
    modifier: Modifier = Modifier,
    mainAxisSpacing: Dp = 0.dp,
    crossAxisSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        StaggeredFlowRow(
            mainAxisSpacing = mainAxisSpacing,
            crossAxisSpacing = crossAxisSpacing,
            content = content
        )
    }
}