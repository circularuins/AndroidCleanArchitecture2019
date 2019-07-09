package com.circularuins.simplemercari.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.domain.model.Master
import com.circularuins.simplemercari.domain.repository.MasterRepository
import com.circularuins.simplemercari.domain.usecase.MainUseCase
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

    override fun showProgress() {
        // TODO
    }

    override fun hideProgress() {
        // TODO
    }

    override fun setTab(masters: List<Master>) {
        // TODO
    }

    override fun showError(error: Throwable) {
        // TODO
    }
}
