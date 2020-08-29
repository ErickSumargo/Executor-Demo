package com.bael.executor.demo.lib.threading.di.module

import com.bael.executor.demo.lib.threading.executor.contract.Executor
import com.bael.executor.demo.lib.threading.executor.implementation.DefaultExecutor
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
    abstract fun bindExecutor(executor: DefaultExecutor): Executor
}
