package com.example.ecommerceapp.features.auth.presentation.registration.forget_password

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    route = Routes.FORGET_PASSWORD
)
fun ForgetPasswordScreen(
   forgetPasswordViewModel: ForgetPasswordViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val state = forgetPasswordViewModel.state
    val (emailFocusRequester, _) = FocusRequester.createRefs()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 30.dp)
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                })
            },

        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {


                    // Title
                    Text(
                        text = stringResource(id = R.string.forget_password_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    // Subtitle
                    Text(
                        text = stringResource(id = R.string.forget_password_subtitile),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.padding(30.dp))

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
                        modifier = Modifier.focusRequester(emailFocusRequester),
                    )

                    Spacer(modifier = Modifier.padding(30.dp))

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