package com.bael.executor.demo.lib.base.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.bael.executor.demo.lib.threading.contract.Threading
import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema
import com.bael.executor.demo.lib.threading.util.Util.MainThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by ErickSumargo on 01/09/20.
 */

abstract class BaseUI<DF : Any, VM : ViewModel>(layoutRes: Int) :
    Fragment(layoutRes),
    Threading {
    override val coroutineContext: CoroutineContext = MainThread

    @Inject
    protected lateinit var dispatcherFactory: DF

    @Inject
    internal lateinit var viewModelDeferred: @JvmSuppressWildcards Lazy<VM>

    @Inject
    internal lateinit var executor: Executor

    protected val viewModel: VM by lazy { viewModelDeferred.value }

    override fun <T> launch(
        thread: CoroutineContext,
        schema: ExecutorSchema,
        block: suspend CoroutineScope.() -> T
    ) {
        lifecycleScope.launch(context = thread) {
            executor.execute(schema) { block() }
        }
    }
}
