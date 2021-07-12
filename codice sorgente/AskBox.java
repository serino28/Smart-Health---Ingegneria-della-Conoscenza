import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
 *AskBox <br>
 *La classe AskBox consente
 *la visualizzazione di una finestra
 *con menu a tendina e bottone (ok)
 *@author Davide Chiapperini, Simone Andriani
 */

public class AskBox {
    private static Label label;
    private static Label label2;
    private static Button okButton;
    private static ComboBox box;
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

    public static void display(String title, String message, int textSize, int minWidth, int minHeight, int bDim, Image i, Ospedale osp) {
        //window setup
        AppMain.active = false;
        window = new Stage();
        window.getIcons().add(i);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);
        window.centerOnScreen();
        window.setOnCloseRequest(e -> {
            e.consume();
            closeWindow();
        });

        //label
        label = new Label();
        label.setText(message);
        label.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        label.setFont(new Font(textSize));

        label2 = new Label("possono essere gestite emergenze non covid?");
        label2.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");

        //buttons
        okButton = new Button("Ok");
        okButton.setStyle("-fx-font-size:" + bDim);

        //box
        box = new ComboBox();
        box.getItems().addAll(
                "emergenze_normali_erogabili",
                "emergenze_covid_erogabili",
                "ospedale_2_mode",
                "ospedale_normale",
                "ospedale_covid",
                "ospedale_funzionante",
                "personale_normale_disponibile",
                "personale_covid_disponibile",
                "ambulanze_partibili",
                "medici_disponibili",
                "rianimatori_disponibili",
                "posti_normali_disponibili",
                "posti_covid_disponibili",
                "medici_normali_disponibili",
                "medici_covid_disponibili",
                "rianimatori_nomali_disponibili",
                "rianimatori_covid_disponibili",
                "normali_gestibile",
                "covid_gestibile",
                "ambulanze_disponibili",
                "autisti_disponibili"
        );
        box.getSelectionModel().selectFirst();
        box.valueProperty().addListener(e ->
        {
            switch (box.getSelectionModel().getSelectedItem().toString())
            {
                case "emergenze_normali_erogabili":
                {
                    label2.setText("possono essere gestite emergenze non covid?");
                    break;
                }
                case "emergenze_covid_erogabili":
                {
                    label2.setText("possono essere gestite emergenze covid?");
                    break;
                }
                case "ospedale_2_mode":
                {
                    label2.setText("l'ospedale è abilitato a gestire sia emergenze covid che non?");
                    break;
                }
                case "ospedale_normale":
                {
                    label2.setText("l'ospedale è abilitato a gestire solo emergenze non covid?");
                    break;
                }
                case "ospedale_covid":
                {
                    label2.setText("l'ospedale è abilitato a gestire solo emergenze covid?");
                    break;
                }
                case "ospedale_funzionante":
                {
                    label2.setText("l'ospedale è in funzione?");
                    break;
                }
                case "personale_normale_disponibile":
                {
                    label2.setText("il personale non covid è disponibile?");
                    break;
                }
                case "personale_covid_disponibile":
                {
                    label2.setText("il personale covid è disponibile?");
                    break;
                }
                case "ambulanze_partibili":
                {
                    label2.setText("è possibile far partire un'ambulanza?");
                    break;
                }
                case "medici_disponibili":
                {
                    label2.setText("ci sono medici disponibili?");
                    break;
                }
                case "rianimatori_disponibili":
                {
                    label2.setText("ci sono rianimatori disponibili?");
                    break;
                }
                case "posti_normali_disponibili":
                {
                    label2.setText("sono posti normali disponibili?");
                    break;
                }
                case "posti_covid_disponibili":
                {
                    label2.setText("ci sono posti covid disponibili?");
                    break;
                }
                case "autisti_disponibili":
                {
                    label2.setText("ci sono autisti disponibili?");
                    break;
                }
                case "medici_normali_disponibili":
                {
                    label2.setText("ci sono medici non covid disponibili?");
                    break;
                }
                case "medici_covid_disponibili":
                {
                    label2.setText("ci sono medici covid disponibili?");
                    break;
                }
                case "rianimatori_nomali_disponibili":
                {
                    label2.setText("ci sono rianimatori normali disponibili?");
                    break;
                }
                case "rianimatori_covid_disponibili":
                {
                    label2.setText("ci sono rianimatori covid disponibili?");
                    break;
                }
                case "normali_gestibile":
                {
                    label2.setText("è possibile gestire le emergenze normali?");
                    break;
                }
                case "covid_gestibile":
                {
                    label2.setText("è possibile gestire le emergenze covid?");
                    break;
                }
                case "ambulanze_disponibili":
                {
                    label2.setText("ci sono ambulanze disponibili?");
                    break;
                }
                default:
                {
                    label2.setText("");
                    break;
                }
            }
        });

        //Clicking will set answer and close window
        okButton.setOnAction(e -> {
            boolean risp = osp.getKb().askEsterno((String)box.getValue());
            try {
                AnswerBox.display("Risposte", (String)box.getValue() + ": " + risp, (String)box.getValue(), osp);
            } catch (Exception exception) {}
            closeWindow();
        });

        //layouts
        VBox layout = new VBox(10);
        HBox layoutButtons = new HBox(10);
        VBox layoutComplete = new VBox(10);

        //Add buttons and layouts
        layout.getChildren().addAll(label, box, label2);
        layout.setAlignment(Pos.CENTER);
        layoutButtons.getChildren().addAll(okButton);
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
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static void display(String title, String message, Ospedale osp) {
        display(title, message, 30, 300, 150, 20, new Image("file:img\\icon.png"), osp);
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param bDim dimensione bottone della finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static void display(String title, String message, int textSize, int bDim, Ospedale osp) {
        display(title, message, textSize, 400, 150, bDim, new Image("file:img\\icon.png"), osp);
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

    public static void display(String title, String message, int textSize, int bDim, int minWidth, int minHeight, Ospedale osp) {
        display(title, message, textSize, minWidth, minHeight, bDim, new Image("file:img\\icon.png"), osp);
    }

    public static void display(String title, String message, int minWidth, int minHeight, int bDim, Ospedale osp) {
        display(title, message, 30, minWidth, minHeight, bDim, new Image("file:img\\icon.png"), osp);
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