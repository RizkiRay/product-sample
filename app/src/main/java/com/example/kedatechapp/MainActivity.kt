package com.example.kedatechapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kedatechapp.data.db.AppDatabase
import com.example.kedatechapp.data.db.dao.ProductDao
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        insertDummyData()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun insertDummyData() {
        // insert dummy data here
        val productDao = AppDatabase.invoke(application).ProductDao()
        lifecycleScope.launch(Dispatchers.IO) {
            productDao.insertAll(
                *arrayOf(
                    Product(
                        1,
                        "Deadpool Mask",
                        "https://images.tokopedia.net/img/cache/900/product-1/2018/4/4/0/0_836bb635-878d-40b9-85be-fca6b18e8539_1080_1073.jpg",
                        "Topeng deadpool berwarna memrah yang dapat digunakan sebagai kostum halloween.",
                        400000,
                        false
                    ),
                    Product(
                        2,
                        "Action Figure Pokemon",
                        "https://images.tokopedia.net/img/cache/900/VqbcmM/2022/1/21/1cbb4daf-657e-498c-8e81-adbbadb6e13f.jpg",
                        "Pokemon atau pocket monster adalah salah satu karakter animasi buatan Jepang yang sampai saat ini banyak penggemarnya",
                        50000,
                        false
                    ),
                    Product(
                        3,
                        "Kingkong Skull Island",
                        "https://images.tokopedia.net/img/cache/900/VqbcmM/2022/10/24/17bb5162-d580-4eb5-bcbe-90bc8175bf5e.jpg",
                        "Mainan patung berbentuk KingKong di Skull Island",
                        67000,
                        false
                    ),
                    Product(
                        4,
                        "Vegeta",
                        "https://images.tokopedia.net/img/cache/900/VqbcmM/2023/9/14/a37ef924-9e2a-4575-90d3-788ab9c51ca2.jpg",
                        "Action Figure Vegeta sedang mengamuk.",
                        135000,
                        false
                    ),
                    Product(
                        5,
                        "Spirit Bomb Goku",
                        "https://images.tokopedia.net/img/cache/900/VqbcmM/2024/3/17/2490f7e7-0332-4180-b21c-960f1807939f.png",
                        "Statue Dragon Ball Goku Spirit Bomb Hunter Studio design recast LED",
                        250000,
                        false
                    ),
                    Product(
                        6,
                        "Figure Hulkbuster",
                        "https://images.tokopedia.net/img/cache/900/hDjmkQ/2021/2/2/bf1b19e7-0b4b-41cb-b8a1-5e4dbeedc832.jpg",
                        "helm ironman dapat dibuka, Badan dapat mengeluarkan lampu LED",
                        100000,
                        false
                    ),
                    Product(
                        7,
                        "Action Figure Iron-man",
                        "https://images.tokopedia.net/img/cache/900/VqbcmM/2023/2/21/8484e44f-2df8-4a85-8d7f-d26fa423eaa3.jpg",
                        "Dibuat oleh tangan para ahli dalam bidangnya sehingga Finishing produk sudah dipastikan berkualitas Premium dan Terbaik dikelasnya.",
                        530000,
                        false
                    ),
                    Product(
                        8,
                        "Action Figure Charmander",
                        "https://images.tokopedia.net/img/cache/900/hDjmkQ/2023/7/28/e519a1cf-ed64-4bde-9016-59c5b615a69f.jpg",
                        "Seri Pokemon Moncolle EX adalah mainan karakter Pokemon yang dikeluarkan oleh Takara Tomy",
                        83000,
                        false
                    ),
                    Product(
                        9,
                        "Boneka Baby Yoda",
                        "https://images.tokopedia.net/img/cache/900/hDjmkQ/2022/8/5/50ff4f00-8c5d-46b0-a742-7448be47a42d.jpg",
                        "Star Wars Grogu/The Child/Baby Yoda Snackin' Animatronic adalah mainan yang selalu lapar!",
                        1350000,
                        false
                    ),
                    Product(
                        10,
                        "Action Figure Tyrannosaurus-Rex",
                        "https://images.tokopedia.net/img/cache/900/VqbcmM/2020/11/8/e937c6ac-2a2f-478c-b3d5-35a4328b6495.jpg",
                        "banyak varian baru macam macam dinosaurus",
                        50000,
                        false
                    )
                )
            )
        }
    }
}