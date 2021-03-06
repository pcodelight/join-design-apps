package com.pcodelight.joindesign.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    private var selectedStore: Store? = null

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

        btnChoose.setOnClickListener {
            selectedStore?.let {
                startActivityForResult(Intent(this@StoreSelectionScreen, DashboardScreen::class.java).apply {
                    putExtra(DashboardScreen.STORE_UUID_KEY, it)
                }, ACTIVITY_FROM_STORE_SELECTION)
            }
        }
    }

    private val errorObserver = Observer<String> {
        if (it.isNotBlank()) {
            Toast.makeText(this@StoreSelectionScreen, it, Toast.LENGTH_LONG).show()
        }
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

    private fun onItemSelected(store: Store) {
        rvStore.post {
            selectedStore = store
            viewModel.listStore.value?.let {
                renderStoreItems(it)
            }
            btnChoose.visibility = View.VISIBLE
        }
    }

    private fun renderStoreItems(stores: List<Store>) {
        itemAdapter.set(
            stores.map {
                StoreSelectionItem(it, it.id == selectedStore?.id) {
                    onItemSelected(it)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ACTIVITY_FROM_STORE_SELECTION && resultCode == LOGOUT_RESULT_CODE) {
            startActivity(Intent(this, LoginScreen::class.java))
            this@StoreSelectionScreen.finish()
        }
    }

    companion object {
        const val ACTIVITY_FROM_STORE_SELECTION = 1
        const val LOGOUT_RESULT_CODE = 2
    }
}
