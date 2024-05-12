package com.jess.nbcamp.challenge3.presentation.search.di

import com.jess.nbcamp.challenge3.data.repository.SearchRepositoryImpl
import com.jess.nbcamp.challenge3.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class BindModule {

    @ViewModelScoped
    @Binds
    abstract fun bindSearchRepository(
        repository: SearchRepositoryImpl
    ): SearchRepository
}