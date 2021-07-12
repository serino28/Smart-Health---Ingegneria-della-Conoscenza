/*public class AppMain {
    public static void main ( String args[]){
        Boolean prima_esecuzione = true;
        Citta citta = new Citta();
        System.out.println("costruita citta");
        Ospedale ospedale = new Ospedale(prima_esecuzione,citta);
        System.out.println("costruito ospedale");
        citta.setOspedale(ospedale);
        citta.mandaSegnalazione();

        //temporizzare lo scorrere del tempo
        //in base a temporizzazione lanciare segnalzioni
    }
}*/
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import javax.security.auth.login.AccountLockedException;

public class AppMain extends Application {
    private Stage window;
    private BorderPane layout;
    private int duration = 500;
    public static TextArea txt = new TextArea();
    private static Boolean prima_esecuzione = true;
    private static Citta citta = new Citta();
    private static Ospedale osp;
    public static boolean active = true;
    public static Button pause;


    /**
     * Metodo dal quale si avvia il programma
     *
     * @param args indirizzo del server (in posizione 0), porta del server (in posizione 1)
     */

    public static void main(String[] args) throws Exception {

        PipedOutputStream pout = new PipedOutputStream();
        PrintStream pst = new TextAreaPrintStream(txt, pout);
        System.setOut(pst);
        /*
        Console console = new Console(txt);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);*/

        try {
            launch(args);
        } catch (Exception ex){
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        {
            try {
            osp = new Ospedale(prima_esecuzione,citta);
            citta.setOspedale(osp);
            window = primaryStage;
            window.setTitle("Smart Health");
            window.getIcons().add(new Image("file:img\\icon.png"));
            window.setMinWidth(900);
            window.setMaxWidth(900);
            window.setMinHeight(600);
            window.setMaxHeight(600);
            window.setOnCloseRequest(e -> {
                e.consume();
                try {
                    closeApplication(0);
                } catch (Exception exception){}
            });
            window.centerOnScreen();

            //File menu
            Menu fileMenu = new Menu("_File");
            MenuItem map = new MenuItem("View Map...");
            MenuItem exp = new MenuItem("View Ontologies...");
            MenuItem sett = new MenuItem("Settings...");
            MenuItem exit = new MenuItem("Exit...");
            fileMenu.getItems().addAll(map, exp, sett, exit);
            exit.setOnAction(e -> {
                try {
                    closeApplication(0);
                } catch (Exception exception) {}
            });

            //? Menu
            Menu PIMenu = new Menu("?");
            MenuItem about = new MenuItem("About");
            PIMenu.getItems().add(about);
            MenuItem cfu = new MenuItem("Check for Updates");
            PIMenu.getItems().add(cfu);
            exp.setOnAction(e->{
                String url = "file:///"+System.getProperty("user.dir")+"/info/ontologie.html";
                url = url.replace("\\", "/");
                url = url.replace(" ", "%20");
                try {

                    URI oURL = new URI(url);
                    oURL.normalize();
                    Desktop.getDesktop().browse(oURL);
                }
                catch (Exception ex){System.out.println("File not found: " + url);}
            });
                final Ospedale[] o = new Ospedale[1];
            sett.setOnAction(e -> {
                try {
                    o[0] = OspedaleBox.display("Cambia Ospedale", "Inserisci nuovi dati per ospedale:", citta, osp);
                } catch (Exception exception) {}
                if(o[0] != null)
                    citta.setOspedale(o[0]);
            });
            cfu.setOnAction(e -> {
                try {
                    AlertBox.display("Updates", "There are no new updates");
                } catch (Exception exception) {}
            });
            about.setOnAction(e -> {
                try {
                    AboutBox.display("About",
                            "Sviluppatori: " + '\n'
                                    + "1. Davide Chiapperini" + '\n'
                                    + "2. Simone Andriani" + '\n'
                                    + "3. Antonio Serino" + '\n'
                                    + "4. Nicola Francavilla", 25, 20, 600, 440);
                } catch (Exception exception) {}
            });
            map.setOnAction(e -> {
                try {
                    AboutBox.display("View Map",
                            "Mappa della città", 25, 800, 500, 20, new Image("file:img/map.jpg"), 600);
                } catch (Exception exception) {}
            });

            //Main menu bar
            MenuBar menuBar = new MenuBar();
            menuBar.getMenus().addAll(fileMenu, PIMenu);

            //Layout buttons
            Label lbl = new Label("BARRA DI VISUALIZZAZIONE EVENTI");
            lbl.setStyle("-fx-text-fill: #003184;-fx-font-size: 15; -fx-font-weight: bold");
            TextField time = new TextField("");
            txt.setEditable(false);
            //txt.setMouseTransparent(true);
            txt.setWrapText(true);
            txt.setFocusTraversable(false);
            time.setDisable(true);
            txt.setStyle("-fx-text-fill: #FFFFFF;" + "-fx-font-size: 15;" + "-fx-max-width: 800;" + "-fx-min-height: 320");
            time.setStyle("-fx-text-fill: #FFFFFF;" + "-fx-max-width: 200;" + "-fx-text-alignment: center;");

            //center layout
            VBox centerlayout = new VBox(8);
            //centerlayout.setStyle("-fx-background-color: #9C9C9C;");
            VBox.setMargin(time, new Insets(0, 600, 0, 0));
            VBox.setMargin(lbl, new Insets(0, 50, 0, 50));
            VBox.setMargin(txt, new Insets(0, 20, 0, 20));


            Timer myTimer = new Timer();

                myTimer.scheduleAtFixedRate(new TimerTask() {

                    @Override
                    public void run() {
                    try{
                        Calendar cal = Calendar.getInstance();
                        time.setText(cal.getTime().toString());
                        if (cal.getTime().getSeconds() % 30 == 0 && active && osp != null) {
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            citta.mandaSegnalazione();
                        }
                        if (cal.getTime().getSeconds() == 30 && osp != null) {
                            osp.simulaDegenzaPazienti();
                        }
                    } catch (NullPointerException ex) {}
                    catch (Exception e){}
                    }
                }, 0, 1000);

            centerlayout.getChildren().addAll(time, lbl, txt);

            //ask
            Button ask = new Button("Ask");
            ask.setStyle("-fx-font-size:20");
            ask.setOnAction(e -> AskBox.display("Ask", "Chiedi qualcosa al sistema", 500, 200, 20, osp));

            Button clear = new Button("Clear Console");
            clear.setStyle("-fx-font-size:20");
            clear.setOnAction(e-> txt.setText(""));


            Button start = new Button("New Emergency");
            start.setStyle("-fx-font-size:20");
            start.setOnAction(e->{
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    citta.mandaSegnalazione();
            });
            pause = new Button("Pause");
            pause.setStyle("-fx-font-size:20");
            pause.setOnAction(e->{
                if(pause.getText() == "Pause") {
                    active = false;
                    pause.setText("Play");
                }
                else
                {
                    active = true;
                    pause.setText("Pause");
                }
            });

            HBox buttonlayout = new HBox(10);
            buttonlayout.setAlignment(Pos.CENTER);
            buttonlayout.getChildren().addAll(pause, start, ask, clear);
            centerlayout.setAlignment(Pos.CENTER);
            centerlayout.getChildren().addAll(buttonlayout);

            //final layout
            layout = new BorderPane();
            layout.setTop(menuBar);
            layout.setCenter(centerlayout);

            BackgroundImage myBI = new BackgroundImage(new Image("file:img/screen2.jpg",900,600,
                    false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            layout.setBackground(new Background(myBI));
            Scene scene = new Scene(layout, 500, 400);

            //JMetro style pack
            JMetro jMetro = new JMetro(Style.DARK);
            jMetro.setScene(scene);

            //window stuff
            window.setScene(scene);
            window.show();
            } catch (Exception e){}
            //System.out.println("costruito ospedale");
        }
    }

    /**
     * Metodo che consente la chiusura della finestra
     *
     * @param mode 0 se con conferma del client, 1 senza conferma
     */

    public void closeApplication(int mode) throws Exception {
        if (mode == 0) {
            if (ConfirmBox.display("Exit window", "Are you sure?")) {
                Timeline timeline = new Timeline();
                KeyFrame key = new KeyFrame(Duration.millis(500), new KeyValue(window.getScene().getRoot().opacityProperty(), 0));
                timeline.getKeyFrames().add(key);
                timeline.setOnFinished((ae) ->
                {
                    window.close();
                    Thread.currentThread().stop();
                });
                timeline.play();
            }
        } else {
            Timeline timeline = new Timeline();
            KeyFrame key = new KeyFrame(Duration.millis(500), new KeyValue(window.getScene().getRoot().opacityProperty(), 0));
            timeline.getKeyFrames().add(key);
            timeline.setOnFinished((ae) ->
            {
                window.close();
                Thread.currentThread().stop();
            });
            timeline.play();
        }
    }

    public static class Console extends OutputStream {

        private TextArea output;

        public Console(TextArea ta) {
            this.output = ta;
        }

        @Override
        public void write(int i) throws IOException {
            output.appendText(String.valueOf((char) i));
        }
    }
}