package yaniv.modelClasses;
// ************************************************************
// Title: yaniv.modelClasses.Card
// File: yaniv.modelClasses.Card.java
// Author: Brian Vegh
// Description: Class file for yaniv.modelClasses.Card objects
// ************************************************************
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.File;

public class Card {

    private static final int SPADES =0;
    private static final int HEARTS = 1;
    private static final int DIAMONDS = 2;
    private static final int CLUBS = 3;
    public static final int JOKER = 4;
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    private final String FILE_FILTER_STRING = ".png";
    private final int suit;
    private final int value;
    private final int pointValue;
    private static int jokerCount = 0;
    private static int jokersCreated = 0;
    public boolean isSelected;
    private String imageFilepath;
    private static int jokersImaged;
    private Image image;
    private BufferedImage bufferedImage;

    public Card(String backOfCardImageFilepath){
        //***Override Constructor specific for back of card object
        value = -1;
        pointValue = -1;
        suit=-1;
        isSelected = false;
        setImageFilepath(backOfCardImageFilepath);
        setImage();
    }

    public Card() { //Joker
        suit = JOKER;
        value = 1;
        pointValue = 0;
        isSelected=false;
        setImageFilepath();
        setImage();
    }
    public Card(int theValue, int theSuit) {
        if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS &&
                theSuit != CLUBS && theSuit != JOKER)
            throw new IllegalArgumentException("Illegal playing card suit");
        if (theSuit != JOKER && (theValue < 1 || theValue > 13))
            throw new IllegalArgumentException("Illegal playing card value");
        value = theValue;
        suit = theSuit;
        isSelected=false;
        if (theSuit != JOKER && theValue < 10)
            pointValue = theValue;
        else pointValue = 10;
        setImageFilepath();
        setImage();
    }
    public static void newDeckHasJokers() {
        jokerCount+=2;
    }

    public int getSuit()  {
        return suit;
    }

    public int getValue()  {
        return value;
    }

    public int getPointValue()  {
        return pointValue;
    }

    private String getSuitAsString()  {
        switch (suit) {
            case SPADES:
                return "Spades";
            case HEARTS:
                return "Hearts";
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
            default:
                return "Joker";
        }//
    }
    private String getSuitAsStringBasic()  {
        switch (suit) {
            case SPADES:
                return "S";
            case HEARTS:
                return "H";
            case DIAMONDS:
                return "D";
            case CLUBS:
                return "C";
            default:
                return "Joker";
        }//
    }//methodEnd
    private String getValueAsString()  {
        if (suit == JOKER)
            return "" + value;
        else {
            switch ( value ) {
                case 1:   return "Ace";
                case 2:   return "2";
                case 3:   return "3";
                case 4:   return "4";
                case 5:   return "5";
                case 6:   return "6";
                case 7:   return "7";
                case 8:   return "8";
                case 9:   return "9";
                case 10:  return "10";
                case 11:  return "Jack";
                case 12:  return "Queen";
                default:  return "King";
            }
        }
    }
    private String getValueAsStringBasic()  {
        if (suit == JOKER)
            return "" + value;
        else {
            switch ( value ) {
                case 1:   return "A";
                case 2:   return "2";
                case 3:   return "3";
                case 4:   return "4";
                case 5:   return "5";
                case 6:   return "6";
                case 7:   return "7";
                case 8:   return "8";
                case 9:   return "9";
                case 10:  return "10";
                case 11:  return "J";
                case 12:  return "Q";
                default:  return "K";
            }
        }
    }
    public String toString() {
        if (suit == JOKER) {
            if (value == 1)
                return "Joker";
            else
                return "Joker #" + value;
        }
        else
            return getValueAsString() + " of " + getSuitAsString();
    }
    public String toStringBasic()  {
        if (suit == JOKER) {
            if (value == 1)
                return "Joker";
            else
                return "Joker #" + value;
        }
        else
            return getValueAsStringBasic() + "-" + getSuitAsStringBasic();
    }
    private void setImageFilepath() {
        String relativePath="images/";
        String filename;
        String path;
        if (suit == JOKER && jokerCount >= 0) {
            filename = "jokers/Joker";
            filename += (jokerCount + 1 - jokersCreated);
            jokersCreated++;
        } else {
            filename = getValueAsStringBasic();
            filename += getSuitAsStringBasic();
        }
        path = relativePath +filename+FILE_FILTER_STRING;
        imageFilepath = path;
       // System.out.println(imageFilepath);
    }
    private void setImageFilepath(String backOfCardFilename) {
        String relativePath ="images/back of card images/";
        imageFilepath = relativePath +backOfCardFilename +FILE_FILTER_STRING;
       // System.out.println(imageFilepath);
    }
     public Image getImage(double height, double width) {
         return new Image(imageFilepath,width,height,false,false);
    }
//    public String getImageFilepath() {
//        return imageFilepath;
//    }
    public ImageView getImageView(double height, double width) {
        ImageView scaledImage = new ImageView(image);
        scaledImage.setFitHeight(height);
        scaledImage.setFitWidth(width);
        return scaledImage;
    }
    private void setImage() {
        image = new Image(imageFilepath);
        File imageFile = new File(imageFilepath);
    }
    public static String getSuitsAsString(int i) {
        switch (i) {
            case 0:
                return "Spades";
            case 1:
                return "Hearts";
            case 2:
                return "Diamonds";
            case 3:
                return "Clubs";
            default:
                return "Joker";
        }
    }

}


