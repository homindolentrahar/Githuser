package com.homindolentrahar.githuser.di

import com.homindolentrahar.githuser.data.remote.UserApiService
import com.homindolentrahar.githuser.data.repository.UserRepositoryImpl
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.domain.usecases.users.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUsersRepository(
        apiService: UserApiService
    ): UserRepository = UserRepositoryImpl(apiService)
}