package com.juliansuis.bistrosoft.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import com.juliansuis.bistrosoft.ui.utils.ScreenState
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    lifecycleEvents: List<LifecycleEvent>,
    uiState: ScreenState<String>,
    onGetTime: () -> Unit,
) {

    fun calculateFactorial(number: Int): String {
        var result: Long = 1
        for (i in 2..number) {
            result *= i
        }

        return result.toString()
    }

    fun timestampToDateTime(timestamp: Long, context: Context): String {
        val date = Date(timestamp)
        val sdf = SimpleDateFormat("dd-MM-yy HH:mm", context.resources.configuration.locales[0])
        return sdf.format(date)
    }

    val context = LocalContext.current
    var hasTimeBtnBeenPressed by rememberSaveable { mutableStateOf(false) }
    var inputNumber by rememberSaveable { mutableStateOf("") }
    var outputNumber by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Input a number to get the factorial:",
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 36.dp, bottom = 4.dp),
        )
        OutlinedTextField(
            value = inputNumber,
            onValueChange = {
                inputNumber = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            maxLines = 1,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    outputNumber = if (inputNumber.isNotEmpty()) {
                        calculateFactorial(inputNumber.toInt())
                    } else {
                        "Input a number"
                    }
                }
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    keyboardController?.hide()
                    outputNumber = if (inputNumber.isNotEmpty()) {
                        calculateFactorial(inputNumber.toInt())
                    } else {
                        "Input a number"
                    }
                },
                modifier = Modifier.height(70.dp)
            ) {
                Text("Calculate factorial")
            }
            Button(
                onClick = {
                    hasTimeBtnBeenPressed = true
                    onGetTime()
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
            ) {
                if (!hasTimeBtnBeenPressed) {
                    Text("Get DateTime")
                } else {
                    when (uiState) {
                        is ScreenState.Error -> Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )

                        ScreenState.Loading -> CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        is ScreenState.Success -> {
                            Text("Get DateTime")
                        }
                    }
                }
            }
        }
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            Text(
                text = "The result is: ",
                fontSize = 24.sp,
            )
            Text(
                text = outputNumber,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp))
        Text(
            text = "Lifecycle events:",
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
        )
        LazyColumn(
            content = {
                items(lifecycleEvents) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = it.eventName,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = timestampToDateTime(it.timestamp, context),
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Divider()
                }
            },
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        lifecycleEvents = listOf(
            LifecycleEvent(1, "Event 1", 123),
            LifecycleEvent(2, "Event 2", 234),
            LifecycleEvent(3, "Event 3", 345),
            LifecycleEvent(4, "Event 4", 456),
        ),
        uiState = ScreenState.Loading,
        onGetTime = {}
    )
}
