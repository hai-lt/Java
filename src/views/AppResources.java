package views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;

public class AppResources {
  public static final Color COLOR_SUCCESS = Color.BLUE;
  public static final Color COLOR_WARNING = Color.ORANGE;
  public static final Color COLOR_DANGER = Color.RED;

  public static final String WELCOME_MESSAGE = "Welcome to OS Management designed by Hai Le Tan";

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

}
