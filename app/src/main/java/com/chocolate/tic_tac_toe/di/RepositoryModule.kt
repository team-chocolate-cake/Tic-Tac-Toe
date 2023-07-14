package com.chocolate.tic_tac_toe.di

import com.chocolate.tic_tac_toe.data.repository.GameRepositoryImp
import com.chocolate.tic_tac_toe.data.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGameRepository(
        gameRepositoryImp: GameRepositoryImp
    ): GameRepository
}