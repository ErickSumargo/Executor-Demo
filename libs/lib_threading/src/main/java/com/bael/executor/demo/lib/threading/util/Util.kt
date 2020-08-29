package com.bael.executor.demo.lib.threading.util

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Created by ErickSumargo on 01/09/20.
 */

object Util {
    val MainThread: CoroutineContext
        get() = Main + SupervisorJob()

    val DefaultThread: CoroutineContext
        get() = Default + SupervisorJob()
}
