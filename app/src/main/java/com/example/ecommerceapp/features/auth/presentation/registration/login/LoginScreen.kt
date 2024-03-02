@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ecommerceapp.features.auth.presentation.registration.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.config.Routes
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.example.ecommerceapp.ui.theme.Spacing
import com.example.ecommerceapp.widgets.CustomButton
import com.example.ecommerceapp.widgets.CustomOutlineTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Destination(
    route = Routes.LOGIN,
)
fun LoginScreen(
    navigator: DestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val standardPadding = screenWidth * 0.072f
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()
    val state = loginViewModel.state

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    val (emailFocusRequester, passwordFocusRequester) = remember { FocusRequester.createRefs() }
    loginViewModel.onEvent(LoginEvent.OnSetNavigator(navigator))

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

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        topBar = {
            LoginAppBar(
                loginViewModel
            )
        },

        content = { paddingValues ->
            LoginScreenContent(
                paddingValues = paddingValues,
                screenHeight = screenHeight,
                standardPadding = standardPadding,
                loginViewModel = loginViewModel,
                scrollState = scrollState,
                state = state,
                emailFocusRequester = emailFocusRequester,
                passwordFocusRequester = passwordFocusRequester,
                focusManager = focusManager,
                keyboardController = keyboardController,
                scope = scope,
                snackbarHostState = snackbarHostState,
                spacing = spacing
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAppBar(
    loginViewModel: LoginViewModel
) {
    TopAppBar(
        title = {},
        actions = {
            TextButton(onClick = { loginViewModel.onEvent(LoginEvent.OnNavigateToHome) }) {
                Text(
                    text = stringResource(id = R.string.skip),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginScreenContent(
    paddingValues: PaddingValues,
    screenHeight: Dp,
    standardPadding: Dp,
    loginViewModel: LoginViewModel,
    scrollState: LazyListState,
    state: LoginState,
    emailFocusRequester: FocusRequester,
    passwordFocusRequester: FocusRequester,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    spacing: Spacing
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .padding(horizontal = standardPadding)
                .navigationBarsPadding()
                .padding(bottom = spacing.large)
                .fillMaxSize()
                .imePadding(),
        ) {
            item(
                key = "login"
            ) {
                Spacer(modifier = Modifier.padding(screenHeight * 0.018f))


                // Title
                Text(
                    modifier = Modifier.padding(bottom =  screenHeight * 0.01f),
                    text = stringResource(id = R.string.login_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // Subtitle
                Text(
                    text = stringResource(id = R.string.login_desc),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.padding(spacing.large))

                // form
                CustomOutlineTextField(
                    value = state.email,
                    onValueChange = {
                        loginViewModel.onEvent(
                            LoginEvent.OnEmailChange(it)
                        )
                    },
                    label = stringResource(id = R.string.email_label),
                    placeHolder = stringResource(id = R.string.email_placeholder),
                    onPasswordToggleClick = {},
                    errorMessage = state.emailErrorMessage,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                    ),
                    keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                    modifier = Modifier.focusRequester(emailFocusRequester),
                )

                Spacer(modifier = Modifier.padding(spacing.medium))

                CustomOutlineTextField(
                    value = state.password,
                    onValueChange = {
                        loginViewModel.onEvent(
                            LoginEvent.OnPasswordChange(it)
                        )
                    },
                    label = stringResource(id = R.string.password_label),
                    placeHolder = "",
                    isPasswordVisible = state.isPasswordVisible,
                    onPasswordToggleClick = {
                        loginViewModel.onEvent(
                            LoginEvent.OnPasswordVisibilityChange
                        )
                    },
                    errorMessage = state.passwordErrorMessage,
                    isProtected = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    modifier = Modifier.focusRequester(passwordFocusRequester),
                )


                Spacer(modifier = Modifier.padding(spacing.small))
            }

            // reset password
            item(
                key = "reset_password"
            ) {
                Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        loginViewModel.onEvent(
                            LoginEvent.OnNavigateToForgotPassword
                        )
                    }
                )
            }

                Spacer(modifier = Modifier.padding(spacing.huge))

                // Login button
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    title = "Login",
                    onClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        loginViewModel.onEvent(
                            LoginEvent.OnLogin
                        )
                    }
                )
            }

        }

        // register toggle statement
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.register_login_toggle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(spacing.tiny))
            Text(
                text = "REGISTER",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    loginViewModel.onEvent(
                        LoginEvent.OnNavigateToRegister
                    )
                }
            )
        }

        if (state.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
}