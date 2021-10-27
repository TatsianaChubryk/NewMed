package com.example.newmed.fragments

import android.content.Context
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.newmed.R
import com.example.newmed.databinding.FragmentSheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlin.math.sqrt

class SheduleFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentSheduleBinding

    private var inRandomProcess = false

    private var animDraw: AnimatedVectorDrawable? = null

    private var sensorManager: SensorManager? = null

    companion object {
        private const val SHAKE_POWER = 30
    }

    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSheduleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        binding.animView.setBackgroundResource(R.drawable.virus_anim)
        animDraw = (binding.animView.background) as AnimatedVectorDrawable

        animDraw?.registerAnimationCallback(object : Animatable2.AnimationCallback(){
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)

                binding.animView.visibility = View.INVISIBLE

                inRandomProcess = false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometerSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        accelerometerSensor?.let {
            sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (!inRandomProcess) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > SHAKE_POWER) {
                inRandomProcess = true
                binding.animView.visibility = View.VISIBLE
                animDraw?.start()
                binding.animView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
}