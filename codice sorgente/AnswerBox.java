import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class AnswerBox {
    private static boolean answer;
    private static Label label;
    private static Button howButton;
    private static Button whyButton;
    private static Button notButton;
    private static Button okButton;
    private static TextArea txt;
    private static Stage window;
    private static int duration = 500;

    public static boolean display(String title, String message, String answer, int textSize, int minWidth, int minHeight, int bDim, Image i, Ospedale osp) throws Exception{
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
        howButton = new Button("How?");
        notButton = new Button("Why not?");
        okButton = new Button("Close");
        howButton.setStyle("-fx-font-size:" + bDim);
        notButton.setStyle("-fx-font-size:" + bDim);
        okButton.setStyle("-fx-font-size:" + bDim);

        //Textarea
        txt = new TextArea();
        txt.setStyle("-fx-opacity: 1; -fx-font-size: 20; -fx-text-fill: #FFFFFF; -fx-max-width: 400; -fx-max-height: 200;");
        txt.setEditable(false);
        //txt.setMouseTransparent(true);
        txt.setFocusTraversable(false);

        if(AppMain.pause.getText() == "Pause")
            AppMain.active = true;
        final String how = osp.getKb().how(answer), not = osp.getKb().whyNot(answer);

        //Clicking will set answer and close window
        okButton.setOnAction(e -> {
            closeWindow();
        });
        howButton.setOnAction(e -> {
            txt.setText(how);
        });
        notButton.setOnAction(e -> {
            txt.setText(not);
        });

        //layouts
        VBox layout = new VBox(10);
        HBox innerlayout = new HBox(10);

        //Add buttons and layouts
        innerlayout.getChildren().addAll(howButton, notButton, okButton);
        innerlayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, innerlayout, txt);
        layout.setAlignment(Pos.CENTER);
        VBox.setMargin(txt, new Insets(0, 50, 20, 50));

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

        return true;
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static boolean display(String title, String message, String answer, Ospedale osp) throws Exception{
        return display(title, message, answer, 30, 300, 150, 20, new Image("file:img\\icon.png"), osp);
    }

    /**
     * Metodo che visualizza la finestra
     * @param title titolo della finestra
     * @param message messaggio mostrato nella finestra
     * @param textSize dimensione testo della finestra
     * @param bDim dimensione bottone della finestra
     * @return bottone cliccato (true per yesButton, false per noButton)
     */

    public static boolean display(String title, String message, String answer, int textSize, int bDim, Ospedale osp) throws Exception{
        return display(title, message, answer, textSize, 400, 150, bDim, new Image("file:img\\icon.png"), osp);
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

    public static boolean display(String title, String message, String answer, int textSize, int bDim, int minWidth, int minHeight, Ospedale osp) throws Exception{
        return display(title, message, answer, textSize, minWidth, minHeight, bDim, new Image("file:img\\icon.png"), osp);
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