package com.circularuins.simplemercari.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.domain.usecase.MainUseCase
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var repository: MasterRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MercariApplication).component.inject(this)

        // TODO: presenter生成もDaggerで
        val presenter = MainPresenter(this, MainUseCase(repository))
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
        // TODO
    }

    override fun hideProgress() {
        // TODO
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
    }

    override fun showError(error: Throwable) {
        // TODO
    }
}
