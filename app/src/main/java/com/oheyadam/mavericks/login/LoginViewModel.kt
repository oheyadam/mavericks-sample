package com.oheyadam.mavericks.login

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.oheyadam.mavericks.di.AssistedViewModelFactory
import com.oheyadam.mavericks.di.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoginViewModel @AssistedInject constructor(
    @Assisted initialState: LoginState,
    private val login: Login,
    private val failLogin: FailLogin
) : MavericksViewModel<LoginState>(initialState) {

    init {
        onAsync(
            LoginState::request,
            onSuccess = {
                /** Do one shot type of actions like tracking **/
            },
            onFail = {
                /** Do one shot type of actions like tracking **/
            }
        )
    }

    fun login() = withState { state ->
        suspend {
            login(state.userName, state.password)
        }.execute { copy(request = it) }
    }

    fun loginError() {
        suspend {
            failLogin()
        }.execute { copy(request = it) }
    }

    fun updateUsername(userName: String) = setState { copy(userName = userName) }

    fun updatePassword(password: String) = setState { copy(password = password) }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<LoginViewModel, LoginState> {
        override fun create(state: LoginState): LoginViewModel
    }

    companion object : MavericksViewModelFactory<LoginViewModel, LoginState> by hiltMavericksViewModelFactory()
}
