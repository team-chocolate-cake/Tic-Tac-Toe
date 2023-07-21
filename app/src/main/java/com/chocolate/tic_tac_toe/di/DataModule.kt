package com.chocolate.tic_tac_toe.di

import com.chocolate.tic_tac_toe.data.local.PlayerAvatars
import com.chocolate.tic_tac_toe.data.local.PlayerDataStorage
import com.chocolate.tic_tac_toe.data.local.playerdata.PlayerAvatarsImp
import com.chocolate.tic_tac_toe.data.local.playerdata.PlayerDataStorageImp
import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.data.remote.firebase.FirebasePlayerDatabaseImp
import com.chocolate.tic_tac_toe.data.remote.firebase.FirebaseSessionDatabaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindPlayerDataStorage(
        playerDataStorageImp: PlayerDataStorageImp
    ): PlayerDataStorage

    @Binds
    abstract fun bindPlayerAvatars(
        playerAvatarsImp: PlayerAvatarsImp
    ): PlayerAvatars

    @Binds
    abstract fun bindFirebasePlayerDatabase(
        firebasePlayerDatabaseImp: FirebasePlayerDatabaseImp
    ): FirebasePlayerDatabase

    @Binds
    abstract fun bindFirebaseSessionDatabase(
        firebaseSessionDatabaseImp: FirebaseSessionDatabaseImp
    ): FirebaseSessionDatabase
}