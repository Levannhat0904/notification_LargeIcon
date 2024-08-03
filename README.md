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

<img width="529" alt="image" src="https://github.com/user-attachments/assets/3d3abc02-9836-4dbc-9778-841d8c4ba12d">


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

kết quả 
<img width="455" alt="image" src="https://github.com/user-attachments/assets/4fd21189-5a4f-4f47-abcb-6fad7220efb8">

Để hiển thị một đoạn văn bản dài khi mở rộng thông báo, ta thiết lập giá trị NotificationCompat.BigTextStyle trong phương thức setStyle():

// kotlin:

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_mail)
                .setContentTitle(emailObject.getSenderName())
                .setContentText(emailObject.getSubject())
                .setLargeIcon(emailObject.getSenderAvatar())
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(emailObject.getSubjectAndSnippet()))
                .build()
Ví dụ trong video:

// kotlin:

        private fun createNotification() {
        
            val larIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_studio)
            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(larIcon)
                .setContentTitle(getString(R.string.notification_title))
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.long_text))
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false)
                .addAction(R.drawable.ic_notification, getString(R.string.text_reply), null)
                .addAction(R.drawable.ic_notification, getString(R.string.text_archive), null)
        }
Kết quả:

<img width="409" alt="image" src="https://github.com/user-attachments/assets/0e863287-56f2-441a-b243-d301846c0606">

Ta có thể tạo một thông báo dạng inbox và sử dụng NotificationCompat.InboxStyle truyền vào phương thức setStyle() nếu ta muốn tạo các dòng tóm tắt ngắn gọn từ các thông báo được gửi tới.

Ví dụ tóm tắt tin nhắn, email… làm vậy cho phép ta thêm nhiều mẩu nội dung text, mỗi mẩu một dòng vào thông báo thay vì chi tiết thông báo dài như phần mà ta vừa tìm hiểu ở trên.
Để tạo một dòng mới, gọi addLine(), ta có thể gọi tối đa 6 lần. Nếu ta gọi nhiều hơn con số này, chỉ 6 lời gọi đầu tiên được hiển thị lên.
/ kotlin:

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_mail)
                .setContentTitle("5 New mails from " + sender.toString())
                .setContentText(subject)
                .setLargeIcon(aBitmap)
                .setStyle(NotificationCompat.InboxStyle()
                        .addLine(messageSnippet1)
                        .addLine(messageSnippet2))
                .build()
                
Code mẫu trong video:

        private fun createNotification() {
            val larIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_studio)
            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(larIcon)
                .setContentTitle(getString(R.string.notification_title))
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine(getString(R.string.summary_text1))
                        .addLine(getString(R.string.summary_text2))
                        .addLine(getString(R.string.summary_text3))
                        .addLine(getString(R.string.summary_text4))
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false)
                .addAction(R.drawable.ic_notification, getString(R.string.text_reply), null)
                .addAction(R.drawable.ic_notification, getString(R.string.text_archive), null)
        }

<img width="458" alt="image" src="https://github.com/user-attachments/assets/882eaf83-ddcf-4916-b69e-6d7e3242eae8">

Hiển thị một cuộc hội thoại trong notification
Sử dụng giá trị NotificationCompat.MessagingStyle để hiển thị các tin nhắn theo thứ tự giữa nhiều người. Đây là ý tưởng cho ứng dụng nhắn tin vì nó cung cấp một bố cục nhất quán cho mỗi tin nhắn bằng cách xử lý tên người gửi tới và văn bản tin nhắn độc lập nhau, mỗi tin nhắn có thể dài trên nhiều dòng.
Để thêm một tin nhắn mới vào luồng tin nhắn, ta gọi addMessage(), truyền văn bản của tin nhắn, thời gian nhận, tên người gửi vào đó.
Ta cũng có thể truyền vào thông tin như một đối tượng NotificationCompatMessagingStyle.Message. Ví dụ:

// kotlin:
        
        var message1 = NotificationCompat.MessagingStyle.Message(messages[0].getText(),
                messages[0].getTime(),
                messages[0].getSender())
        var message2 = NotificationCompat.MessagingStyle.Message(messages[1].getText(),
                messages[1].getTime(),
                messages[1].getSender())
        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_message)
                .setStyle(NotificationCompat.MessagingStyle(resources.getString(R.string.reply_name))
                        .addMessage(message1)
                        .addMessage(message2))
                .build()

