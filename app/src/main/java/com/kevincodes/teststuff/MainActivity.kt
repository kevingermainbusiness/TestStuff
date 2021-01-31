package com.kevincodes.teststuff

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.doOnLayout
import androidx.lifecycle.lifecycleScope
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kevincodes.teststuff.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val glide: RequestManager by lazy {
        Glide.with(this).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_android)
                .error(R.drawable.ic_baseline_error_outline_24)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }

    private val scope: CoroutineScope by lazy { CoroutineScope(Dispatchers.Main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mToolbar)
        lifecycleScope.launchWhenCreated {
            val dummyList = StuffUtil.createDummyList()
            val dummyAdapter = DummyAdapter()
            dummyAdapter.dummyList = dummyList
            binding.dummyRecyclerView.apply {
                adapter = dummyAdapter
                layoutManager =
                    GridLayoutManager(
                        this@MainActivity,
                        4, RecyclerView.HORIZONTAL,
                        false
                    )
                this.doOnLayout {/*Executes code, once Rv is laid out*/
                    dummyAdapter.setItemClickListener {
                        convertToBitmap(it.image)
                    }
                }
            }
        }
    }

    private fun convertToBitmap(image: String) = scope.launch(Dispatchers.IO) {
        glide.asBitmap().load(image).listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let { bitmap ->
                    createPaletteAsync(bitmap)
                }
                return false
            }
        }).submit()
    }

    private fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val lightVibrant = palette?.lightVibrantSwatch
            val vibrant = palette?.vibrantSwatch
            val darkVibrant = palette?.darkVibrantSwatch
            val lightMuted = palette?.lightMutedSwatch
            val muted = palette?.mutedSwatch
            val darkMuted = palette?.darkMutedSwatch
            binding.apply {
                if (vibrant != null) {
                    window.statusBarColor = vibrant.rgb
                    mToolbar.setBackgroundColor(vibrant.rgb)
                    mToolbar.setTitleTextColor(vibrant.titleTextColor)
                    //StuffUtil.createToast(this@MainActivity, "Vibrant Swatch")
                } else if (darkMuted != null) {
                    window.statusBarColor = darkMuted.rgb
                    mToolbar.setBackgroundColor(darkMuted.rgb)
                    mToolbar.setTitleTextColor(darkMuted.titleTextColor)
                    //StuffUtil.createToast(this@MainActivity, "Muted Swatch")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_test -> {
                startActivity(Intent(this, SecondActivity::class.java))
                true
            }
            else -> true
        }
    }
}