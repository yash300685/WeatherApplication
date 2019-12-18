package com.modmobility.weatherapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.modmobility.weatherapp.R
import kotlinx.android.synthetic.main.weather_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Just a plain Simple UI Fragment displaying Weather info for current location
 */

class WeatherFragment : Fragment() {

    private  val viewModel: WeatherViewModel by viewModel()


    companion object {
        fun newInstance() = WeatherFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        initViewModel()
    }

     private fun initViewModel() {



             // Observe catsList and update our adapter if we get new one from API
             viewModel.tempData.observe(this, Observer { temperature ->
                 if(temperature!=null) {
                     val tempInCelcius:Double = temperature.toDouble() - 273
                     weatherView.text= "$tempInCelcius C"
                     viewModel.tempData.postValue(null)
                     viewModel.tempData.removeObservers(this)

                 }


             })
             // Observe showLoading value and display or hide our activity's progressBar
             viewModel.loading.observe(this, Observer { showLoading ->
                 mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
             })
             // Observe showError value and display the error message as a Toast
             viewModel.error.observe(this, Observer { showError ->
                 if(showError!=null) {
                     Toast.makeText(activity, showError, Toast.LENGTH_SHORT).show()
                     viewModel.error.postValue(null)

                 }
             })
             // The observers are set, we can now ask API to load a data list
             viewModel.getWeather("Roselle,Illinois")
         }


}
