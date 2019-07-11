import graphic.AccountPageMenu;
import graphic.PrimaryStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.Duelyst;
import logic.Message;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Main extends Application {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryStage.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(AccountPageMenu.class.getResource("AccountPageMenu.fxml"));
        Image image = new Image("ui/mouse_attack@2x.png");
        root.setCursor(new ImageCursor(image));
        Duelyst.playMusic();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            Duelyst duelyst = new Duelyst();
            try {
                duelyst.preStart();
            } catch (IOException e) {
                e.printStackTrace();
            }
            launch(args);
        });
        try {
            Socket socket = new Socket("192.168.198.101", 8989);
            OutputStream outputStream = socket.getOutputStream();
            Formatter formatter = new Formatter(outputStream);

            System.out.println("Enter your username:");
            String clientName = scanner.nextLine();

            new Thread(() -> {
                String message = scanner.nextLine();
                while (socket.isConnected()) {
                    formatter.format(new Message(clientName, message).toJson() + "\n");
                    formatter.flush();
                    message = scanner.nextLine();
                }
                try {
                    socket.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    Scanner scanner = new Scanner(socket.getInputStream());
                    while (true) {
                        if (scanner.hasNextLine()) {
                            System.out.print(Message.fromJson(scanner.nextLine()));
                        }
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
