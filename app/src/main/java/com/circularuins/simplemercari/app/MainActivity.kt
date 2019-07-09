package com.circularuins.simplemercari.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.repository.MasterRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var masterRepository: MasterRepository
    @Inject
    lateinit var itemRepository: ItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MercariApplication).component.inject(this)

        // TODO: 消す
        masterRepository.getMaster()
            .subscribe({
                val hoge = it.get(0)
                print(hoge.name)
            }, {
                print(it)
            })

        // TODO: 消す
        itemRepository.getItems("women")
            .subscribe({
                val hoge = it.get(0)
                print(hoge.name)
            }, {
                print(it)
            })
    }
}
