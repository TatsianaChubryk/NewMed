package com.example.newmed.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.newmed.R
import com.example.newmed.databinding.FragmentLoginBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val subscriptions = CompositeDisposable()

    private val access = Pair("doctor", "doc123")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginObservable = Observable.create<Boolean> { emitter ->
            binding.tvLogin.addTextChangedListener {
                if (!emitter.isDisposed) {
                    emitter.onNext(it.toString().isNotEmpty())
                }
            }
        }

        val passwordObservable = Observable.create<Boolean> { emitter ->
            binding.tvPassword.addTextChangedListener {
                if (!emitter.isDisposed) {
                    emitter.onNext(it.toString().isNotEmpty())
                }
            }
        }

        val disposable = Observable
            .combineLatest(
                loginObservable,
                passwordObservable,
                { loginFilled, passwordFilled -> loginFilled && passwordFilled }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.btnLogin.isEnabled = it
            }

        subscriptions.add(disposable)

        binding.tvLogin.addTextChangedListener {
            binding.loginLayout.error = null
            binding.passwordLayout.error = null
        }

        binding.tvPassword.addTextChangedListener {
            binding.loginLayout.error = null
            binding.passwordLayout.error = null
        }

        binding.btnLogin.setOnClickListener {
            val textInputLogin: String = binding.tvLogin.text.toString()
            val textInputPassword: String = binding.tvPassword.text.toString()

            if (textInputLogin == access.first && textInputPassword == access.second) {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.frame, PriceFragment())
                    ?.commit()
            } else {
                binding.loginLayout.error = getString(R.string.fail_login)
                binding.passwordLayout.error = getString(R.string.fail_password)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}