package com.bael.executor.demo.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bael.executor.demo.screen.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/09/20.
 */

@Module
@InstallIn(FragmentComponent::class)
@ExperimentalCoroutinesApi
object ExecutorScreenModule {

    @FragmentScoped
    @Provides
    fun Fragment.provideViewModel(): Lazy<ViewModel> = viewModels()
}
