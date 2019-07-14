package com.circularuins.simplemercari.app.main

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.app.list.ItemListFragment
import com.circularuins.simplemercari.di.MainModule
import com.circularuins.simplemercari.di.NetModule
import com.circularuins.simplemercari.domain.model.Master
import com.google.android.material.snackbar.Snackbar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.CompletableSource
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity :  RxAppCompatActivity(), MainContract.View, ApiErrorView {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun requestScope(): CompletableSource {
        return AndroidLifecycleScopeProvider.from(this).requestScope()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MercariApplication)
            .component
            .plus(
                MainModule(this, this, this),
                NetModule()
            )
            .inject(this)

        presenter.start()
    }

    override fun initToolBar() {
        toolbar.apply {
            setLogo(R.mipmap.icon_launcher)
            title = getString(R.string.toolbar_name)
        }
        setSupportActionBar(toolbar)
        ViewCompat.setElevation(toolbar, 10f)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun setTab(masters: List<Master>) {
        val adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return masters.size
            }

            override fun getItem(position: Int): Fragment {
                return ItemListFragment.newInstance(masters[position].requestType)
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return masters[position].name
            }
        }
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun showNetworkError() {
        showSnackBar(getString(R.string.message_error_network))
    }

    override fun showError(error: String) {
        showSnackBar(error)
    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(
            parent_constraint,
            message,
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.setAction(getString(R.string.label_reconnect)) {
            presenter.start()
        }
        snackBar.show()
    }
}
