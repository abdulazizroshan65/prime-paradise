package com.example.prime_paradise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.prime_paradise.databinding.ActivityMainBinding

class AvatarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*if (CookieHelper(this).getUpdateState()){
            binding.updateButton.visibility = View.VISIBLE
        }

        binding.createButton.setOnClickListener {
            openWebViewPage()
        }

        binding.updateButton.setOnClickListener{
            openUpdateWebView()
        }*/
    }

    private fun openUpdateWebView() {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.IS_CREATE_NEW, false)
        startActivity(intent)
    }


    private fun openWebViewPage() {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.IS_CREATE_NEW, true)
        startActivity(intent)
    }
}