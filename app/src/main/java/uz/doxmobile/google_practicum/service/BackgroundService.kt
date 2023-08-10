package uz.doxmobile.google_practicum.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import uz.doxmobile.google_practicum.R
import uz.doxmobile.google_practicum.model.Location
import uz.doxmobile.google_practicum.usecase.SetLocationUseCase
import javax.inject.Inject

/**
 *   Davron
 *   09.08.2023  |  среда  |  12:13
 */

@AndroidEntryPoint
class BackgroundService : Service() {

    @Inject
    lateinit var locationClient: LocationClient

    @Inject
    lateinit var setLocationUseCase: SetLocationUseCase

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        Log.i(TAG, "start: BackgroundService")
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking location...")
            .setContentText("Location service is running...")
            .setSmallIcon(R.drawable.taxi)
            .setOngoing(true)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient.getLocationUpdates(1000L)
            .catch { e -> e.printStackTrace() }
            .onEach { _location ->
                val lat = _location.latitude
                val lng = _location.longitude
                val location = Location(
                    latitude = lat,
                    longitude = lng,
                )

                val ll = LocalDateTime.now()
                val newLocationNotification = notification.setContentText(
                    "Location : ($lat, $lng)"
                )
                notificationManager.notify(1, newLocationNotification.build())
                setLocationUseCase(location)
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    companion object {
        const val TAG = "BackgroundService"
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}