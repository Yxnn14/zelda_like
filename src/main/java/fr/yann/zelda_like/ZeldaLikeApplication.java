package fr.yann.zelda_like;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.controller.Cursor;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.core.ImplZeldaLike;
import fr.yann.zelda_like.core.controller.ImplController;
import fr.yann.zelda_like.core.level.DemoLevel;
import fr.yann.zelda_like.core.level.OneLevel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.BiConsumer;

public class ZeldaLikeApplication extends Application {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private static int fps = 0;
    private static int tps = 0;

    public static int getFps() {
        return ZeldaLikeApplication.fps;
    }

    public static int getTps() {
        return ZeldaLikeApplication.tps;
    }

    @Override
    public void start(Stage stage) throws IOException {
        final ZeldaLike zeldaLike = new ImplZeldaLike(
                System.LoggerFinder.getLoggerFinder().getLogger("Zelda Like", this.getClass().getModule()),
                new Group()
        );

        final Scene scene = new Scene(
            zeldaLike.getScene(),
            ZeldaLikeApplication.WIDTH,
            ZeldaLikeApplication.HEIGHT,
            false,
            SceneAntialiasing.BALANCED
        );
        scene.setFill(Color.color(0, 0, 0));

        this.registerKeyBind(zeldaLike, scene);

        zeldaLike.getLevelManager().load(OneLevel.class);

        stage.setTitle("Zelda like");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // TODO: Mise a jour du rendu (~60FPS)
        final AnimationTimer frameTimer = new AnimationTimer() {
            int counter = 0;
            long lastTime = System.currentTimeMillis();
            @Override
            public void handle(long l) {
                zeldaLike.getLevelManager().getRender().render();
                counter++;
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastTime >= 1000L) {
                    ZeldaLikeApplication.fps = counter;
                    counter = 0;
                    lastTime += 1000;
                }
            }
        };
        frameTimer.start();

        // TODO: Mise à jour technique (~20TPS)
        Thread tickTimer = new Thread(() -> {
            final double tickTimeOffset = 1000000000.0/20.0;
            long lastTickTime = System.nanoTime();
            long lastTime = System.currentTimeMillis();
            int tps = 0;
            while (!Thread.currentThread().isInterrupted()) {
                long currentTickTime = System.nanoTime();
                long currentTime = System.currentTimeMillis();
                if (currentTickTime - lastTickTime > tickTimeOffset) {
                    lastTickTime += tickTimeOffset;
                    final Level level = zeldaLike.getLevelManager().get();
                    if (level != null) {
                        level.getUpdaterManager().update();
                    }
                    tps++;
                }
                if (currentTime - lastTime >= 1000) {
                    ZeldaLikeApplication.tps = tps;
                    tps = 0;
                    lastTime += 1000;
                }
            }
        });
        tickTimer.start();

        stage.setOnCloseRequest(windowEvent -> {
            frameTimer.stop();
            tickTimer.interrupt();
        });
    }

    private void registerKeyBind(ZeldaLike zeldaLike, Scene scene)
    {
        zeldaLike.getControllerManager()
            .register(ImplController.create(Controller.UP, KeyCode.Z, KeyCode.UP))
            .register(ImplController.create(Controller.DOWN, KeyCode.S, KeyCode.DOWN))
            .register(ImplController.create(Controller.LEFT, KeyCode.Q, KeyCode.LEFT))
            .register(ImplController.create(Controller.RIGHT, KeyCode.D, KeyCode.RIGHT))
            .register(ImplController.create(Controller.ACTION, KeyCode.SPACE, MouseButton.PRIMARY))
            .register(ImplController.create(Controller.OPEN_INVENTORY, KeyCode.E))
            .register(ImplController.create(Controller.SLOT_1, KeyCode.DIGIT1, KeyCode.NUMPAD0))
            .register(ImplController.create(Controller.SLOT_2, KeyCode.DIGIT2, KeyCode.NUMPAD1))
            .register(ImplController.create(Controller.SLOT_3, KeyCode.DIGIT3, KeyCode.NUMPAD2))
            .register(ImplController.create(Controller.SLOT_4, KeyCode.DIGIT4, KeyCode.NUMPAD3))
            .register(ImplController.create(Controller.SLOT_5, KeyCode.DIGIT5, KeyCode.NUMPAD4))
            .register(ImplController.create(Controller.SLOT_6, KeyCode.DIGIT6, KeyCode.NUMPAD5))
            .register(ImplController.create(Controller.SLOT_7, KeyCode.DIGIT7, KeyCode.NUMPAD6))
            .register(ImplController.create(Controller.SLOT_8, KeyCode.DIGIT8, KeyCode.NUMPAD7))
            .register(ImplController.create(Controller.SLOT_9, KeyCode.DIGIT9, KeyCode.NUMPAD8))
            .register(ImplController.create(Controller.SLOT_10, KeyCode.DIGIT0, KeyCode.NUMPAD9))
            .register(ImplController.create(Controller.FUNCTION_1, KeyCode.F1))
            .register(ImplController.create(Controller.FUNCTION_2, KeyCode.F2));
        ;


        final BiConsumer<Object, Boolean> keyController = (key, pressed) -> {
            final Controller controller;
            if (key instanceof KeyCode keyCode) {
                controller = zeldaLike.getControllerManager().by(keyCode);
            } else if (key instanceof MouseButton mouseButton) {
                controller = zeldaLike.getControllerManager().by(mouseButton);
            } else {
                controller = null;
            }
            if (controller != null) {
                controller.setPressed(pressed);
            }
        };

        scene.setOnKeyPressed(keyEvent -> keyController.accept(keyEvent.getCode(), true));
        scene.setOnKeyReleased(keyEvent -> keyController.accept(keyEvent.getCode(), false));

        scene.setOnMouseMoved(
            mouseEvent -> zeldaLike.getControllerManager().getCursor()
                .move(mouseEvent.getX(), mouseEvent.getY())
        );

        scene.setOnMousePressed(mouseEvent -> keyController.accept(mouseEvent.getButton(), true));
        scene.setOnMouseReleased(mouseEvent -> {
            keyController.accept(mouseEvent.getButton(), false);
            zeldaLike.getControllerManager().getCursor().setDragged(false);
            zeldaLike.getControllerManager().getCursor().move(mouseEvent.getX(), mouseEvent.getY());
        });

        scene.setOnMouseDragged(mouseEvent -> {
            final Cursor cursor = zeldaLike.getControllerManager().getCursor();
            cursor.setDragged(true);
            cursor.moveDrag(mouseEvent.getX(), mouseEvent.getY());
        });
    }

    public static void main(String[] args) {
        ZeldaLikeApplication.launch();
    }
}