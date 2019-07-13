package com.circularuins.simplemercari.app.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.app.ApiErrorView
import com.circularuins.simplemercari.app.viewdata.ListViewData
import com.circularuins.simplemercari.domain.repository.ItemRepository
import com.circularuins.simplemercari.domain.usecase.ListUseCase
import com.trello.rxlifecycle2.components.support.RxFragment
import com.ubercab.autodispose.rxlifecycle.RxLifecycleInterop
import io.reactivex.CompletableSource
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : RxFragment(), ListContract.View, ApiErrorView {

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

    override fun requestScope(): CompletableSource {
        return RxLifecycleInterop.from(this).requestScope()
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
        (activity?.application as MercariApplication).component.inject(this)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = context ?: return
        // TODO: presenter生成もDaggerで
        val presenter = ListPresenter(context, this, this, ListUseCase(repository))
        presenter.start(requestType)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun setList(items: List<ListViewData>) {
        val context = context ?: return
        item_list.adapter = ItemsAdapter(context, items)
        item_list.layoutManager = GridLayoutManager(context, 2)
    }

    override fun showNetworkError() {
        Toast.makeText(context, getString(R.string.message_error_network), Toast.LENGTH_LONG).show()
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}