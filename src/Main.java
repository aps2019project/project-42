import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import graphic.AccountPageMenu;
import graphic.PrimaryStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.Account;
import logic.Duelyst;
import logic.Message;

import java.io.*;
import java.lang.reflect.Type;
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
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        loadAccounts();
        new Thread(Main::consoleChat);
        Duelyst duelyst = new Duelyst();
        duelyst.preStart();
        launch(args);
//        try {
//            duelyst.preStart();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        launch(args);
    }

    private static void loadAccounts() {
        try {
            YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader("accounts.json");
            Account[] accounts = yaGson.fromJson(reader, (Type) Account[].class);
            if (accounts != null) {
                for (Account account : accounts) {
                    Duelyst.accounts.add(account);
                    System.out.println(account.getUser() + " added!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consoleChat() {
        try {
            File file = new File(".config");
            String ip = "localhost";
            int port = 8989;

            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                ip = bufferedReader.readLine();
                port = Integer.parseInt(bufferedReader.readLine());
            }
            Socket socket = new Socket(ip,port);
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
