package com.circularuins.simplemercari.app.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.app.ApiErrorView
import com.circularuins.simplemercari.app.list.ListFragment
import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.domain.usecase.StartUseCase
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.CompletableSource
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity :  RxAppCompatActivity(), MainContract.View, ApiErrorView {

    @Inject
    lateinit var repository: MasterRepository

    override fun requestScope(): CompletableSource {
        return AndroidLifecycleScopeProvider.from(this).requestScope()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MercariApplication).component.inject(this)

        // TODO: presenter生成もDaggerで
        val presenter = MainPresenter(this, this, this, StartUseCase(repository))
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
                return ListFragment.newInstance(masters[position].requestType)
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return masters[position].name
            }
        }
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun showNetworkError() {
        Toast.makeText(this, getString(R.string.message_error_network), Toast.LENGTH_LONG).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
