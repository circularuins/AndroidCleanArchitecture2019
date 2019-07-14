package com.circularuins.simplemercari.app.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.circularuins.simplemercari.MercariApplication
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.app.common.ApiErrorView
import com.circularuins.simplemercari.app.viewdata.ListViewData
import com.circularuins.simplemercari.di.ListModule
import com.circularuins.simplemercari.di.NetModule
import com.google.android.material.snackbar.Snackbar
import com.trello.rxlifecycle2.components.support.RxFragment
import com.ubercab.autodispose.rxlifecycle.RxLifecycleInterop
import io.reactivex.CompletableSource
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

/**
 * 商品一覧画面
 */
class ItemListFragment : RxFragment(), ItemListContract.View, ApiErrorView {

    private lateinit var requestType: String

    @Inject
    lateinit var presenter: ItemListContract.Presenter

    // 画面回転時のために商品リストを保持する
    private var storedItems = listOf<ListViewData>()

    companion object {
        private const val REQUEST_TYPE = "request_type"

        fun newInstance(requestType: String): ItemListFragment {
            val fragment = ItemListFragment()
            fragment.arguments = Bundle().apply {
                putString(REQUEST_TYPE, requestType)
            }
            return fragment
        }
    }

    // 非同期処理で参照するライフサイクルのスコープ
    override fun requestScope(): CompletableSource {
        return RxLifecycleInterop.from(this).requestScope()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 画面開店時にフラグメントを再生成しない
        retainInstance = true

        requestType = arguments?.getString(REQUEST_TYPE,"") ?: ""

        val context = context ?: return
        (activity?.application as MercariApplication)
            .component
            .plus(
                ListModule(context, this, this),
                NetModule()
            )
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 商品リストが保存されていた場合
        if (storedItems.isNotEmpty()) {
            // 保存されたリストを表示する
            setList(storedItems)
            return
        }

        presenter.start(requestType)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun setList(items: List<ListViewData>) {
        // 画面回転時のために商品リストを保持しておく
        storedItems = items

        val context = context ?: return
        item_list.adapter = ItemsAdapter(context, items)
        item_list.layoutManager = GridLayoutManager(context, 2)
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
            presenter.start(requestType)
        }
        snackBar.show()
    }
}