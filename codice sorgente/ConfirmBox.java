import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
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

public class ConfirmBox {
    private static boolean answer;
    private static Label label;
    private static Button yesButton;
    private static Button noButton;
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

    public static boolean display(String title, String message, int textSize, int minWidth, int minHeight, int bDim, Image i) throws Exception{
        //window setup
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

        //buttons
        yesButton = new Button("Yes");
        noButton = new Button("No");

        yesButton.setStyle("-fx-font-size:" + bDim);
        noButton.setStyle("-fx-font-size:" + bDim);

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            closeWindow();
        });
        noButton.setOnAction(e -> {
            answer = false;
            closeWindow();
        });

        //layouts
        VBox layout = new VBox(10);
        HBox layoutButtons = new HBox(10);
        VBox layoutComplete = new VBox(10);

        //Add buttons and layouts
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);
        layoutButtons.getChildren().addAll(yesButton, noButton);
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
        return answer;
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static boolean display(String title, String message) throws Exception{
        return display(title, message, 30, 300, 150, 20, new Image("file:img\\icon.png"));
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param bDim dimensione bottone della finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static boolean display(String title, String message, int textSize, int bDim) throws Exception{
        return display(title, message, textSize, 400, 150, bDim, new Image("file:img\\icon.png"));
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

    public static boolean display(String title, String message, int textSize, int bDim, int minWidth, int minHeight) throws Exception{
        return display(title, message, textSize, minWidth, minHeight, bDim, new Image("file:img\\icon.png"));
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