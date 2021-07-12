import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
 *ConfirmBox <br>
 *La classe ConfirmBox consente
 *la visualizzazione di una finestra
 *con testo e 2 bottoni (si e no)
 *@author Davide Chiapperini, Simone Andriani
 */

public class OspedaleBox {
    private static boolean answer;
    private static Button yesButton;
    private static Button noButton;
    private static Button defButton;
    private static Stage window;
    private static int duration = 500;

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param minWidth lunghezza minima della finestra
     * @param minHeight larghezza minima della finestra
     * @param bDim dimensione bottone della finestra
     * @param i icona della finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static Ospedale display(String title, String message, int textSize, int minWidth, int minHeight, int bDim, Image i, Citta cit, Ospedale o) throws Exception{
        //window setup

        final Ospedale[] osp = new Ospedale[1];

        window = new Stage();
        window.getIcons().add(i);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);
        window.centerOnScreen();
        window.setOnCloseRequest(e -> {
            e.consume();
            osp[0] = null;
            closeWindow();
        });

        //label
        Label lbl = new Label();
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold; -fx-font-size: " + textSize);
        Label lbl1 = new Label();
        lbl1.setText("N° posti covid:");
        lbl1.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl2 = new Label();
        lbl2.setText("N° posti non covid:");
        lbl2.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl3 = new Label();
        lbl3.setText("N° rianimotori covid:");
        lbl3.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl4 = new Label();
        lbl4.setText("N° rianimotori non covid:");
        lbl4.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl5 = new Label();
        lbl5.setText("N° medici covid:");
        lbl5.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl6 = new Label();
        lbl6.setText("N° medici non covid:");
        lbl6.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl7 = new Label();
        lbl7.setText("N° ambulanze:");
        lbl7.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        Label lbl8 = new Label();
        lbl8.setText("N° autisti:");
        lbl8.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");

        TextField txt1 = new TextField(o.getDisp_postiCovid().toString());
        TextField txt2 = new TextField(o.getDisp_postiNorm().toString());
        TextField txt3 = new TextField(o.getDisp_rianimatori_covid().toString());
        TextField txt4 = new TextField(o.getDisp_rianimatori_norm().toString());
        TextField txt5 = new TextField(o.getDisp_mediciCovid().toString());
        TextField txt6 = new TextField(o.getDisp_mediciNorm().toString());
        TextField txt7 = new TextField(o.getDisp_ambulanze().toString());
        TextField txt8 = new TextField(o.getDisp_autisti().toString());

        //buttons
        yesButton = new Button("Ok");
        noButton = new Button("Cancel");
        defButton = new Button("Default");

