package com.ad.common.tools;

import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * 生成雪碧图
 */
public class BuildSprite {

    public static File[] getFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            return files;
        } else {
            return null;
        }
    }

    public static void append2File(String file, String content) {
        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "E:\\CoderYoung\\Desktop\\data";
        File[] files = getFiles(path + "\\image");
        if (files != null && files.length > 0) {
            int colCount = 20;//每行图片个数
            int rowCount = (int) Math.ceil(files.length * 1.0 / colCount);  //行数
            int imgWidth = 24;//图片宽度
            int imgHeight = 16;//图片高度
            BufferedImage bufferedImage = new BufferedImage(imgWidth * colCount, imgHeight * rowCount, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
            bufferedImage = graphics2D.getDeviceConfiguration().createCompatibleImage(imgWidth * colCount, imgHeight * rowCount, Transparency.TRANSLUCENT);
            graphics2D.dispose();
            graphics2D = bufferedImage.createGraphics();
            JSONObject jsonObject = new JSONObject();
            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                int x;
                int y = imgHeight * rowIndex;
                for (int colIndex = 0; colIndex < colCount; colIndex++) {
                    int imgIndex = rowIndex * colCount + colIndex;
                    if (imgIndex < files.length) {
                        File file = files[imgIndex];
                        x = imgWidth * colIndex;
                        if (!file.isDirectory()) {
                            String name = file.getName();
                            name = name.substring(0, name.lastIndexOf("."));
                            BufferedImage bi = ImageIO.read(file);

                            int h = bi.getHeight();
                            int w = bi.getWidth();
                            graphics2D.drawImage(bi, x, y, w, h, null);

                            JSONObject js = new JSONObject();
                            js.put("x", x);
                            js.put("y", y);
                            js.put("width", w);
                            js.put("height", h);
                            jsonObject.put(name, js);
                        }
                    }
                }
            }
            graphics2D.dispose();
            FileOutputStream out = new FileOutputStream(path + "\\merge.png");
            ImageIO.write(bufferedImage, "PNG", out);
            append2File(path + "\\merge.json", jsonObject.toString());
        }
    }

}
