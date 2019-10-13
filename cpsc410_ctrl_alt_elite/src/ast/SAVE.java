package ast;

import mainrun.Main;

import java.util.ArrayList;
import java.util.List;

public class SAVE extends STATEMENT{
    String name;
    List<String> names = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("save");
        tokenizer.getAndCheckNext(":");

        if (tokenizer.checkToken("all")){
            name = tokenizer.getNext();
            names.add(name);
        }
        else {
            String first = tokenizer.getNext();
            names.add(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                String photo = tokenizer.getNext();
                names.add(photo);
            }
        }

    }

    @Override
    public void evaluate() {
        //File firstPhotoFile = new File(photos.get(0));


        try {
            
            String fullPathNameOfFirstPhoto = loadObject.getDir(); + "\\" + photos.get(0);
            BufferedImage firstImage = null;
            firstImage = ImageIO.read(new File(fullPathNameOfFirstPhoto));


            // Setting the name of the output file that will become the gif
            ImageOutputStream output =
                    null;

            output = new FileImageOutputStream(new File
                    ("C:\\Users\\HP USER\\Desktop\\CPSC410DSL\\cpsc410_ctrl_alt_elite\\" + name));


            // creating the actual GifCreator object that will make the gif
            GifCreator writer =
                    new GifCreator(output, firstImage.getType(), 400, true);
            writer.writeToSequence(firstImage);

            /* Running a for loop through all the photos we'd like to make a gif of. The steps to do this
             *  are the same as the steps done to work with the first photo of the gif.
             */
            for (int k = 0; k < photos.size(); k++) {
                //File nextPhotoFile = new File(photos.get(k));
                //String pathNameOfNextPhoto = nextPhotoFile.getAbsoluteFile().getParent();
                String fullPathNameOfNextPhoto = loadObject.getDir(); + "\\" + photos.get(k);
                BufferedImage secondImage = ImageIO.read(new File(fullPathNameOfNextPhoto));
                writer.writeToSequence(secondImage);
            }
            writer.close();
            output.close();
        } catch (IOException i){
            System.out.println("Gif Creation Failed");
        }
    }
    

    @Override
    public void nameCheck() {
        for (String s: names) {
            if(!Main.variables.containsKey(s)){
                System.exit(0);
            }
        }
    }
}