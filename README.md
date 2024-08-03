# notification_LargeIcon
Một notification đơn giản thường chứa một tiêu đền một dòng văn bản và một hoặc nhiều hành động người dùng có thể thực hiện để phản hồi.

Để cung cấp nhiều thông tin hơn từ notification, ta có thể tạo một notification lớn hơn, có thể mở rộng bằng cách áp dụng một vài mẫu notification như mô tả trong bài học này.

Ta cần tạo các thông tin cơ bản trong notification như trong bài học 7.7, sau đó gọi setStyle() với một đối tượng style và cung cấp thông tin thương ứng cho từng template ta muốn sử dụng.

Để thêm 1 ảnh vào notification, truyền một đối tượng NotificationCompat.BigPictureStyle vào setStyle():

// kotlin:

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_post)
                .setContentTitle(imageTitle)
                .setContentText(imageDescription)
                .setStyle(NotificationCompat.BigPictureStyle()
                        .bigPicture(myBitmap))
                .build()
Nếu ta muốn ảnh xuất hiện như biểu tượng nhỏ khi notification được thu gọn lại, ta gọi setLargeIcon() và truyền vào đó ảnh nhưng cũng nhớ gọi BigPictureStyle.bigLargeIcon(null) để biểu tượng ảnh lớn kia biến mất khi notification được mở rộng ra.

// kotlin:


        var notification = NotificationCompat.Builder(context, CHANNEL_ID)

                .setSmallIcon(R.drawable.new_post)
                
                .setContentTitle(imageTitle)
                
                .setContentText(imageDescription)
                
                .setLargeIcon(myBitmap)
                
                .setStyle(NotificationCompat.BigPictureStyle()
                
                        .bigPicture(myBitmap)
                        
                        .bigLargeIcon(null))
                        
                .build()
        
        
Kết quả minh họa khi notification được thu gọn và mở rộng:


Code mẫu:

// kotlin:

        private fun createNotification() {

        val larIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_cat)
        val largePicture = BitmapFactory.decodeResource(resources, R.drawable.cat1)
        val nullBitmap: Bitmap? = null
        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(larIcon)
            .setContentTitle(getString(R.string.notification_title))
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(largePicture)
                    .bigLargeIcon(nullBitmap)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .addAction(R.drawable.ic_notification, getString(R.string.text_detail), null)
            .addAction(R.drawable.ic_notification, getString(R.string.text_share), null)
    }

    @SuppressLint("MissingPermission")
    private fun sendNotification() {
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId++, notificationBuilder.build())
        }
    }
