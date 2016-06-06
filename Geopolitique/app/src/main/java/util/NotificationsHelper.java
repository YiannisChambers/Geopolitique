package util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import geopolitique.id11699156.com.geopolitique.HomeScreenActivity;
import geopolitique.id11699156.com.geopolitique.IssuesActivity;
import geopolitique.id11699156.com.geopolitique.PollsScreen;
import geopolitique.id11699156.com.geopolitique.R;

/**
 * Created by yiannischambers on 6/06/2016.
 */
public class NotificationsHelper {

    private static int nextID;

    private static NotificationCompat.Builder mBuilder;

    private static void sendNotification(Context context, String description, Intent intent){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle(context.getString(R.string.notifications_title))
                        .setContentText(description);

        //Ensure that all notifications are automatically cancelled once pressed
        mBuilder.setAutoCancel(true);

        Intent resultIntent = intent;
        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(HomeScreenActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Notification notif = mBuilder.build();
        notif.defaults |= Notification.DEFAULT_SOUND;
        notif.defaults |= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(nextID, notif);
        nextID += 1;
    }
    public static void sendPollsNotification(Context context) {

        sendNotification(context,
                context.getString(R.string.notifications_polls),
                new Intent(context, PollsScreen.class));

    }

    public static void sendStatisticsNotification(Context context) {
        sendNotification(context,
                context.getString(R.string.notifications_statistics),
                new Intent(context, PollsScreen.class));
    }

    public static void sendIssueNotification(Context context) {
        sendNotification(context,
                context.getString(R.string.notifications_issue),
                new Intent(context, IssuesActivity.class));
    }

}
