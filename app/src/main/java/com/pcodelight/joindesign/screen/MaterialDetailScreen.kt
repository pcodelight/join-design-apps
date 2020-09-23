package com.pcodelight.joindesign.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.model.RawMaterial
import com.pcodelight.joindesign.repo.MaterialRepository
import com.pcodelight.joindesign.ui.*
import com.pcodelight.joindesign.viewmodel.MaterialDetailViewModel
import kotlinx.android.synthetic.main.material_detail_screen_layout.*

class MaterialDetailScreen : AppCompatActivity() {
    private val itemAdapter by lazy { ItemAdapter<AbstractItem<*>>() }
    private val adapter by lazy { FastAdapter.with(itemAdapter) }

    @Suppress("UNCHECKED_CAST")
    private val viewModel: MaterialDetailViewModel by lazy {
        ViewModelProvider(this@MaterialDetailScreen, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MaterialDetailViewModel(MaterialRepository()) as T
            }
        })
            .get(MaterialDetailViewModel::class.java)
            .apply {
                isLoading.observe(this@MaterialDetailScreen, isLoadingObserver)
                rawMaterialData.observe(this@MaterialDetailScreen, materialDataObserver)
                error.observe(this@MaterialDetailScreen, errorObserver)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.material_detail_screen_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvMaterialDetail.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        rvMaterialDetail.adapter = adapter

        viewModel.rawMaterialId = intent.getStringExtra(MATERIAL_ID_KEY) ?: ""
        viewModel.getRawMaterialDetail()
    }

    private val isLoadingObserver = Observer<Boolean> {
        if (it) {
            loadingView.visibility = View.VISIBLE
            rvMaterialDetail.visibility = View.GONE
            tvError.visibility = View.GONE
        } else {
            loadingView.visibility = View.GONE
            rvMaterialDetail.visibility = View.VISIBLE
            tvError.visibility = View.VISIBLE
        }
    }

    private val errorObserver = Observer<String> {
        if (it.isNullOrBlank()) {
            tvError.visibility = View.GONE
            rvMaterialDetail.visibility = View.VISIBLE
        } else {
            tvError.visibility = View.VISIBLE
            rvMaterialDetail.visibility = View.GONE
        }
    }

    private val materialDataObserver = Observer<RawMaterial> {
        it?.let {
            itemAdapter.set(
                listOf(
                    MaterialItem(it) {},
                    SegmentWithHorizontalChildItem(
                        "Stores",
                        it.stores.map { store ->
                            TitleDescriptionItem(
                                store.name,
                                store.description ?: ""
                            )
                        }
                    ),
                    SegmentWithHorizontalChildItem(
                        "Warehouses",
                        it.warehouses.map { warehouse ->
                            TitleDescriptionItem(
                                warehouse.name,
                                ""
                            )
                        }
                    ),
                    SegmentWithHorizontalChildItem(
                        "Supplier",
                        listOf(ContactInfoItem(it.supplier))
                    )
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val MATERIAL_ID_KEY = "material_id_key"
    }
}
