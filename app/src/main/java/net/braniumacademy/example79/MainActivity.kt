package net.braniumacademy.example79

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var containerLayout: View
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            sendNotification()
        } else {
            Snackbar.make(
                containerLayout,
                R.string.text_permission_denied,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        containerLayout = findViewById(R.id.container_layout)
        val btnDownload = findViewById<Button>(R.id.btn_post_notification)
        btnDownload.setOnClickListener { checkPermissions() }
        createNotificationChannel()
        createNotification()
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_DENIED &&
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        ) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            return
        }
        sendNotification()
    }

    private fun createNotification() {
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_cat)
        val largePicture = BitmapFactory.decodeResource(resources, R.drawable.cat1)
        val nullBitmap: Bitmap? = null
        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(largeIcon)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_content))
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(largePicture)
                .bigLargeIcon(nullBitmap)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .addAction(R.drawable.ic_notification, getString(R.string.text_detail), null)
            .addAction(R.drawable.ic_notification, getString(R.string.text_share), null)

    }

    @SuppressLint("MissingPermission")
    private fun sendNotification() {
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId++, notificationBuilder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                ContextCompat.getSystemService(baseContext, NotificationManager::class.java)
                        as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "lesson7.9_channel_1"
        private var notificationId = 0
    }
}