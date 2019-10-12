package ast;

//GIF::- "gif" "create" NAME ":" PHOTO+

import java.util.ArrayList;
import java.util.List;
import LoadImageApp.java;

public class GIF extends STATEMENT{
    String name;
    List<String> photos = new ArrayList<>();
    

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("gif");
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
        /* I'm getting the name of the first image from the list of photos we'd like to make a gif out of.
        *  Then I get the name of the directory of that first photo
        *  Then I combine the name of the directory with the name of the photo to get the full path name
        *  of the first photo.
        *  Then you read the path name to get a BufferedImage object.
        */

        File firstPhotoFile = new File(photos[0]);

        

        String pathNameOfFirstPhoto = firstPhotoFile.getAbsoluteFile().getParent();
        String fullPathNameOfFirstPhoto = pathNameOfFirstPhoto + photos[0];
        BufferedImage firstImage = ImageIO.read(new File(fullPathNameOfFirstPhoto));


        // Setting the name of the output file that will become the gif
        ImageOutputStream output = 
        new FileImageOutputStream(new File
            ("C:\\Users\\HP USER\\Desktop\\CPSC410DSL\\cpsc410_ctrl_alt_elite\\gifOutput"));

        // creating the actual GifCreator object that will make the gif
        GifCreator writer = 
        new GifCreator(output, firstImage.getType(), 400, true);
        writer.writeToSequence(firstImage);

        /* Running a for loop through all the photos we'd like to make a gif of. The steps to do this
        *  are the same as the steps done to work with the first photo of the gif.
        */
        for (int k = 0; k < photos.size(); k++){
            File nextPhotoFile = new File(photos[k]);
            String pathNameOfNextPhoto = nextPhotoFile.getAbsoluteFile().getParent();
            String fullPathNameOfNextPhoto = pathNameOfNextPhoto + photos[k];
            BufferedImage secondImage = ImageIO.read(new File(fullPathNameOfNextPhoto));
            writer.writeToSequence(secondImage);
        }
        writer.close();
        output.close();
    }

}