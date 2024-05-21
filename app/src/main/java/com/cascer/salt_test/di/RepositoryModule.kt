package com.cascer.salt_test.di

import com.cascer.salt_test.data.api.ApiService
import com.cascer.salt_test.data.repository.Repository
import com.cascer.salt_test.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepositoryImpl(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }
}