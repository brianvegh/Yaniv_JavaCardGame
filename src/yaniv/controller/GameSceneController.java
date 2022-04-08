package yaniv.controller;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: GameSceneController.java
// Author: Brian Vegh
// Description: FXML GUI Controller
// ***********************************************************
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import yaniv.modelClasses.Help;
import yaniv.modelClasses.Round;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static yaniv.controller.GameD.round;
import static yaniv.modelClasses.Round.getPlayer;
import static yaniv.modelClasses.Round.humanDrawAction;
import static yaniv.modelClasses.Round.setHumanCardToDraw;

public class GameSceneController implements Initializable {
    public static Button[] playerHandButtons;
    public static List<Label[]> discardLabels;
    public static List<Label[]> handLabels;
    public static ToggleButton deckTopCard;
    static ToggleButton aVoid;
    static ToggleButton discardInside;
    public static TextField actionMessageTextField;
    public static ToggleButton[] drawButtons;
    public static Node[] npcHandBoxes;
    public static Text[] npcHSizeLabels;
    public static Text scoreText;
    public static TableColumn[] tableColumns;


    @FXML
    private Text currentHandValueText;
    @FXML
    private TextField actionTextField;
    @FXML
    public Button drawButton, discardButton, callYanivButton;
    @FXML
    private ToggleButton discardInsideToggle;
    @FXML
    private ToggleButton discardOutsideToggle;
    @FXML
    private ToggleButton deckToggle;
    @FXML
    private Button h0;
    @FXML
    private Button h1;
    @FXML
    private Button h2;
    @FXML
    private Button h3;
    @FXML
    private Button h4;
    @FXML
    private Button h5;
    @FXML
    private Button h6;
    @FXML
    private Label d10;
    @FXML
    private Label d11;
    @FXML
    private Label d12;
    @FXML
    private Label d13;
    @FXML
    private Label d14;
    @FXML
    private Label d20;
    @FXML
    private Label d21;
    @FXML
    private Label d22;
    @FXML
    private Label d23;
    @FXML
    private Label d24;
    @FXML
    private Label d30;
    @FXML
    private Label d31;
    @FXML
    private Label d32;
    @FXML
    private Label d33;
    @FXML
    private Label d34;
    @FXML
    private Label d40;
    @FXML
    private Label d41;
    @FXML
    private Label d42;
    @FXML
    private Label d43;
    @FXML
    private Label d44;
    @FXML
    private Label npcH20;
    @FXML
    private Label npcH21;
    @FXML
    private Label npcH22;
    @FXML
    private Label npcH23;
    @FXML
    private Label npcH24;
    @FXML
    private Label npcH25;
    @FXML
    private Label npcH26;
    @FXML
    private Label npcH30;
    @FXML
    private Label npcH31;
    @FXML
    private Label npcH32;
    @FXML
    private Label npcH33;
    @FXML
    private Label npcH34;
    @FXML
    private Label npcH35;
    @FXML
    private Label npcH36;
    @FXML
    private Label npcH40;
    @FXML
    private Label npcH41;
    @FXML
    private Label npcH42;
    @FXML
    private Label npcH43;
    @FXML
    private Label npcH44;
    @FXML
    private Label npcH45;
    @FXML
    private Label npcH46;
    @FXML
    private TableView<?> scoreTable;
    @FXML
    private TableColumn<?, ?> stbc0;
    @FXML
    private TableColumn<?, ?> stbc1;
    @FXML
    private TableColumn<?, ?> stbc2;
    @FXML
    private TableColumn<?, ?> stbc3;
    @FXML
    private Text npc2HSizeLabel;
    @FXML
    private Text npc3HSizeLabel;
    @FXML
    private Text npc4HSizeLabel;
    @FXML
    public Font x3;
    @FXML
    public Color x4;
    @FXML
    public AnchorPane table;
    @FXML
    public MenuItem quit, newGame, help;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
               initializeNodeArrays();
    }
    private void initializeNodeArrays() {
        playerHandButtons = new Button[]{h0, h1, h2, h3, h4, h5, h6};
        Label[] playerDiscard = new Label[]{d10, d11, d12, d13, d14};
        Label[] npcD1 = new Label[]{d20, d21, d22, d23, d24};
        Label[] npcD2 = new Label[]{d30, d31, d32, d33, d34};
        Label[] npcD3 = new Label[]{d40, d41, d42, d43, d44};
        Label[] npcH1 = new Label[]{npcH20, npcH21, npcH22, npcH23, npcH24, npcH25, npcH26};
        Label[] npcH2 = new Label[]{npcH30, npcH31, npcH32, npcH33, npcH34, npcH35, npcH36};
        Label[] npcH3 = new Label[]{npcH40, npcH41, npcH42, npcH43, npcH44, npcH45, npcH46};
        discardLabels = Arrays.asList(playerDiscard, npcD1, npcD2, npcD3);
        handLabels = Arrays.asList(null, npcH1, npcH2, npcH3);
        actionMessageTextField= actionTextField;
        scoreText=currentHandValueText;
        drawButtons = new ToggleButton[] {deckToggle, discardInsideToggle, discardOutsideToggle};
        npcHSizeLabels = new Text[] {null,npc2HSizeLabel,npc3HSizeLabel,npc4HSizeLabel};
        tableColumns=new TableColumn[]{stbc0,stbc1,stbc2,stbc3};
        scoreTable.setPlaceholder(new Label());
    }
    public void callYanivButton(ActionEvent e) {
        if (Round.getHumanPlayerScore()<=11) {
            if (Round.getHumanCallYanivIsLegal()) {
                if (confirmDialog()) {
                    Round.callYaniv();
                }
            } else displayMessage("Alert!", "Call Yaniv at the beginning of your turn, before any other action.");
        }else displayMessage("Alert!", "You must have 11 points or less in your hand to call Yaniv!");
    }

    public void discardButton(ActionEvent event) {
        Round.humanDiscardAction();
        Images.deSelectAllButtons();
    }
    public void nextTurn(ActionEvent event) throws InterruptedException {
        System.out.println("nextTurnClicked");
        Round.nextTurn();
    }
    public void drawButton(ActionEvent event) throws InterruptedException {
        humanDrawAction();
        for (ToggleButton t : drawButtons){
            t.setBorder(null);
        }
        Thread.sleep(1000);
    }

    private static void handButtonClickControl(int buttonNumber){
        Images.toggleBorder(playerHandButtons[buttonNumber]);
        getPlayer(0).getHand().getCard(buttonNumber).isSelected = playerHandButtons[buttonNumber].getBorder() != null;
    }
    private static void drawToggleButtonClickControl(int buttonNumber){
        BorderStroke darkBlue = new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(6));
        Border border = new Border(darkBlue);
        switch (buttonNumber) {
            case 0:
                for (ToggleButton t: drawButtons){
                    t.setBorder(null);
                }
                drawButtons[0].setBorder(border);
                setHumanCardToDraw(0);
                break;
            case 1:
                for (ToggleButton t: drawButtons){
                    t.setBorder(null);
                }
                drawButtons[1].setBorder(border);
                setHumanCardToDraw(1);
                break;
            case 2:
                for (ToggleButton t: drawButtons){
                    t.setBorder(null);
                }
                drawButtons[2].setBorder(border);
                setHumanCardToDraw(2);
                break;
        }
        getPlayer(0).getHand().getCard(buttonNumber).isSelected = playerHandButtons[buttonNumber].getBorder() != null;
    }
    public void h0(ActionEvent event) {
        handButtonClickControl(0);
    }
    public void h1(ActionEvent event) {
        handButtonClickControl(1);
    }
    public void h2(ActionEvent event) {
        handButtonClickControl(2);
    }
    public void h3(ActionEvent event) {
        handButtonClickControl(3);
    }
    public void h4(ActionEvent event) {
        handButtonClickControl(4);
    }
    public void h5(ActionEvent event) {
        handButtonClickControl(5);
    }
    public void h6(ActionEvent event) {
        handButtonClickControl(6);
    }
    public void discardInsideToggle(ActionEvent event) {
        BorderStroke darkBlue = new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(6));
        Border border = new Border(darkBlue);
        for (ToggleButton t: drawButtons){
            t.setBorder(null);
        }
        discardInsideToggle.setBorder(border);
        round.humanCardToDraw=1;
        //drawToggleButtonClickControl(1);
    }
    public void discardOutsideToggle (ActionEvent event) {
        drawToggleButtonClickControl(2);
    }
    public void deckToggle(ActionEvent event) {
        drawToggleButtonClickControl(0);
    }

    public static void displayMessage (String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private static boolean confirmDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are You Sure???");
        alert.setHeaderText("If you don't have the lowest hand value,\nyou will lose the game!");
        alert.setContentText("Proceed?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    public static String getUserTextInput(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Text");
        dialog.setHeaderText(message);
        Optional<String> result = dialog.showAndWait();
        String userTextInput = "";
        if (result.isPresent()) {
            userTextInput = result.get();
        }
        return userTextInput;
    }
    public void quit(ActionEvent event){
        System.exit(0);
    }
    public void newGame(ActionEvent event){
    }
    public void help(ActionEvent event) throws IOException, URISyntaxException {
        Help.help();
    }






}
