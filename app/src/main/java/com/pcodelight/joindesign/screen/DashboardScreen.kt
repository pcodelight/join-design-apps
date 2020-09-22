package com.pcodelight.joindesign.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.model.RawMaterial
import com.pcodelight.joindesign.model.Store
import com.pcodelight.joindesign.repo.MaterialRepository
import com.pcodelight.joindesign.repo.StoreRepository
import com.pcodelight.joindesign.ui.DividerItem
import com.pcodelight.joindesign.ui.LoadingItem
import com.pcodelight.joindesign.ui.MaterialItem
import com.pcodelight.joindesign.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.dashboard_screen_layout.*

class DashboardScreen : AppCompatActivity() {
    lateinit var viewModel: DashboardViewModel

    private val footerAdapter = ItemAdapter<AbstractItem<*>>()
    private val materialAdapter = ItemAdapter<AbstractItem<*>>()
    private val adapter = FastAdapter.with(listOf(materialAdapter, footerAdapter))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_screen_layout)

        initViewModel()

        val intentData = intent.extras?.getSerializable(STORE_UUID_KEY) as? Store
        intentData?.let {
            viewModel.selectedStore = it
        }
        viewModel.getRawMaterials("")

        initView()
    }

    private fun initView() {
        tvName.text = viewModel.selectedStore.name

        rvMaterial.layoutManager = LinearLayoutManager(
            this@DashboardScreen,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvMaterial.adapter = adapter
    }

    @Suppress("UNCHECKED_CAST")
    private fun initViewModel() {
        viewModel = ViewModelProvider(this@DashboardScreen, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DashboardViewModel(StoreRepository(), MaterialRepository()) as T
            }
        })
            .get(DashboardViewModel::class.java)
            .apply {
                isLoadMore.observe(this@DashboardScreen, isLoadMoreObserver)
                isLoading.observe(this@DashboardScreen, isLoadingObserver)
                error.observe(this@DashboardScreen, errorObserver)

                materialResponse.observe(
                    this@DashboardScreen,
                    materialResponseObserver
                )

                additionalPageResponse.observe(
                    this@DashboardScreen,
                    additionalPageResponseObserver
                )
            }
    }

    private val isLoadMoreObserver = Observer<Boolean> {
        if (it) {
            footerAdapter.set(listOf(
                LoadingItem()
            ))
        } else {
            footerAdapter.clear()
        }
    }

    private val isLoadingObserver = Observer<Boolean> {
        if (it) {
            lvParent.visibility = View.VISIBLE
            rvMaterial.visibility = View.GONE
        } else {
            lvParent.visibility = View.GONE
            rvMaterial.visibility = View.VISIBLE
        }
    }

    private val errorObserver = Observer<String> {
        if (it.isNotBlank()) {
            Toast.makeText(this@DashboardScreen, it, Toast.LENGTH_SHORT).show()
            Log.d("dashboardScreen", it)
        }
    }

    private val materialResponseObserver = Observer<List<RawMaterial>> { rawMaterials ->
        val items = mutableListOf<AbstractItem<*>>()
        rawMaterials.forEach {
            items.add(DividerItem())
            items.add(MaterialItem(it))
        }

        materialAdapter.set(items)
    }

    private val additionalPageResponseObserver = Observer<List<RawMaterial>> { rawMaterials ->
        rawMaterials.forEach {
            materialAdapter.add(DividerItem())
            materialAdapter.add(MaterialItem(it))
        }
    }

    companion object {
        const val STORE_UUID_KEY = "store_uuid_key"
    }
}
