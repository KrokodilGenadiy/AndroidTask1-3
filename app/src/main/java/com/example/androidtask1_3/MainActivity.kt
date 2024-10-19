package com.example.androidtask1_3

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.androidtask1_3.databinding.ActivityMainBinding
import com.example.androidtask1_3.fragments.Fragment1
import com.example.androidtask1_3.fragments.Fragment2
import com.example.androidtask1_3.fragments.Fragment3
import com.example.androidtask1_3.workmanager.ChargingWorker

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startWorkManager()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permission = "android.permission.POST_NOTIFICATIONS"
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startWorkManager()
        } else {
            requestPermissionLauncher.launch(permission)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(Fragment1(), "1")
    }

    private fun startWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val chargingWorkRequest = OneTimeWorkRequest.Builder(ChargingWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(chargingWorkRequest)
    }

    fun navigateForward(tag: String) {
        when (tag) {
            "1" -> changeFragment(Fragment2(), "2")
            "2" -> changeFragment(Fragment3(), "3")
            "3" -> changeFragment(Fragment1(), "1")
        }
    }

    fun navigateBack(tag: String) {
        when (tag) {
            "1" -> changeFragment(Fragment3(), "3")
            "2" -> changeFragment(Fragment1(), "1")
            "3" -> changeFragment(Fragment2(), "2")
        }
    }

    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        val existingFragment = checkFragmentExistence(tag)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, existingFragment ?: fragment, tag)
            .commit()
    }
}

