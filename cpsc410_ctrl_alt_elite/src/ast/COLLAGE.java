package ast;

import libs.NameCheckException;
import mainrun.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class COLLAGE extends STATEMENT {
    public static final int WATERMARK_HEIGHT = 24;
    public static final int BG_COLOR = 0x424242;
    public static final int OFFSET = 10;
    String name;
    ArrayList<String> photos = new ArrayList<>();
    LOAD loadObject;

    public COLLAGE(STATEMENT load) {
        this.loadObject = (LOAD) load;
    }


    @Override
    public void parse() {
        tokenizer.getAndCheckNext("collage");
        tokenizer.getAndCheckNext("create");
        name = tokenizer.getNext();

        tokenizer.getAndCheckNext(":");

        if (tokenizer.checkToken("all")) {
            String all = tokenizer.getNext();
            photos.add(all);
        } else {
            String first = tokenizer.getNext();
            photos.add(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                String photo = tokenizer.getNext();
                photos.add(photo);
            }
        }

    }

    @Override
    public void evaluate() {
        BufferedImage[] imgs = new BufferedImage[photos.size()];
        int i = 0;
        for (String photoName : photos) {
            imgs[i] = ((BufferedImage) Main.variables.get(photoName));
            i++;
        }

        BufferedImage collage = makeCollage(imgs);
        collage = addWatermark(collage);
        Main.variables.put(name, collage);
    }


    @Override
    public void nameCheck() {
        for (String s : photos) {
            if (!Main.variables.containsKey(s)) {
                throw new NameCheckException(s);
            }
        }

        Main.variables.put(name, "");
    }

    private static BufferedImage addWatermark(BufferedImage collage) {
        int width = collage.getWidth();
        int height = collage.getHeight() + WATERMARK_HEIGHT;

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = newImage.createGraphics();
        g2.setPaint(new Color(BG_COLOR));
        g2.drawRect(0, 0, width, height);

        g2.drawImage(collage, 0, 0, null);
        g2.setPaint(Color.WHITE);
        g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g2.drawString("Made with Gif-T Shop", OFFSET, collage.getHeight() + OFFSET);

        return newImage;
    }

    private static BufferedImage makeCollage(BufferedImage... images) {
        int width = 0;
        int height = 0;
        int imgheight = 0, imgwidth = 0;
        for (BufferedImage img : images) {
            imgwidth = Math.max(img.getWidth(), imgwidth);
            imgheight = Math.max(img.getHeight(), imgheight);
        }
        int rows = (int) Math.floor((Math.sqrt(images.length)));
        int imagesPerRow = images.length / rows;

        if (images.length == 3 || images.length == 5 || images.length == 7) {
            rows = 1;
            imagesPerRow = images.length;
        }

        rows = images.length > imagesPerRow * rows ? rows + 1 : rows;
        height = rows > 1 ? (rows * (imgheight + OFFSET)) + OFFSET : imgheight + (2 * OFFSET);
        width = OFFSET + ((imgwidth + OFFSET) * imagesPerRow);

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        // create the Graphics2D object that will be used to write our images to the output
        Graphics2D g2 = newImage.createGraphics();
        g2.setPaint(new Color(BG_COLOR));
        g2.drawRect(0, 0, width, height);


        int onRow = 0;

        int x = OFFSET;
        int y = OFFSET;
        for (int i = 0; i < images.length; i++) {
            g2.drawImage(images[i], x, y, null);
            x += images[i].getWidth() + OFFSET;

            onRow++;
            if (onRow >= imagesPerRow) {
                x = OFFSET;
                y += imgheight + OFFSET;
                onRow = 0;
            }
        }

        g2.dispose();
        return newImage;
    }

    public static void display(BufferedImage image) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JLabel(new ImageIcon(image)));
        f.pack();
        f.setVisible(true);
    }
}