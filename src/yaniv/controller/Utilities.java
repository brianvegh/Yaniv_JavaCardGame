package yaniv.controller;// ************************************************************
// Title: Yanive yaniv.controller.Utilities
// File: yaniv.controller.Utilities.java
// Author: Brian Vegh
// Description: Collection of yaniv.controller.Utilities for project
// ************************************************************


class Utilities {

   // public static ArrayList printHand (ArrayList hand) {

    //}




    public static void helpSplash() {
        //Display help section of game
    }
    public  static void about() {
        splashDescription();
        splashCredits();
    }
    private static void splashDescription() {
        GameSceneController.displayMessage("Splash!", "Title: Yaniv yaniv.modelClasses.Card yaniv.controller.Game\n"+
                        "My Name: Brian Vegh\n"+
                        "Course Info: CIS201, Fall 2018\n"+
                        "*********************************************************\n"+
                        "My project will be a GUI based java application that allows a single \n"+
                        "player to play a card game against 3 Computer players. Yaniv, also \n"+
                        "known throughout the middle and near east as the traveler’s card game\n"+
                        "is a complex game which is similar to rummy. Players participate in \n"+
                        "rounds; the goal of each is to hold the lowest number of points before \n"+
                        "declaring “Yaniv!” The player with the least points receives a score of \n"+
                        "zero for that round; all other players receive the cumulative value of \n"+
                        "their hand. As players exceed a specified point value (usually 200), they \n"+
                        "are eliminated. The last player remaining is the victor."); //This line is
           }//splash

    public static void splashCredits() {

        GameSceneController.displayMessage("Thanks","Thanks to Kari Laitinen @Github for the colection of card face\n" +
                "images (not including Jokers. Thanks http://math.hws.edu for help with design of\n" +
                "Card, Hand and Deck classes. All other algorithm design is original, by Brian Vegh\n");
    }//splash


}



