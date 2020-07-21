package com.payday.bank.view.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.payday.bank.presentation.base.BaseViewModel
import com.payday.bank.presentation.base.ViewModelFactory
import com.payday.bank.util.extension.getGenericClass
import com.payday.bank.view.activity.BaseActivity
import dagger.android.support.DaggerFragment
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    DaggerFragment(contentLayoutId) {

    @get:MenuRes protected open val menuRes: Int? = null

    @Inject protected lateinit var viewModelFactory: ViewModelFactory

    @Inject protected lateinit var router: Router

    protected val viewModel: VM by lazy {
        val provider = ViewModelProvider(this, viewModelFactory)
        val viewModelClass = javaClass.getGenericClass<VM>(0)
        provider.get(viewModelClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorLiveEvent.observe(viewLifecycleOwner, ::onError)

        setHasOptionsMenu(menuRes?.takeIf { it != ResourcesCompat.ID_NULL } != null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menuRes?.let { inflater.inflate(it, menu) }
    }

    protected fun setHomeIconEnabled(enable: Boolean) {
        getBaseActivity()?.supportActionBar?.setDisplayHomeAsUpEnabled(enable)
    }

    protected open fun onError(errorMessage: String) {
        getBaseActivity()?.onError(errorMessage) ?: Timber.e(errorMessage)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                router.exit();
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    protected fun requireBaseActivity() = requireActivity() as BaseActivity<*>

    protected fun getBaseActivity() = activity as BaseActivity<*>?
}