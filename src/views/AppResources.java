package views;

import java.awt.Color;
import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class AppResources {
  public static final Color COLOR_SUCCESS = Color.BLUE;
  public static final Color COLOR_WARNING = Color.ORANGE;
  public static final Color COLOR_DANGER = Color.RED;

  public static final String WELCOME_MESSAGE = "Welcome to OS Management designed by Hai Le Tan";

  public static final String FILE_ATTACHMENT_ICON = "resources/file.ico";

  public static void notifyMessage(JLabel label, String message, long duration, Color type) {
    String old_message = label.getText();
    new Thread(new Runnable() {
      @Override
      public void run() {
        label.setText(message);
        label.setForeground(type);
        try {
          Thread.sleep(duration);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        label.setText(old_message);
        label.setForeground(COLOR_SUCCESS);
      }
    }).start();
  }

  public static void notifyMessage(JLabel label, String message, long duration) {
    notifyMessage(label, message, duration, COLOR_SUCCESS);
  }

  public static String getCurrentTime() {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
  }

  public static void log(DatagramPacket incoming) {
    System.out.println(AppResources.getCurrentTime() + " has requested: " + new String(incoming.getData()));
  }

  public static void log(String message) {
    System.out.println(AppResources.getCurrentTime() + " message: " + message);
  }

}
