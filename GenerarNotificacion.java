// Crear un identificador único para la notificación
int notificationId = 1;

// Crear un intent para abrir la pantalla de inicio del usuario
Intent homeIntent = new Intent(Intent.ACTION_MAIN);
homeIntent.addCategory(Intent.CATEGORY_HOME);
homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

// Crear un PendingIntent para la acción "Ver mi Perfil"
PendingIntent homePendingIntent = PendingIntent.getActivity(
    context,
    0,
    homeIntent,
    PendingIntent.FLAG_UPDATE_CURRENT
);

// Crear un intent para la acción "Dar Follow/Unfollow"
Intent followIntent = new Intent(context, FollowActivity.class);
followIntent.putExtra("userId", userId); // Aquí debes proporcionar el ID de usuario correspondiente
PendingIntent followPendingIntent = PendingIntent.getActivity(
    context,
    0,
    followIntent,
    PendingIntent.FLAG_UPDATE_CURRENT
);

// Crear un intent para la acción "Ver Usuario"
Intent viewUserIntent = new Intent(context, UserActivity.class);
viewUserIntent.putExtra("userId", userId); // Aquí debes proporcionar el ID de usuario correspondiente
PendingIntent viewUserPendingIntent = PendingIntent.getActivity(
    context,
    0,
    viewUserIntent,
    PendingIntent.FLAG_UPDATE_CURRENT
);

// Crear una instancia de NotificationCompat.Builder para construir la notificación
NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
    .setSmallIcon(R.drawable.notification_icon)
    .setContentTitle("Nueva interacción")
    .setContentText("Alguien raiteó tu foto")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .addAction(R.drawable.ic_home, "Ver mi Perfil", homePendingIntent)
    .addAction(R.drawable.ic_follow, "Dar Follow/Unfollow", followPendingIntent)
    .addAction(R.drawable.ic_user, "Ver Usuario", viewUserPendingIntent)
    .setAutoCancel(true);

// Obtener el servicio de notificaciones y mostrar la notificación
NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
notificationManager.notify(notificationId, builder.build());
