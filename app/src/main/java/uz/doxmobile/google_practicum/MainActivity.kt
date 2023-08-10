package uz.doxmobile.google_practicum

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import uz.doxmobile.google_practicum.databinding.ActivityMainBinding
import uz.doxmobile.google_practicum.service.BackgroundService

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var mMap: GoogleMap? = null
    private var rotate = 0f
    private lateinit var marker: MarkerOptions
    private var position = LatLng(41.353620, 69.287732)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!hasLocationPermission()) {
            requestLocationPermission()
        } else {
            startServiceFunc()
        }
        marker = MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi))

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        initListeners()
        observeLocation()
    }

    private fun startServiceFunc() {
        startService(
            Intent(applicationContext, BackgroundService::class.java).apply {
                action = BackgroundService.ACTION_START
            }
        )
    }

    private fun observeLocation() {
        lifecycleScope.launch {
            viewModel.location.filterNotNull().collectLatest {
                position = LatLng(it.latitude, it.longitude)
                addMarker(position)
            }
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            0
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startServiceFunc()
        }
    }

    private fun initListeners() {
        binding.ivZoomIn.setOnClickListener {
            mMap?.animateCamera(CameraUpdateFactory.zoomIn())
        }
        binding.ivZoomOut.setOnClickListener {
            mMap?.animateCamera(CameraUpdateFactory.zoomOut())
        }

        binding.ivGps.setOnClickListener {
            mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
        }
    }

    private fun addMarker(position: LatLng) {
        mMap?.clear()
        mMap?.addMarker(
            marker
                .position(position)
                .title("Me")
                .anchor(0.5f, 0.5f)
                .rotation(rotate)
                .flat(true)
        )
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17f))
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        addMarker(position)
        mMap?.setPadding(0, 150, 0, 0)
    }

    override fun onDestroy() {
        startService(
            Intent(applicationContext, BackgroundService::class.java).apply {
                action = BackgroundService.ACTION_STOP
            }
        )
        super.onDestroy()
    }
}