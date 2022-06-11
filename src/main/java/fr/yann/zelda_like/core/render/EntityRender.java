package fr.yann.zelda_like.core.render;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.api.render.Render;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class EntityRender implements Render {
    private final ZeldaLike zeldaLike;
    private final LevelRender levelRender;

    private Group group;

    public EntityRender(ZeldaLike zeldaLike, LevelRender levelRender) {
        this.zeldaLike = zeldaLike;
        this.levelRender = levelRender;
    }

    @Override
    public void render() {
        if (this.group != null) {
            this.levelRender.getGroup().getChildren().remove(this.group);
        }

        final Level level = this.zeldaLike.getLevelManager().get();
        if (level == null) {
            return;
        }

        this.group = new Group();

        final double xRatio = (double) ZeldaLikeApplication.WIDTH / (double) level.getWidth();
        final double yRatio = (double) ZeldaLikeApplication.HEIGHT / (double) level.getHeight();

        final double width = xRatio / 2.0;
        final double height = yRatio / 2.0;

        final double xOffset = (xRatio - width) / 2.0;
        final double yOffset = (yRatio - height) / 2.0;

        level.getEntities()
            .forEach(entity -> {

                final Image image = entity.getTexture();
                if (image != null) {
                    final ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(width);
                    imageView.setFitHeight(height);
                    imageView.setX((entity.getLocation().getX() * xRatio) + xOffset);
                    imageView.setY((entity.getLocation().getY() * yRatio) + yOffset);
                    this.group.getChildren().addAll(imageView);
                    return;
                }

                final Rectangle rectangle = new Rectangle();
                rectangle.setWidth(width);
                rectangle.setHeight(height);
                rectangle.setX((entity.getLocation().getX() * xRatio) + xOffset);
                rectangle.setY((entity.getLocation().getY() * yRatio) + yOffset);
                rectangle.setFill(entity.getColor());

                final Rectangle view = new Rectangle();
                view.setWidth(width / 5.0);
                view.setHeight(height / 5.0);
                view.setFill(Color.color(1, 0, 0));
                switch (entity.getLocation().getOrientation()) {
                    case NORTH -> {
                        view.setX(rectangle.getX() + (width / 2.0) - (view.getWidth() / 2.0));
                        view.setY(rectangle.getY() - (view.getHeight() / 2.0));
                    }
                    case SOUTH -> {
                        view.setX(rectangle.getX() + (width / 2.0) - (view.getWidth() / 2.0));
                        view.setY(rectangle.getY() + height - (view.getHeight() / 2.0));
                    }
                    case WEST -> {
                        view.setX(rectangle.getX() - (view.getWidth() / 2.0));
                        view.setY(rectangle.getY() + (height / 2.0) - (view.getHeight() / 2.0));
                    }
                    case EAST -> {
                        view.setX(rectangle.getX() + width - (view.getWidth() / 2.0));
                        view.setY(rectangle.getY() + (height / 2.0) - (view.getHeight() / 2.0));
                    }
                }

                this.group.getChildren().addAll(rectangle, view);
            });

        this.levelRender.getGroup().getChildren().add(1, this.group);
    }
}
