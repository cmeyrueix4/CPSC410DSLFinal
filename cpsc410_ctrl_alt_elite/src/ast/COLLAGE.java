package ast;

import libs.NameCheckException;
import mainrun.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class COLLAGE extends STATEMENT{
    String name;
    ArrayList<String> photos = new ArrayList<>();
    LOAD loadObject;

    public COLLAGE(STATEMENT load){
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
        }
        else {
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
        if (photos.size() != 5){
        try {
            BufferedImage img1 = ImageIO.read(new File(photos.get(0)));
            BufferedImage joinedImg = ImageIO.read(new File(photos.get(0)));
            for (int k = 1; k < photos.size() - 1; k++){
                BufferedImage img2 = ImageIO.read(new File(photos.get(k)));
                joinedImg = joinBufferedImage(img1,img2);
                img1 = joinedImg;
            }
            //BufferedImage img2=ImageIO.read(new File(args[1]));
            boolean success = ImageIO.write(joinedImg, "jpg", new File(photos.get(photos.size() - 1)));
          //  boolean success = ImageIO.write(joinedImg, "jpg", new File(filename+"joined.jpg"));
            
           // System.out.println("saved success? "+success);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        else {
            try {
            BufferedImage topLeftCornerImage = ImageIO.read(new File(photos.get(0)));
            BufferedImage topRightCornerImage = ImageIO.read(new File(photos.get(1)));
            BufferedImage topRowPortionOfCollage = 
            joinBufferedImage(topLeftCornerImage, topRightCornerImage);
   
            BufferedImage bottomLeftCornerImage = ImageIO.read(new File(photos.get(2)));
            BufferedImage bottomRightCornerImage = ImageIO.read(new File(photos.get(3)));
            BufferedImage bottomRowPortionOfCollage = 
            joinBufferedImage(bottomLeftCornerImage, bottomRightCornerImage);

            Graphics2D g = topRowPortionOfCollage.createGraphics();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            
            g.drawImage(bottomRowPortionOfCollage, (topRowPortionOfCollage.getWidth() - 
                bottomRowPortionOfCollage.getWidth()) / 2,
                (topRowPortionOfCollage.getHeight() - bottomRowPortionOfCollage.getHeight()) / 2, null);
                g.dispose();

            display(topRowPortionOfCollage);
            ImageIO.write(topRowPortionOfCollage, "jpeg", new File(photos.get(4)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }

    @Override
    public void nameCheck() {
        for (String s: photos) {
            if(!Main.variables.containsKey(s)){
                throw new NameCheckException(s);
            }
        }

        Main.variables.put(name, "");

    }

    /* This method does what the name says: it joins two images together. The only problem when joining 
    *  two images is that one image might be way bigger than the other, so the method works as follows:
    *  We come up with an offset, in this case we'll use 5. 
    *  -> We get the overall width that will be needed for the joined image by adding the width of the first
    *  image and the width of the second image with the offset.
    *  -> Then we get the taller of the two images by taking the maximum height of the two images. We then 
    *  add the offset to the maximum height to obtain the overall height that will be used.
    *  -> Create a new BufferedImage with the width and height we calculated, as well as any filter that
    *  you want to apply to the image.
    *  -> We then make a Graphics2D object and initialize it with the new BufferedImage we just made
    *  -> Initially, the BufferedImage is a blank image of the size we gave it. Then what we'll end
    *  up doing is writing both images to that output image. This writing is done using the  
    *  Graphics2D object.
    */ 
    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {

        //getting the width and height
        int offset  = 5;
        int wid = img1.getWidth()+img2.getWidth()+offset;
        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
        
        // print("first debug");
       
        //create a new buffered image that has size width and height. The third parameter can be 
        // modified to choose whatever filter you want to apply to the output image.
        // You can check out the filters in the following document:
        // https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
       
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_RGB);
        // create the Graphics2D object that will be used to write our images to the output
        Graphics2D g2 = newImage.createGraphics();
        
        //print("second debug");
       // Color oldColor = g2.getColor();
        //fill background
      //  g2.setPaint(Color.WHITE);
      //  g2.fillRect(0, 0, wid, height);
        //draw image
      //  g2.setColor(oldColor);
        
        // g2, the Graphics2D object, does the joining of the two images here. 
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
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