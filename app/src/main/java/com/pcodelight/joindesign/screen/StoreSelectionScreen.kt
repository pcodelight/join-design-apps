package com.pcodelight.joindesign.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.pcodelight.joindesign.AuthHelper
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.model.Store
import com.pcodelight.joindesign.repo.StoreRepository
import com.pcodelight.joindesign.ui.StoreSelectionItem
import com.pcodelight.joindesign.viewmodel.StoreSelectionViewModel
import kotlinx.android.synthetic.main.store_selection_screen_layout.*

class StoreSelectionScreen : AppCompatActivity() {
    private var viewModel: StoreSelectionViewModel = StoreSelectionViewModel(StoreRepository())
    private var itemAdapter = ItemAdapter<StoreSelectionItem>()
    private var adapter = FastAdapter.with(itemAdapter)
    private var selectedStoreId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_selection_screen_layout)

        initViewModel()
        initView()

        AuthHelper.instance.getAuthToken()?.let {
            if (it.isNotBlank()) {
                viewModel.getStores()
            } else {
                startActivity(Intent(this@StoreSelectionScreen, LoginScreen::class.java))
                finish()
            }
        }
    }

    private fun initView() {
        rvStore.layoutManager = LinearLayoutManager(this@StoreSelectionScreen, VERTICAL, false)
        rvStore.itemAnimator = null
        rvStore.adapter = adapter
    }

    private val errorObserver = Observer<String> {

    }

    private val loadingObserver = Observer<Boolean> {
        if (it) {
            loadingView.visibility = View.VISIBLE
            btnChoose.visibility = View.GONE
            rvStore.visibility = View.GONE
        } else {
            loadingView.visibility = View.GONE
            btnChoose.visibility = View.VISIBLE
            rvStore.visibility = View.VISIBLE
        }
    }

    private val storesObserver = Observer<List<Store>> { stores ->
        renderStoreItems(stores)
    }

    private fun renderStoreItems(stores: List<Store>) {
        itemAdapter.set(
            stores.map {
                StoreSelectionItem(it, it.id == selectedStoreId) { id, _ ->
                    selectedStoreId = id
                    rvStore.post {
                        renderStoreItems(stores)
                    }
                }.apply {
                    identifier = it.id.toLong()
                }
            }
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this@StoreSelectionScreen, object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return StoreSelectionViewModel(StoreRepository()) as T
                }
            })
                .get(StoreSelectionViewModel::class.java)
                .apply {
                    error.observe(this@StoreSelectionScreen, errorObserver)
                    isLoading.observe(this@StoreSelectionScreen, loadingObserver)
                    listStore.observe(this@StoreSelectionScreen, storesObserver)
                }
    }
}
