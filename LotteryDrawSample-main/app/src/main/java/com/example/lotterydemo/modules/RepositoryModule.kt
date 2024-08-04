package com.example.lotterydemo.modules

import android.content.Context
import com.example.lotterydemo.data.repo.LotteryRepository
import com.example.lotterydemo.data.repo.impls.LotteryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLotteryRepository(
        @ApplicationContext appContext: Context,
    ): LotteryRepository =
        LotteryRepositoryImpl(
            dispatcher = Dispatchers.IO
        )

}