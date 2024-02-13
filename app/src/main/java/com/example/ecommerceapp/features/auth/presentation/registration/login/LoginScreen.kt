package com.example.ecommerceapp.features.auth.presentation.registration.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.config.Routes
import com.example.ecommerceapp.features.auth.presentation.registration.register.RegisterEvent
import com.example.ecommerceapp.widgets.CustomButton
import com.example.ecommerceapp.widgets.CustomOutlineTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Destination(
    route = Routes.LOGIN
)
fun LoginScreen(
    navigator: DestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val state = loginViewModel.state
    val (emailFocusRequester, passwordFocusRequester) = FocusRequester.createRefs()
    loginViewModel.onEvent(LoginEvent.OnSetNavigator(navigator))

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                })
            },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // skip button
                Box(modifier = Modifier
                    .clickable {
                        loginViewModel.onEvent(LoginEvent.OnNavigateToHome)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },

        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column (
                        modifier = Modifier
                        .fillMaxWidth()
                            .weight(1f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start

                ){
                    // Title
                    Text(
                        text = stringResource(id = R.string.login_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    // Subtitle
                    Text(
                        text = stringResource(id = R.string.login_desc),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(30.dp))

                    // Login form
                    CustomOutlineTextField(
                        value = state.email,
                        onValueChange = {
                            loginViewModel.onEvent(
                                LoginEvent.OnEmailChange(it)
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
                        keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                        modifier = Modifier.focusRequester(emailFocusRequester),
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    CustomOutlineTextField(
                        value = state.password,
                        onValueChange = {
                            loginViewModel.onEvent(
                                LoginEvent.OnPasswordChange(it)
                            )
                        },
                        label = "Password",
                        placeHolder = "********",
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


                    Spacer(modifier = Modifier.padding(8.dp))

                    // Forgot password
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

                    Spacer(modifier = Modifier.padding(30.dp))

                    // Login button
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
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

                // register toggle statement
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.register_login_toggle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
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

            }
        }
    )
}