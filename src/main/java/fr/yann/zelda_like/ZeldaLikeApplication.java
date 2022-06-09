package fr.yann.zelda_like;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.core.ImplZeldaLike;
import fr.yann.zelda_like.core.level.DemoLevel;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ZeldaLikeApplication extends Application {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    @Override
    public void start(Stage stage) throws IOException {
        final ZeldaLike zeldaLike = new ImplZeldaLike(
                System.LoggerFinder.getLoggerFinder().getLogger("Zelda Like", this.getClass().getModule()),
                new Group()
        );
        final Scene scene = new Scene(zeldaLike.getScene(), ZeldaLikeApplication.WIDTH, ZeldaLikeApplication.HEIGHT);
        stage.setTitle("Zelda like");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ZeldaLikeApplication.launch();
    }
}