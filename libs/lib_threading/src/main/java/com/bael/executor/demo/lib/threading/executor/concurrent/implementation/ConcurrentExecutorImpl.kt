package com.bael.executor.demo.lib.threading.executor.concurrent.implementation

import com.bael.executor.demo.lib.threading.executor.concurrent.contract.ConcurrentExecutor
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class ConcurrentExecutorImpl @Inject constructor() : ConcurrentExecutor {

    override suspend fun <T> run(block: suspend () -> T): T {
        return block()
    }
}
