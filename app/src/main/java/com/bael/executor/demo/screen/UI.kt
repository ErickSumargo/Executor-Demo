package com.bael.executor.demo.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bael.executor.demo.R
import com.bael.executor.demo.databinding.ExecutorLayoutBinding
import com.bael.executor.demo.ext.textOf
import com.bael.executor.demo.lib.base.ext.viewBinding
import com.bael.executor.demo.lib.base.ui.BaseUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/09/20.
 */

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class UI :
    BaseUI<Dispatcher.Factory, ViewModel>(R.layout.executor_layout),
    Renderer {
    private val viewBinder by viewBinding(ExecutorLayoutBinding::bind)

    private val args: UIArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dispatcherFactory.create(
            viewModel,
            renderer = this
        ).observe(lifecycleOwner = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setType(args.type)
    }

    override fun renderSummary(task: Int) {
        viewBinder.summaryView.also { view ->
            view.text = context?.textOf(R.string.summary, task)
        }
    }

    override fun renderCTA(type: String, task: Int) {
        viewBinder.ctaView.also { view ->
            view.setOnClickListener {
                viewModel.assignTask(task = task + 1)
                viewModel.launchTask(type, task = task + 1)
            }
        }
    }

    override fun renderProgress(progress: String) {
        viewBinder.progressView.also { view ->
            view.text = progress
        }
    }
}
