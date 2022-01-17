package com.oheyadam.mavericks.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.oheyadam.mavericks.R
import com.oheyadam.mavericks.databinding.LoginFragmentBinding
import com.oheyadam.mavericks.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment), MavericksView {

    private val binding: LoginFragmentBinding by viewBinding()
    private val viewModel: LoginViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onAsync(
            LoginState::request,
            onSuccess = { findNavController().navigate(R.id.action_LoginFragment_to_VehiclesFragment) }
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            username.doAfterTextChanged { text ->
                viewModel.updateUsername(text.toString())
            }
            password.doAfterTextChanged { text ->
                viewModel.updatePassword(text.toString())
            }
            login.setOnClickListener {
                viewModel.login()
            }
            loginError.setOnClickListener {
                viewModel.loginError()
            }
            withState(viewModel) { state ->
                username.setText(state.userName)
                password.setText(state.password)
            }
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        with(binding) {
            loading.isVisible = state.isLoading
            login.isEnabled = state.canSubmit
            error.isVisible = state.isFail
            content.isVisible = !state.isFail
        }
    }
}
