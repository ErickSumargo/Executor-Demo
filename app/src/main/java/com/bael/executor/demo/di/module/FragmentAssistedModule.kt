package com.bael.executor.demo.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Created by ErickSumargo on 01/09/20.
 */

@AssistedModule
@Module(includes = [AssistedInject_FragmentAssistedModule::class])
@InstallIn(FragmentComponent::class)
abstract class FragmentAssistedModule
