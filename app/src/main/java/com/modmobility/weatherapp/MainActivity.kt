package com.modmobility.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modmobility.weatherapp.fragment.WeatherFragment

/**
 * Main activity class which acts as container to all Fragments in application
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val ft=supportFragmentManager.beginTransaction().replace(R.id.mainContainer,
            WeatherFragment()
        )
        ft.commit()

    }
}
