package views.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHandle {
  private String path;

  public ImageHandle(String path) {
    this.path = path;
  }
  
  public ImageIcon getIcon() {
    BufferedImage myPicture;
    try {
      myPicture = ImageIO.read(new File(path));
      return new ImageIcon(myPicture);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}
