package com.juliansuis.bistrosoft

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import com.juliansuis.bistrosoft.ui.screen.HomeScreen
import com.juliansuis.bistrosoft.ui.theme.BistrosoftTheme
import com.juliansuis.bistrosoft.ui.utils.ScreenState
import com.juliansuis.bistrosoft.ui.viewmodel.HomeViewModel
import com.juliansuis.bistrosoft.ui.viewmodel.LifecycleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleOwner {

    private val homeViewModel: HomeViewModel by viewModels()
    private val lifecycleViewModel: LifecycleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleViewModel.insertLifecycleEvent(
            LifecycleEvent(eventName = "OnCreate", timestamp = System.currentTimeMillis())
        )
        setContent {
            BistrosoftTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val uiState by homeViewModel.uiState.collectAsState()
                    val lifecycleList by lifecycleViewModel.events.collectAsState()

                    if (uiState is ScreenState.Success) {
                        val data = (uiState as ScreenState.Success).data
                        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
                    }
                    HomeScreen(
                        lifecycleEvents = lifecycleList,
                        uiState = uiState,
                        onGetTime = {
                            homeViewModel.fetchTime()
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleViewModel.insertLifecycleEvent(
            LifecycleEvent(eventName = "OnStart", timestamp = System.currentTimeMillis())
        )
    }

    override fun onPause() {
        super.onPause()
        lifecycleViewModel.insertLifecycleEvent(
            LifecycleEvent(eventName = "OnPause", timestamp = System.currentTimeMillis())
        )
    }

    override fun onStop() {
        super.onStop()
        lifecycleViewModel.insertLifecycleEvent(
            LifecycleEvent(eventName = "OnStop", timestamp = System.currentTimeMillis())
        )
    }

    override fun onResume() {
        super.onResume()
        lifecycleViewModel.insertLifecycleEvent(
            LifecycleEvent(eventName = "OnResume", timestamp = System.currentTimeMillis())
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleViewModel.insertLifecycleEvent(
            LifecycleEvent(eventName = "OnDestroy", timestamp = System.currentTimeMillis())
        )
    }
}
