package com.juliansuis.bistrosoft.ui.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import com.juliansuis.bistrosoft.ui.viewmodel.LifecycleViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class LifecycleObserver @Inject constructor(
    private val viewModel: LifecycleViewModel,
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        viewModel.insertLifecycleEvent(
            LifecycleEvent(
                eventName = "onCreate",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override fun onStart(owner: LifecycleOwner) {
        viewModel.insertLifecycleEvent(
            LifecycleEvent(
                eventName = "onStart",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override fun onResume(owner: LifecycleOwner) {
        viewModel.insertLifecycleEvent(
            LifecycleEvent(
                eventName = "onResume",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override fun onPause(owner: LifecycleOwner) {
        viewModel.insertLifecycleEvent(
            LifecycleEvent(
                eventName = "onPause",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override fun onStop(owner: LifecycleOwner) {
        viewModel.insertLifecycleEvent(
            LifecycleEvent(
                eventName = "onStop",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override fun onDestroy(owner: LifecycleOwner) {
        viewModel.insertLifecycleEvent(
            LifecycleEvent(
                eventName = "onDestroy",
                timestamp = System.currentTimeMillis()
            )
        )
    }
}