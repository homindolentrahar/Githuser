package com.homindolentrahar.githuser.presentation.user_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homindolentrahar.githuser.common.Constants
import com.homindolentrahar.githuser.domain.model.UserDetailModel
import com.homindolentrahar.githuser.domain.usecases.users.UsersUsecases
import com.homindolentrahar.githuser.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val usersUsecases: UsersUsecases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    private var getSingleUserJob: Job? = null

    init {
        savedStateHandle.get<String>(Constants.PARAM_USERNAME)?.let { username ->
            getSingleUser(username)
        }
    }

    private fun getSingleUser(username: String) {
        getSingleUserJob =
            usersUsecases.getSingleUser(username).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            userDetail = result.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        getSingleUserJob?.cancel()

        super.onCleared()
    }
}

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetailModel? = null,
    val error: String = "",
)