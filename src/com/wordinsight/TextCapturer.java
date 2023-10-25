package com.wordinsight;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextCapturer {
    private int width;
    private int height;
    private int margin;
    private Font mainFont;
    private Font titleFont;
    private Font subtitleFont;
    private Font bodyFont;

    public TextCapturer() {
        width = 800;
        height = 800;
        margin = 60;
        try {
            mainFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Times New Roman.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        titleFont = mainFont.deriveFont(Font.BOLD).deriveFont(84.0f);
        subtitleFont = mainFont.deriveFont(Font.ITALIC).deriveFont(42.0f);
        bodyFont = mainFont.deriveFont(Font.PLAIN).deriveFont(32.0f);
    }

    public void writeNewImage(String title, String subtitle, String body) {
        title = (title == null) ? "<blank>" : title;
        subtitle = (subtitle == null) ? "<blank>" : subtitle;
        body = (body == null) ? "<blank>" : body;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.setFont(titleFont);
        g2d.drawString(title, margin, height / 6);
        g2d.setFont(subtitleFont);
        g2d.drawString(subtitle, margin, height / 4);
        g2d.setFont(bodyFont);
        // g2d.drawString(body, margin, height / 3);
        drawTextWrap(body, g2d, height / 3);

        File outputFile = new File(String.format("output/word-insight_%s.png", title));
        try {
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
                System.out.println("Created \"output\" directory");
            }
            ImageIO.write(img, "png", outputFile);
            System.out.println("Saved image to " + outputFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNewImage(WordData wordData) {
        writeNewImage(
                wordData.getWord(),
                wordData.getPhonetic() + " - " + wordData.getPartOfSpeech(),
                wordData.getDefinition());
    }

    private void drawTextWrap(String text, Graphics2D g2d, int startY) {
        FontMetrics fm = g2d.getFontMetrics();
        int lineHeight = fm.getHeight();
        String textToDraw = text;
        String[] arr = textToDraw.split(" ");
        int nIndex = 0;
        while (nIndex < arr.length) {
            String line = arr[nIndex++];
            while ((nIndex < arr.length) && (fm.stringWidth(line + " " + arr[nIndex]) < width - (margin * 2))) {
                line = line + " " + arr[nIndex];
                nIndex++;
            }
            g2d.drawString(line, margin, startY);
            startY = startY + lineHeight;
        }
    }
}
