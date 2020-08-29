package com.bael.executor.demo.lib.threading.executor.concurrent

import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/09/20.
 */

class ConcurrentExecutor @Inject constructor() {

    suspend fun <T> run(block: suspend () -> T): T {
        return block()
    }
}
