package com.example.water_app.main

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navMain.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                // 프래그먼트 이동
                R.id.first -> {
                    val fragment1 = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment1).commit()
                }
                R.id.second -> {
                    val fragment2 = DonationFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment2).commit()
                }
                R.id.third -> {
                    val fragment3 = MapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment3).commit()
                }
                R.id.fourth -> {
                    val fragment4 = StoreFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment4).commit()
                }
                R.id.fifth -> {
                    val fragment5 = MyPageFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment5).commit()
                }
            }
            true
        }
            selectedItemId = R.id.first
        }

        // HashKey
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            for (signature in packageInfo.signingInfo.apkContentsSigners) {
                try {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("getKeyHash", "key hash: ${Base64.encodeToString(md.digest(), Base64.NO_WRAP)}")
                } catch (e: NoSuchAlgorithmException) {
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature=$signature", e)
                }
            }
        }
    }
}