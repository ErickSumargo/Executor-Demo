package com.bael.executor.demo.lib.threading.di.module

import com.bael.executor.demo.lib.threading.executor.concurrent.contract.ConcurrentExecutor
import com.bael.executor.demo.lib.threading.executor.concurrent.implementation.ConcurrentExecutorImpl
import com.bael.executor.demo.lib.threading.executor.conflated.contract.ConflatedExecutor
import com.bael.executor.demo.lib.threading.executor.conflated.implementation.ConflatedExecutorImpl
import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.implementation.ExecutorImpl
import com.bael.executor.demo.lib.threading.executor.queue.contract.QueueExecutor
import com.bael.executor.demo.lib.threading.executor.queue.implementation.QueueExecutorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by ErickSumargo on 01/09/20.
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class ExecutorModule {

    @Binds
    abstract fun bindExecutor(executor: ExecutorImpl): Executor

    @Binds
    abstract fun bindConcurrentExecutor(executor: ConcurrentExecutorImpl): ConcurrentExecutor

    @Binds
    abstract fun bindQueueExecutor(executor: QueueExecutorImpl): QueueExecutor

    @Binds
    abstract fun bindConflatedExecutor(executor: ConflatedExecutorImpl): ConflatedExecutor
}
