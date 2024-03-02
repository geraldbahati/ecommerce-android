@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)

package com.example.ecommerceapp.features.auth.presentation.registration.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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
    route = Routes.REGISTER
)
fun RegisterScreen(
    navigator: DestinationsNavigator,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val standardPadding = screenWidth * 0.072f
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()
    val state = registerViewModel.state

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val (emailFocusRequester, passwordFocusRequester, lastNameFocusRequester, firstNameFocusRequester) = remember { FocusRequester.createRefs() }
    registerViewModel.onEvent(
        RegisterEvent.OnSetNavigator(navigator)
    )

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
            RegisterAppBar(registerViewModel)
        },

        content = { paddingValues ->
            ScreenContent(
                scrollState = scrollState,
                paddingValues = paddingValues,
                standardPadding = standardPadding,
                screenHeight = screenHeight,
                spacing = spacing,
                state = state,
                registerViewModel = registerViewModel,
                passwordFocusRequester = passwordFocusRequester,
                emailFocusRequester = emailFocusRequester,
                firstNameFocusRequester = firstNameFocusRequester,
                lastNameFocusRequester = lastNameFocusRequester,
                focusManager = focusManager,
                keyboardController = keyboardController,
                snackbarHostState = snackbarHostState,
                scope = scope
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterAppBar(
    registerViewModel: RegisterViewModel
) {
    TopAppBar(
        title = {},
        actions = {
            TextButton(onClick = { registerViewModel.onEvent(RegisterEvent.OnNavigateToHome) }) {
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
private fun ScreenContent(
    scrollState: LazyListState,
    paddingValues: PaddingValues,
    standardPadding: Dp,
    screenHeight: Dp,
    spacing: Spacing,
    state: RegisterState,
    registerViewModel: RegisterViewModel,
    passwordFocusRequester: FocusRequester,
    emailFocusRequester: FocusRequester,
    firstNameFocusRequester: FocusRequester,
    lastNameFocusRequester: FocusRequester,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
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
            item (
                key = "register"
            ) {
                Spacer(modifier = Modifier.padding(screenHeight * 0.018f))


                // Title
                Text(
                    modifier = Modifier.padding(bottom = screenHeight * 0.01f),
                    text = stringResource(id = R.string.register_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )


                // Subtitle
                Text(
                    text = stringResource(id = R.string.regiser_subtitle),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Spacer(modifier = Modifier.padding(spacing.large))

                // Register form

                CustomOutlineTextField(
                    value = state.email,
                    onValueChange = {
                        registerViewModel.onEvent(
                            RegisterEvent.OnEmailChange(it)
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
                        registerViewModel.onEvent(
                            RegisterEvent.OnPasswordChange(it)
                        )
                    },
                    label = stringResource(id = R.string.password_label),
                    placeHolder = "",
                    isPasswordVisible = state.isPasswordVisible,
                    onPasswordToggleClick = {
                        registerViewModel.onEvent(
                            RegisterEvent.OnPasswordVisibilityChange
                        )
                    },
                    errorMessage = state.passwordErrorMessage,
                    isProtected = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    keyboardActions = KeyboardActions(onNext = { firstNameFocusRequester.requestFocus() }),
                    modifier = Modifier.focusRequester(passwordFocusRequester),
                )


                Spacer(modifier = Modifier.padding(spacing.medium))


                CustomOutlineTextField(
                    value = state.firstName,
                    onValueChange = {
                        registerViewModel.onEvent(
                            RegisterEvent.OnFirstNameChange(it)
                        )
                    },
                    label = stringResource(id = R.string.first_name),
                    onPasswordToggleClick = {},
                    placeHolder = "",
                    errorMessage = state.firstNameErrorMessage,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { lastNameFocusRequester.requestFocus() }),
                    modifier = Modifier.focusRequester(firstNameFocusRequester),
                )


                Spacer(modifier = Modifier.padding(spacing.medium))


                CustomOutlineTextField(
                    value = state.lastName,
                    onValueChange = {
                        registerViewModel.onEvent(
                            RegisterEvent.OnLastNameChange(it)
                        )
                    },
                    label = stringResource(id = R.string.last_name),
                    onPasswordToggleClick = {},
                    placeHolder = "",
                    errorMessage = state.lastNameErrorMessage,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    modifier = Modifier.focusRequester(lastNameFocusRequester),
                )

                Spacer(modifier = Modifier.padding(spacing.small))
            }

            // Terms and conditions
            item (
                key = "terms"
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // custom checkbox
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = {
                            registerViewModel.onEvent(
                                RegisterEvent.OnTermsAndConditionsChange(!state.isTermsAndConditionsAccepted)
                            )
                        }
                    ) {
                        // circle outline when unchecked
                        if (!state.isTermsAndConditionsAccepted) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.onBackground.copy(
                                            alpha = 0.5f
                                        ),
                                        CircleShape
                                    )
                                    .clip(CircleShape)
                            )
                        }

                        // check icon when checked
                        if (state.isTermsAndConditionsAccepted) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "checked",
                                tint = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.5f
                                ),
                                modifier = Modifier
                                    .size(16.dp)
                            )
                        }

                    }

                    Text(
                        text = "I have accept ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )


                    Text(
                        text = "Terms & Conditions",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        ),

                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.clickable {
                            registerViewModel.onEvent(
                                RegisterEvent.OnNavigateToTermsAndConditions
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
                    title = "Register"
                ) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    registerViewModel.onEvent(
                        RegisterEvent.OnRegister
                    )
                }
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
                text = "LOGIN",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    registerViewModel.onEvent(
                        RegisterEvent.OnNavigateToLogin
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