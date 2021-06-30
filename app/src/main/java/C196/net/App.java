package C196.net;

import android.app.Application;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App  extends Application{

    public static final String CHANNEL_1_ID = "channel1_for_Course_Start";
    public static final String CHANNEL_2_ID = "channel2_for_Course_End";
    public static final String CHANNEL_3_ID = "channel3_for_Assessment_Due";


    @Override
    public void onCreate(){
        super.onCreate();

        createNotificationChannel();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                            CHANNEL_1_ID,
                            "channel1_for_Course_Start",
                            NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Start of Course");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "channel2_for_Course_End",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("End of Course");

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "channel3_for_assessment_due_date",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel3.setDescription("Assessment Due Date");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
        }
    }

}
