package com.example.ecommerceapp.features.auth.presentation.registration.forget_password

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.example.ecommerceapp.widgets.CustomButton
import com.example.ecommerceapp.widgets.CustomOutlineTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Destination
fun ForgetPasswordScreen(
    navigator: DestinationsNavigator,
   forgetPasswordViewModel: ForgetPasswordViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val standardPadding = screenWidth * 0.072f
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val state = forgetPasswordViewModel.state

    Scaffold (
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
            ForgetPasswordScreenTopBar(
                navigator = navigator
            )
        },

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = standardPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                    Spacer(modifier = Modifier.padding(screenHeight * 0.018f))

                    // Title
                    Text(
                        modifier = Modifier.padding(bottom =  screenHeight * 0.01f),
                        text = stringResource(id = R.string.forget_password_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // Subtitle
                    Text(
                        text = stringResource(id = R.string.forget_password_subtitile),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                Spacer(modifier = Modifier.padding(spacing.large))

                    // Login form
                    CustomOutlineTextField(
                        value = state.email,
                        onValueChange = {
                            forgetPasswordViewModel.onEvent(
                                ForgetPasswordEvent.OnEmailChange(it)
                            )
                        },
                        label = "Email Address",
                        placeHolder = "example@domain.com",
                        onPasswordToggleClick = {},
                        errorMessage = state.emailErrorMessage,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Email,
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    )

                Spacer(modifier = Modifier.padding(spacing.huge))

                    // Login button
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        title = "Send",
                        onClick = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                            forgetPasswordViewModel.onEvent(
                                ForgetPasswordEvent.OnResetPassword
                            )
                        }
                    )
            }

            if (state.isLoading) CircularProgressIndicator()
            state.snackbarErrorMessage?.let {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = it,
                        actionLabel = "Dismiss"
                    )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
//                            loginViewModel.onEvent(LoginEvent.OnDismissSnackbar)
                        }

                        SnackbarResult.Dismissed -> {
//                            loginViewModel.onEvent(LoginEvent.OnDismissSnackbar)
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ForgetPasswordScreenTopBar(
    navigator: DestinationsNavigator
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}