package sample;


import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Controller {

    public void buttonClicked(ActionEvent event) {
        Scene scene = ((Button) event.getSource()).getScene();

        TextField pf = (TextField) scene.lookup("#password_input");
        String password = pf.getText().trim();

        String made = make(password);
        Label resultLabel = (Label) scene.lookup("#result_label");
        resultLabel.setText(made);
        resultLabel.setWrapText(true);
        resultLabel.setTooltip(new Tooltip("Click to copy this to clipboard"));

        System.out.println("--done");
    }

    private String make(String password) {
        if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PassPiss");
            alert.setHeaderText(null);
            alert.setContentText("Can not process empty strings!");
            alert.showAndWait();
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hexdigest = byteToHex(hash);
            System.out.println(hexdigest);
            return  hexdigest;

        } catch (NoSuchAlgorithmException ee) {
            ee.printStackTrace();
            return  null;
        }
    }

    String byteToHex(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : bytes) builder.append(String.format("%02x", b));
        return builder.toString();
    }
}
