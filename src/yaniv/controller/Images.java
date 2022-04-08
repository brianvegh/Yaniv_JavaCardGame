package yaniv.controller;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: Images.java
// Author: Brian Vegh
// Description: GControlles GUI image and text display
// ***********************************************************
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import yaniv.modelClasses.Card;
import yaniv.modelClasses.Discard;
import yaniv.modelClasses.Hand;
import yaniv.modelClasses.Player;

import java.util.ArrayList;
import java.util.List;

import static yaniv.controller.GameD.getRound;
import static yaniv.controller.GameSceneController.*;

public class Images {

    private static void setBackground(Button b, Card c) {
        Image image= c.getImage(b.getPrefHeight(),b.getPrefWidth());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        b.setBackground(background);
    }
    private static void setBackground(ToggleButton b, Card c) {
        Image image= c.getImage(b.getPrefHeight(),b.getPrefWidth());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        b.setBackground(background);
    }
    public static void toggleBorder(Button b) {
        if (b.getBorder()==null) {
            BorderStroke darkOrange = new BorderStroke(Color.DARKORANGE, BorderStrokeStyle.SOLID, null, new BorderWidths(3));
            b.setBorder(new Border(darkOrange));
        }else {
            b.setBorder(null);
        }
    }
    public static void renderHumanHand() {
        int i =0;
        List <Card> hand = getRound().getPlayer(0).getHand().getHand();
        for (Card c: hand) {
            setVisibility(playerHandButtons[i],true);
            setBackground(playerHandButtons[i],c);
            i++;
        }
        int j;
        for (j=i;j<7;j++) {
            setVisibility(playerHandButtons[j],false);
        }
    }
    private static void renderDiscardPiles() {
        for (int l=0; l<discardLabels.size();l++){
            Label[] labels = discardLabels.get(l);
            Player player = GameD.getRound().getPlayer(l);
            Discard discard = player.getDiscard();
            Object[] discardLastDeposit = discard.whatIsLastDeposit().toArray().clone();
            int i;
            for (i=0; i<labels.length&&i<discardLastDeposit.length;i++) {
                Label currentLabel=labels[i];
                Card c= (Card) discardLastDeposit[i];
                ImageView imageView;
                imageView=(c.getImageView(currentLabel.getPrefHeight(),currentLabel.getPrefWidth()));
                currentLabel.setGraphic(imageView);
                setVisibility(currentLabel,true);
            }
            int j;
            for (j=i; j<labels.length;j++) {
                setVisibility(labels[j],false);
            }
        }
        Images.deSelectAllButtons();
    }
    public static void renderNpcHandsCardsVisible() {
        for (int l=1; l<handLabels.size();l++){
            Label[] labels = handLabels.get(l);
            Player player = getRound().getPlayer(l);
            Hand hand = player.getHand();
            int i;
            for (i=0; i<labels.length&&i<hand.size();i++) {
                Label currentLabel=labels[i];
                Card c= hand.getHand().get(i);
                ImageView imageView;
                imageView=(c.getImageView(currentLabel.getPrefHeight(),currentLabel.getPrefWidth()));
                currentLabel.setGraphic(imageView);
                setVisibility(currentLabel,true);
            }
            int j;
            for (j=i; j<labels.length;j++) {
                setVisibility(labels[j],false);
            }
            if (hand.size()>0){
                npcHSizeLabels[l].setVisible(true);
                npcHSizeLabels[l].setText(String.valueOf((hand.size())));
            }else {
                npcHSizeLabels[l].setVisible(false);
            }
        }

        Images.deSelectAllButtons();
    }
    private static void renderNpcHands() {
        Card c = getRound().getBackOfCard();
        ImageView imageView;
        int playerHandSize;
        for (int l=1; l<handLabels.size();l++){
            Label[] labels = handLabels.get(l);
            playerHandSize = getRound().getPlayer(l).getHand().size();
            int i;
            for (i=0; i<labels.length&&i<playerHandSize;i++) {
                Label currentLabel=labels[i];
                imageView=(c.getImageView(currentLabel.getPrefHeight(),currentLabel.getPrefWidth()));
                currentLabel.setGraphic(imageView);
                setVisibility(currentLabel,true);
            }
            int j;
            for (j=i; j<labels.length;j++) {
                setVisibility(labels[j],false);
            }
            if (playerHandSize>0){
                npcHSizeLabels[l].setVisible(true);
                npcHSizeLabels[l].setText(String.valueOf((playerHandSize)));
            }else {
                npcHSizeLabels[l].setVisible(false);
            }
        }
        Images.deSelectAllButtons();
    }
    private static void renderDiscardDrawOptions() {
        drawButtons[1].setVisible(true);
        drawButtons[2].setVisible(true);
        if (getRound().getPlayer(getRound().getNumberRemainingPlayers()-1).getDiscard().whatIsLastDeposit().size()>0) {
        Card[] humanPlayersDiscardDrawOptions = getRound().getHumanPlayersDiscardDrawOptions();
            if (humanPlayersDiscardDrawOptions[0] != null) {
                setBackground(drawButtons[1], humanPlayersDiscardDrawOptions[0]);
                if (humanPlayersDiscardDrawOptions[1] != null) {
                    setBackground(drawButtons[2], humanPlayersDiscardDrawOptions[1]);
                }else{
                    drawButtons[2].setVisible(false);
                }

                }
            }else {
            drawButtons[1].setVisible(false);
            drawButtons[2].setVisible(false);
            }
        setBackground(drawButtons[0], getRound().getBackOfCard());
    }
    private static void setVisibility(Node guiElement, boolean visibility) {
       guiElement.setVisible(visibility);
       guiElement.setManaged(visibility);
    }

    public static void setActionText(String text)  {
        actionMessageTextField.setText(text);
    }
    public static String getActionText() {
        return actionMessageTextField.getText();
    }
    private static void setScoreText() {
        scoreText.setVisible(true);
        if (getRound().getHumanPlayerScore()!=0) {
            scoreText.setText("Current Points in Hand: " + getRound().getHumanPlayerScore());
        }else {
            scoreText.setVisible(false);
        }
    }

    public static void deSelectAllButtons() {
        for (int i = 0; i< getRound().getPlayer(0).getHand().size(); i++) {
            getRound().getPlayer(0).getHand().getCard(i).isSelected = false;
            playerHandButtons[i].setBorder(null);
        }
    }

    public static void renderScoreboard(ArrayList[] playerScores) {
        for (int i=0;i<tableColumns.length;i++){
            for (int j=0;j<playerScores[i].size();j++){
                //tableColumns[i].setCellFactory();
            }
        }
    }

    public static void renderNodes() {
        setScoreText();
        renderHumanHand();
        renderDiscardPiles();
        renderDiscardDrawOptions();
        //renderNpcHandsCardsVisible();
        renderNpcHands();
    }
}//class