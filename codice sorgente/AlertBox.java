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
 *AlertBox <br>
 *La classe AlertBox consente
 *la visualizzazione di una finestra Alert (finestra con testo e bottone)
 *@author Davide Chiapperini, Simone Andriani
 */

public class AlertBox {
    private static int duration = 500;
    private static Label label;
    private static Button closeButton;
    private static Stage window;

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param minWidth lunghezza minima della finestra
     * @param minHeight larghezza minima della finestra
     * @param bDim dimensione bottone della finestra
     * @param i icona della finestra
     */

    public static void display(String title, String message, int textSize, int minWidth, int minHeight, int bDim, Image i) throws Exception{
        //window setup
        window = new Stage();
        window.getIcons().add(i);
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);
        window.centerOnScreen();

        //label and button
        label = new Label();
        label.setText(message);
        label.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold");
        label.setFont(new Font(textSize));
        label.setAlignment(Pos.CENTER);
        closeButton = new Button("Close this window");
        closeButton.setStyle("-fx-font-size:" + bDim);
        closeButton.setOnAction(e -> closeWindow());
        window.setOnCloseRequest(e -> {
            e.consume();
            closeWindow();
        });

        //final layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        BackgroundImage myBI = new BackgroundImage(new Image("file:img/bg.jpg",900,600,
                false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout.setBackground(new Background(myBI));

        //animation
        FadeTransition ft = new FadeTransition(Duration.millis(duration), layout);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        Scene scene = new Scene(layout);

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
     */

    public static void display(String title, String message) throws Exception{
        display(title, message, 30, 400, 150, 20, new Image("file:img\\icon.png"));
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param bDim dimensione bottone della finestra
     */

    public static void display(String title, String message, int textSize, int bDim) throws Exception{
        display(title, message, textSize, 400, 150, bDim, new Image("file:img\\icon.png"));
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param minWidth lunghezza minima della finestra
     * @param minHeight larghezza minima della finestra
     * @param bDim dimensione bottone della finestra
     */

    public static void display(String title, String message, int textSize, int bDim, int minWidth, int minHeight) throws Exception{
        display(title, message, textSize, minWidth, minHeight, bDim, new Image("file:img\\icon.png"));
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