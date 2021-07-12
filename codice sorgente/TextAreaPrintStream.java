import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Class TextAreaPrintStream
 * extends PrintStream.
 * A custom made PrintStream which overrides methods println(String)
 * and print(String).
 * Thus, when the out stream is set as this PrintStream (with System.setOut
 * method), all calls to System.out.println(String) or System.out.print(String)
 * will result in an output stream of characters in the JTextArea given as an
 * argument of the constructor of the class.
 **/


public class TextAreaPrintStream extends PrintStream {

    //The JTextArea to wich the output stream will be redirected.
    private TextArea textArea;

    public TextAreaPrintStream(TextArea area, OutputStream out) {
        super(out);
        textArea = area;
    }

    public void println(String string) {
        Platform.runLater(() -> {
        textArea.appendText(string+"\n");
        });
    }

    public void print(String string) {
        Platform.runLater(() -> {
            textArea.appendText(string);
        });
    }
}