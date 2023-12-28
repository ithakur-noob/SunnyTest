package com.example.sunnytask.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sunnytask.R
import com.example.sunnytask.databinding.FragmentLoginBinding
import com.example.sunnytask.showSnackBar
import com.example.sunnytask.utils.ApplicationGlobal
import com.example.sunnytask.utils.CommonFunctions
import com.example.sunnytask.webServices.ApiResponse
import com.example.sunnytask.webServices.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.loginResponse.observe(requireActivity(), Observer {
            if (activity != null) when (it) {
                is ApiResponse.Loading -> {
                    CommonFunctions.showProgress(requireActivity())
                }

                is ApiResponse.Success<*> -> {
                    CommonFunctions.dismissProgress()
                    ApplicationGlobal.accessToken = it.data.toString()
                    findNavController().navigate(R.id.navigate_to_msg)
                }

                is ApiResponse.Error -> {
                    CommonFunctions.dismissProgress()
                    binding.root.showSnackBar("${it.errorMessage}")
                    // Handle error with response.errorMessage
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            if (binding.etPhone.text.toString().trim()
                    .isEmpty()
            ) binding.root.showSnackBar("Please Enter Phone.")
            else if (binding.etPass.text.toString().trim()
                    .isEmpty()
            ) binding.root.showSnackBar("Please Enter Password.")
            else {
                val hash = HashMap<String, String>()
                hash["phone"] = binding.etPhone.text.toString()
                hash["password"] = binding.etPass.text.toString()
                viewModel.login(hash)
            }
        }
        binding.etPass.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.etPass.right - binding.etPass.compoundDrawablesRelative[2].bounds.width()) {
                    // Toggle password visibility
                    togglePasswordVisibility(binding.etPass)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }
    private fun togglePasswordVisibility(editText: EditText) {
        val selectionStart = editText.selectionStart
        val selectionEnd = editText.selectionEnd

        if (editText.transformationMethod == PasswordTransformationMethod.getInstance()) {
            setDrawableEnd(editText,R.drawable.outline_visibility_24)
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            setDrawableEnd(editText,R.drawable.outline_visibility_off_24)
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        editText.setSelection(selectionStart, selectionEnd)
    }
    private fun setDrawableEnd(editText: EditText, drawableResId: Int) {
        val drawable: Drawable? = ContextCompat.getDrawable(requireActivity(), drawableResId)
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, 0, drawableResId, 0
        )
    }
}