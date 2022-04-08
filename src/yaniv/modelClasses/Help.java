package yaniv.modelClasses;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Help {
    public static void help() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java"));
    }
}