        yesButton.setStyle("-fx-font-size:" + bDim);
        noButton.setStyle("-fx-font-size:" + bDim);
        defButton.setStyle("-fx-font-size:" + bDim);

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            try {
                if(Integer.parseInt(txt1.getText()) < 0
                || Integer.parseInt(txt2.getText()) < 0
                || Integer.parseInt(txt3.getText()) < 0
                || Integer.parseInt(txt4.getText()) < 0
                || Integer.parseInt(txt5.getText()) < 0
                || Integer.parseInt(txt6.getText()) < 0
                || Integer.parseInt(txt7.getText()) < 0
                || Integer.parseInt(txt8.getText()) < 0)
                    throw new NumberFormatException();
            }
            catch (NumberFormatException ex) {
                System.out.println("Not a valid number");
                osp[0] = null;
                closeWindow();
            }
            osp[0] = new Ospedale(Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()),
                    Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()),
                    Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText()),
                    Integer.parseInt(txt7.getText()), Integer.parseInt(txt8.getText()),
                    cit);       //da aggiustare
            closeWindow();
        });
        noButton.setOnAction(e -> {
            osp[0] = null;
            closeWindow();
        });
        defButton.setOnAction(e -> {
            txt1.setText("50");
            txt2.setText("50");
            txt3.setText("20");
            txt4.setText("20");
            txt5.setText("20");
            txt6.setText("20");
            txt7.setText("9");
            txt8.setText("14");
        });

        //layouts
        VBox layout = new VBox(10);
        HBox layoutButtons = new HBox(10);
        HBox layout1 = new HBox(10);
        HBox layout2 = new HBox(10);
        HBox layout3 = new HBox(10);
        HBox layout4 = new HBox(10);
        HBox layout5 = new HBox(10);
        HBox layout6 = new HBox(10);
        HBox layout7 = new HBox(10);
        HBox layout8 = new HBox(10);
        layout1.getChildren().addAll(lbl1, txt1);
        layout2.getChildren().addAll(lbl2, txt2);
        layout3.getChildren().addAll(lbl3, txt3);
        layout4.getChildren().addAll(lbl4, txt4);
        layout5.getChildren().addAll(lbl5, txt5);
        layout6.getChildren().addAll(lbl6, txt6);
        layout7.getChildren().addAll(lbl7, txt7);
        layout8.getChildren().addAll(lbl8, txt8);
        layout1.setAlignment(Pos.BASELINE_RIGHT);
        layout2.setAlignment(Pos.BASELINE_RIGHT);
        layout3.setAlignment(Pos.BASELINE_RIGHT);
        layout4.setAlignment(Pos.BASELINE_RIGHT);
        layout5.setAlignment(Pos.BASELINE_RIGHT);
        layout6.setAlignment(Pos.BASELINE_RIGHT);
        layout7.setAlignment(Pos.BASELINE_RIGHT);
        layout8.setAlignment(Pos.BASELINE_RIGHT);
        layout1.setPadding(new Insets(0,100,0,0));
        layout2.setPadding(new Insets(0,100,0,0));
        layout3.setPadding(new Insets(0,100,0,0));
        layout4.setPadding(new Insets(0,100,0,0));
        layout5.setPadding(new Insets(0,100,0,0));
        layout6.setPadding(new Insets(0,100,0,0));
        layout7.setPadding(new Insets(0,100,0,0));
        layout8.setPadding(new Insets(0,100,0,0));

        VBox layoutComplete = new VBox(10);
        VBox.setMargin(txt1, new Insets(0,10,0,10));
        VBox.setMargin(txt2, new Insets(0,10,0,10));
        VBox.setMargin(txt3, new Insets(0,10,0,10));
        VBox.setMargin(txt4, new Insets(0,10,0,10));

        //Add buttons and layouts
        layout.getChildren().addAll(lbl, layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8);
        layout.setAlignment(Pos.CENTER);
        layoutButtons.getChildren().addAll(defButton, yesButton, noButton);
        layoutButtons.setAlignment(Pos.CENTER);
        layoutComplete.getChildren().addAll(layout, layoutButtons);
        layoutComplete.setAlignment(Pos.CENTER);

        BackgroundImage myBI = new BackgroundImage(new Image("file:img/bg.jpg",900,600,
                false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layoutComplete.setBackground(new Background(myBI));

        //animation
        FadeTransition ft = new FadeTransition(Duration.millis(duration), layoutComplete);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();


        Scene scene = new Scene(layoutComplete);

        //JMetro style pack
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        //window stuff
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return osp[0];
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static Ospedale display(String title, String message, Citta cit, Ospedale o) throws Exception{
        return display(title, message, 30, 300, 150, 20, new Image("file:img\\icon.png"), cit, o);
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param bDim dimensione bottone della finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static Ospedale display(String title, String message, int textSize, int bDim, Citta cit, Ospedale o) throws Exception{
        return display(title, message, textSize, 400, 150, bDim, new Image("file:img\\icon.png"), cit, o);
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param minWidth lunghezza minima della finestra
     * @param minHeight larghezza minima della finestra
     * @param bDim dimensione bottone della finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static Ospedale display(String title, String message, int textSize, int bDim, int minWidth, int minHeight, Citta cit, Ospedale o) throws Exception{
        return display(title, message, textSize, minWidth, minHeight, bDim, new Image("file:img\\icon.png"), cit, o);
    }

    /**
     * Metodo che consente la chiusura della finestra
     */

    private static void closeWindow() {
        Timeline timeline = new Timeline();
        KeyFrame key = new KeyFrame(Duration.millis(duration), new KeyValue(window.getScene().getRoot().opacityProperty(), 0));
        timeline.getKeyFrames().add(key);
        timeline.setOnFinished((ae) -> window.close());
        timeline.play();
    }
}