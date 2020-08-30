package com.bael.executor.demo.lib.threading.contract

import com.bael.executor.demo.lib.threading.executor.schema.ExecutorSchema
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by ErickSumargo on 15/09/20.
 */

interface Threading : CoroutineScope {

    fun <T> launch(
        thread: CoroutineContext,
        schema: ExecutorSchema,
        block: suspend CoroutineScope.() -> T
    )
}
