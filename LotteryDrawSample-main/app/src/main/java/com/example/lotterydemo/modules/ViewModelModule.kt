package com.example.lotterydemo.modules

import com.example.lotterydemo.presentation.screens.drawdetails.DrawDetailsViewModelFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun drawDetailsViewModelFactory(): DrawDetailsViewModelFactory
}