<img width="667" alt="image" src="https://github.com/user-attachments/assets/756f449c-1c74-4792-a379-2341f85f44bd">

Có một điều cần lưu ý rằng, khi sử dụng NotificationCompat.MessagingStyle thì các giá trị truyền vào phương thức setContentTitle() và setContentText() bị bỏ qua.
Tùy chọn, ta có thể gọi setConversationTitle() để thêm một tiêu đề xuất hiện phía trên cuộc hội thoại.
Đây có thể là một cái tên người dùng tự đặt hoặc tên của một nhóm hoặc nếu không có tên cụ thể, ta liệt kê các thành viên tham gia cuộc hội thoại đó.
Không thiết lập một tiêu đề cho cuộc hội thoại 1-1 giữa hai người vì hệ thống sử dụng sự tồn tại của trường này cho việc gợi ý chỉ dẫn rằng cuộc hội thoại đó là một cuộc hội thoại nhóm.
Style đang nói tới chỉ có hiệu lực với các thiết bị chạy Android 7.0 trở lên. Khi sử dụng thư viện tương thích(NotificationCompat) như minh họa ở trên, các notification với MessagingStyle sẽ tự động thu gọn lại vào một notification có style được hỗ trợ mở rộng.
Khi xây dựng một ứng dụng với notification loại này cho một cuộc hội thoại, ta nên bổ sung tính năng phản hồi trực tiếp vào đó.

Tạo một notification với các nút điều khiển đa phương tiện
Nếu muốn hiển thị các nút điều khiển đa phương tiện để kiểm soát và lưu vết thông tin, ta sử dụng NotificationCompat.MediaStyle truyền vào phương thức setStyle().
Gọi addAction() tối đa 5 lần để thêm 5 nút biểu tượng độc lập vào trình điều khiển, sau đó gọi setLargeIcon() để thiết lập ảnh bìa album.
Không giống các style khác của notification, MediaStyle cho phép ta sửa đổi nội dung khung nhìn của kích thước thu gọn bằng cách chỉ định 3 nút hành động nên xuất hiện trong khung nhìn thu gọn.
Để làm điều trên, cung cấp chỉ số nút hành động cho setShowActionIncompactView().
Nếu notification thể hiện một phiên đa phương tiện đang được kích hoạt, ta gắn thêm MediaSession.Token vào notification sử dụng phương thức setMediaSession(). Android sau đó sẽ xác minh điều này như là một notification biểu diễn một phiên đa phương tiện đang hoạt động và phản hồi tương ứng bằng cách hiển thị một ảnh bìa album trên màn hình khóa chẳng hạn.

Code mẫu:

// kotlin:
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat as MediaNotificationCompat

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
                // hiển thị các nút điều khiển trên màn hình khóa ngay cả khi người dùng ẩn nội dung nhạy cảm
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_stat_player)
                // thêm các nút điều khiển gọi tới các intent trong media service
                .addAction(R.drawable.ic_prev, "Previous", prevPendingIntent) // #0
                .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent) // #1
                .addAction(R.drawable.ic_next, "Next", nextPendingIntent) // #2
                // Sử dụng MediaStyle template
                .setStyle(MediaNotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button \*/)
                        .setMediaSession(mediaSession.getSessionToken()))
                .setContentTitle("Wonderful music")
                .setContentText("My Awesome Band")
                .setLargeIcon(albumArtBitmap)
                .build()

<img width="656" alt="image" src="https://github.com/user-attachments/assets/85f4ec48-c0b1-42c0-96d5-7bf06726a6af">

Code mẫu trong video:

// kotlin:

        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_music)
            .setContentTitle("Wonderful music")
            .setContentText("My Awesome Band")
            .addAction(R.drawable.ic_like, "Like", null)
            .addAction(R.drawable.ic_previous, "Previous", null)
            .addAction(R.drawable.ic_pause, "Pause", null)
            .addAction(R.drawable.ic_next, "Next", null)
            .addAction(R.drawable.ic_repeat, "Repeat", null)
            .setStyle(
                MediaNotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1, 2, 3)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    
Kết quả:

<img width="440" alt="image" src="https://github.com/user-attachments/assets/7e2edd09-65dc-4c55-b63f-268282f9ebf0">

