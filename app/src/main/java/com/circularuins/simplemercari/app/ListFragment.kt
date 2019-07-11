package com.circularuins.simplemercari.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.app.viewdata.Item
import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.usecase.ListUseCase
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : Fragment(), ListContract.View {

    private lateinit var requestType: String

    @Inject
    lateinit var repository: ItemRepository

    companion object {
        private const val REQUEST_TYPE = "request_type"

        fun newInstance(requestType: String): ListFragment {
            val fragment = ListFragment()
            fragment.arguments = Bundle().apply {
                putString(REQUEST_TYPE, requestType)
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestType = arguments?.getString(REQUEST_TYPE,"") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        (activity?.application as MercariApplication).component.inject(this)

        // TODO: presenter生成もDaggerで
        val presenter = ListPresenter(this, ListUseCase(repository))
        presenter.start(requestType)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //sampleText.text = requestType
    }

    override fun showProgress() {
        // TODO:
    }

    override fun hideProgress() {
        // TODO:
    }

    override fun setList(items: List<Item>) {
        val context = context ?: return
        item_list.adapter = ItemsAdapter(context, items)
        item_list.layoutManager = GridLayoutManager(context, 2)
    }

    override fun showError(error: Throwable) {
        // TODO:
    }
}