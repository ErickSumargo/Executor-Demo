package com.bael.executor.demo.lib.threading.executor.concurrent.contract

/**
 * Created by ErickSumargo on 01/09/20.
 */

interface ConcurrentExecutor {

    suspend fun <T> run(block: suspend () -> T): T
}
