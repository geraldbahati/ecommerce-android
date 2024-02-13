package com.example.ecommerceapp.features.auth.presentation.registration.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.config.Routes
import com.example.ecommerceapp.widgets.CustomButton
import com.example.ecommerceapp.widgets.CustomOutlineTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Destination(
    route = Routes.REGISTER
)
fun RegisterScreen(
    navigator: DestinationsNavigator,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val state = registerViewModel.state
    val (emailFocusRequester, passwordFocusRequester, firstNameFocusRequester, lastNameFocusRequester) = FocusRequester.createRefs()
    registerViewModel.onEvent(
        RegisterEvent.OnSetNavigator(navigator)
    )

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
                        registerViewModel.onEvent(
                            RegisterEvent.OnNavigateToHome
                        )
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
                        text = stringResource(id = R.string.register_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    // Subtitle
                    Text(
                        text = stringResource(id = R.string.regiser_subtitle),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(30.dp))

                    // Register form
                    CustomOutlineTextField(
                        value = state.email,
                        onValueChange = {
                            registerViewModel.onEvent(
                                RegisterEvent.OnEmailChange(it)
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
                            registerViewModel.onEvent(
                                RegisterEvent.OnPasswordChange(it)
                            )
                        },
                        label = "Password",
                        placeHolder = "********",
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

                    Spacer(modifier = Modifier.padding(16.dp))

                    CustomOutlineTextField(
                        value = state.firstName,
                        onValueChange = {
                            registerViewModel.onEvent(
                                RegisterEvent.OnFirstNameChange(it)
                            )
                        },
                        label = "First Name",
                        onPasswordToggleClick = {},
                        errorMessage = state.firstNameErrorMessage,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { lastNameFocusRequester.requestFocus() }),
                        modifier = Modifier.focusRequester(firstNameFocusRequester),
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    CustomOutlineTextField(
                        value = state.lastName,
                        onValueChange = {
                            registerViewModel.onEvent(
                                RegisterEvent.OnLastNameChange(it)
                            )
                        },
                        label = "Last Name",
                        onPasswordToggleClick = {},
                        errorMessage = state.lastNameErrorMessage,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        modifier = Modifier.focusRequester(lastNameFocusRequester),
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    // Terms and conditions
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
                            if(!state.isTermsAndConditionsAccepted) {
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

                    Spacer(modifier = Modifier.padding(30.dp))

                    // Login button
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        title = "Register",
                        onClick = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                            registerViewModel.onEvent(
                                RegisterEvent.OnRegister
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
                        text = "LOGIN",
                        style = MaterialTheme.typography.bodyMedium.copy (
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

            }

            if(state.isLoading) {
                // loading indicator
                CircularProgressIndicator()
            }

            if (state.snackbarErrorMessage?.isNotBlank() == true) {
                // error message
                Text(
                    text = state.snackbarErrorMessage,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    )
}