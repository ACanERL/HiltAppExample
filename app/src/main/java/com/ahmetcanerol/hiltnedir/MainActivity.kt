package com.ahmetcanerol.hiltnedir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetcanerol.hiltnedir.databinding.ActivityMainBinding
import com.ahmetcanerol.hiltnedir.model.Datum
import com.ahmetcanerol.hiltnedir.model.Root
import com.ahmetcanerol.hiltnedir.viewmodel.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
     lateinit var mainViewModel: CoinViewModel
     private var adapter=CoinAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel= ViewModelProvider(this)[CoinViewModel::class.java]
        setObserverData()
        adapter.onItemClick={item->
            println(item.name)
        }
        binding.swiperefreslayout.setOnRefreshListener {
            setObserverData()
            binding.swiperefreslayout.isRefreshing=false
        }

    }
    private fun setObserverData(){
        var list=ArrayList<Datum>()
        val data = ArrayList<Root>()
        mainViewModel.getCoins().observe(this, Observer {
            data.addAll(listOf(it))
            it.data.let {
                if (it != null) {
                    list.addAll(it)
                }
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            adapter.setCoin(list)
            binding.recyclerView.adapter = adapter
        })
    }

}