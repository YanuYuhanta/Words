/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    //property dibawah digunakan untuk melacak tata letak tempat aplikasi berada
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // pengubahan kode program dibawah berfungsi untuk menyetel LinearLayoutManager dari recyclerview
        chooseLayout()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // pada kode dibawah digunakan untuk menyetel ikon berdasarkan LinerLayoutManager dari RecyclerView
        // setIcon akan memastikan ikon sudah benar berdasarkan tata letaknya
        setIcon(layoutButton)

        return true
    }
    /*
    * kode program dibawah akan memastikan tata letak mana yang dipanggil setiap icon menu diketuk*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //pada kode program dibawah action_switch_layout akan mengubah tampilan berdasarkan id
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                // kode dibawah akan mengatru tata letak item
                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //pada kode program dibawah akan memproses dan memvalidasi bagaimana tata letak yang dipilih user
    private fun chooseLayout() {
        //dibawah merupakan validasi dari button menu
        if (isLinearLayoutManager) {
            //pada tata letak item yang pertama akan menjadi default atau memanjang kebawah
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            //dan jika validasi salah tata letak akan berubah satu baris akan ada 4 item
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }
    private fun setIcon(menuItem: MenuItem?) {
        //Atur drawable untuk ikon menu berdasarkan RecyclerView.LayoutManager yang sedang digunakan
        // Klausa if dapat digunakan di sisi kanan tugas jika semua jalur mengembalikan nilai.
        // Kode berikut setara dengan
        // jika (isLinearLayoutManager)
        // menu.icon = ContextCompat.getDrawable(ini, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(ini, R.drawable.ic_linear_layout)
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
    }

}
